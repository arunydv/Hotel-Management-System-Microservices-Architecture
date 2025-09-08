package com.rating.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class RatingConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security
		        .authorizeHttpRequests(requests -> requests.requestMatchers("/actuator/**").permitAll().anyRequest().authenticated())
		        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));
		return security.build();
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
