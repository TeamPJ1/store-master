
/*
package com.mak.gatewayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;


@Configuration
public class SecurityConfig  {

/*
	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http,
															ReactiveClientRegistrationRepositoryConfiguration  clientRegistrationRepository) {

		// Require authentication for all requests
		http.cors().and().authorizeExchange().anyExchange().permitAll();

		// Allow showing /home within a frame
//      http.headers().frameOptions().mode( XFrameOptionsServerHttpHeadersWriter.Mode.SAMEORIGIN);

		// Disable CSRF in the gateway to prevent conflicts with proxied service CSRF
		http.csrf().disable();
		return http.build();
	}

}


*/
