package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	SecurityWebFilterChain chain(ServerHttpSecurity httpSecurity,
			ReactiveClientRegistrationRepository clientRegistrationRepository) {
		httpSecurity.authorizeExchange(exchanges -> exchanges.anyExchange().authenticated()).oauth2Login(oauth2 -> {
		}).logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler(clientRegistrationRepository)))
				.oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {
				}));
		return httpSecurity.build();
	}

	private ServerLogoutSuccessHandler oidcLogoutSuccessHandler(
			ReactiveClientRegistrationRepository clientRegistrationRepository) {

		OidcClientInitiatedServerLogoutSuccessHandler handler = new OidcClientInitiatedServerLogoutSuccessHandler(
				clientRegistrationRepository);
		handler.setPostLogoutRedirectUri("http://localhost:8083/auth/login");

		return handler;
	}
}
