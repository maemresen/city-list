package com.maemresen.city.list.rest.config.security;

import com.maemresen.city.list.rest.config.security.jwt.JwtAuthenticationEntryPoint;
import com.maemresen.city.list.rest.config.security.jwt.JwtAuthenticationRequestFilter;
import com.maemresen.city.list.rest.config.security.prop.PathProps;
import com.maemresen.city.list.rest.config.security.user.CustomUserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
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

	private final PathProps pathProps;

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final JwtAuthenticationRequestFilter jwtAuthenticationRequestFilter;
	private final CustomUserDetailsServiceImpl customUserDetailsService;

	@Autowired
	void configure(AuthenticationManagerBuilder builder) throws Exception {
		builder.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeHttpRequests(authorize -> authorize
				.requestMatchers(pathProps.getPublicPaths()).permitAll()
				.anyRequest().authenticated()
			);
		http.addFilterBefore(jwtAuthenticationRequestFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
