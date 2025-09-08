package com.user.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
public class UserConfig {
	
	@Autowired
	private ClientRegistrationRepository clientRegistrationRepository;
	@Autowired
	private OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository;
	
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security
		        .authorizeHttpRequests(requests -> requests.requestMatchers("/actuator/**").permitAll().anyRequest().authenticated())
		        .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> {}));
		return security.build();
	}

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
    	List<ClientHttpRequestInterceptor> interceptor = new ArrayList<>();
    	interceptor.add(new RestTemplateInterceptor(manager(clientRegistrationRepository, auth2AuthorizedClientRepository)));
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setInterceptors(interceptor);
		return restTemplate;
	}
    
    @Bean
    OAuth2AuthorizedClientManager manager(ClientRegistrationRepository clientRegistrationRepository, OAuth2AuthorizedClientRepository auth2AuthorizedClientRepository){
    	OAuth2AuthorizedClientProvider provider = OAuth2AuthorizedClientProviderBuilder.builder().clientCredentials().build();
    	DefaultOAuth2AuthorizedClientManager defaultOAuth2AuthorizedClientManager = new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository, auth2AuthorizedClientRepository);
    	defaultOAuth2AuthorizedClientManager.setAuthorizedClientProvider(provider);
    	return defaultOAuth2AuthorizedClientManager;
    }
}
