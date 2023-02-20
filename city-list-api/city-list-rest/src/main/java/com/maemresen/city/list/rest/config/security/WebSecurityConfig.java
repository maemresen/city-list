package com.maemresen.city.list.rest.config.security;

import com.maemresen.city.list.domain.service.impl.CustomUserDetailsServiceImpl;
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
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * API Security related configurations
 */
@RequiredArgsConstructor
@EnableWebSecurity
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
	public SecurityFilterChain configure(HttpSecurity http, JwtAuthenticationEntryPoint authEntryPoint, JwtAuthenticationRequestFilter authREquestFilter, PathProps pathProps
	) throws Exception {
		http.cors().and()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(authEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeHttpRequests(authorize -> {
					PathAllowStatus publicPaths = pathProps.getPublicPaths();
					var registry = authorize.requestMatchers(publicPaths.getAll()).permitAll();

					for (var entry : MapUtils.emptyIfNull(pathProps.getPublicPaths().getByHttpMethod()).entrySet()){
						var httpMethod = entry.getKey();
						var paths = entry.getValue();
						registry = registry.requestMatchers(httpMethod, paths).permitAll();
					}

					registry.anyRequest().authenticated();
				}
			).addFilterBefore(authREquestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}