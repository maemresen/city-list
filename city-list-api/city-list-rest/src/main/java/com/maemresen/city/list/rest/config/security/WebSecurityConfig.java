package com.maemresen.city.list.rest.config.security;

import com.maemresen.city.list.domain.service.impl.CustomUserDetailsServiceImpl;
import com.maemresen.city.list.domain.service.model.prop.security.cors.CorsProps;
import com.maemresen.city.list.domain.service.model.prop.security.path.PathAllowStatus;
import com.maemresen.city.list.domain.service.model.prop.security.path.PathProps;
import com.maemresen.city.list.rest.config.security.jwt.JwtAuthenticationEntryPoint;
import com.maemresen.city.list.rest.config.security.jwt.JwtAuthenticationRequestFilter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * API Security related configurations
 */
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = false, securedEnabled = true)
@Configuration
public class WebSecurityConfig {

	@Autowired
	public void configure(AuthenticationManagerBuilder builder, CustomUserDetailsServiceImpl customUserDetailsService, BCryptPasswordEncoder passwordEncoder) throws Exception {
		builder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder);
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http, JwtAuthenticationEntryPoint authEntryPoint, JwtAuthenticationRequestFilter authRequestFilter, PathProps pathProps) throws Exception {
		http.cors().and()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeHttpRequests(authorize -> {
					PathAllowStatus publicPaths = pathProps.getPublicPaths();
					var registry = authorize.requestMatchers(publicPaths.getAll()).permitAll();

					for (var entry : MapUtils.emptyIfNull(pathProps.getPublicPaths().getByHttpMethod()).entrySet()) {
						var httpMethod = entry.getKey();
						var paths = entry.getValue();
						registry = registry.requestMatchers(httpMethod, paths).permitAll();
					}

					registry.anyRequest().authenticated();
				}
			).addFilterBefore(authRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource(CorsProps corsProps) {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(corsProps.isAllowedCredentials());
		configuration.setAllowedOrigins(corsProps.getAllowedOrigins());
		configuration.setAllowedMethods(corsProps.getAllowedMethods());
		configuration.setAllowedHeaders(corsProps.getAllowedHeaders());
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
