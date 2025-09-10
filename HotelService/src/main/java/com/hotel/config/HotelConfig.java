package com.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class HotelConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security
	            .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
	            .oauth2ResourceServer(Oauth2 -> Oauth2.jwt(jwt -> {}));
		return security.build();
	}
}
