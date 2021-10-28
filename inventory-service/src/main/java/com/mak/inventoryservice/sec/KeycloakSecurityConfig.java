//package com.mak.inventoryservice.sec;
//
//import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
//import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.session.SessionRegistryImpl;
//import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
//import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
//
//
//// equivalent to securityConfig of spring security (heritage from WebSecurityConfigurerAdapter)
////@KeycloakConfiguration
//public class KeycloakSecurityConfig extends KeycloakWebSecurityConfigurerAdapter {
//    @Override
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // auth.jdbcAuthentication() : when users are in database, you specify how to get users and roles
//        // userDetailsService() : implementation  in you service to handle users and roles
//        auth.authenticationProvider(keycloakAuthenticationProvider());// delegate management of users and roles to other provider like keycloak
//
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
//        http.authorizeRequests().antMatchers("/products/**", "/suppliers/**").permitAll();
//
//    }
//}
