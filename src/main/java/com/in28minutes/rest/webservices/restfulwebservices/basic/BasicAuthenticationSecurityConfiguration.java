package com.in28minutes.rest.webservices.restfulwebservices.basic;

import org.springframework.security.config.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.security.auth.message.config.AuthConfig;
import jakarta.websocket.Session;

//@Configuration
public class BasicAuthenticationSecurityConfiguration {
	
	// We'll be configuring the Spring security Filter chain: it is capable of being matched against a http servlet request
	
	// All below steps must be ENABLED AND configured
	// authenticate all requests
	//basic authentication
	// disabling csrf
	//stateless rest api
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
//		// enabling authenticate all requests
//		http.authorizeHttpRequests(
//				auth -> auth.anyRequest().authenticated());
//		
//		//enabling basic authentication
//		http.httpBasic(Customizer.withDefaults());    // http basic authentication meaning: it provides a authentication pop-up while accessing the rest api url
//		
//		//configuring stateless rest api
//		http.sessionManagement(
//				Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//		
//		// disabling csrf
//		http.csrf().disable();
//		Above can be written as chaining of calls
		return http
				.authorizeHttpRequests(
						auth -> 
							auth
							.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
							.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(
						Session -> Session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.csrf().disable()
				.build();
	}

}
