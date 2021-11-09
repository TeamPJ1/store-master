package com.mak.gatewayservice.sec;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;

//@Profile("development")
//@EnableWebFlux
@Configuration
public class GatewayCorsConfiguration {

    /**
     * 1- Configure Bean
     * SimpleUrlHandlerMapping
     * 2- Create Bean
     * CorsWebFilter
     * 3- add this to application.yaml
     * ========================================================================
     * # Cors configuration
     * spring.cloud.gateway.globalcors.add-to-simple-url-handler-mapping=true
     * spring.cloud.gateway.default-filters=[DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials, RETAIN_UNIQUE]
     * # service name in lower MY-SERVICE -> my-service
     * spring.cloud.gateway.discovery.locator.lower-case-service-id=true
     * ========================================================================
	 * it appears that the DedupeResponseHeader filter needs to be inserted as a filter for each route and does not work as a global default-filter.
     *
     * you can also add this but check if it's work according versions.
     * corsConfigurations:
     * '[/**]':
     * allowedOrigins: "*"
     * allowedMethods:
     * - GET
     * - POST
     * - DELETE
     * - PUT
     **/

    private String allowedOriginPattern = "*";

    @Bean
    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
        return simpleUrlHandlerMapping;
    }

    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addAllowedOrigin(allowedOriginPattern);
        corsConfiguration.addExposedHeader(HttpHeaders.SET_COOKIE);
        final UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsWebFilter(corsConfigurationSource);
    }

/*    @Bean
    public WebFluxConfigurer getCorsConfiguration() {
        return new WebFluxConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //.allowedOrigins(allowedOrigin)
                        .allowedOrigins(allowedOriginPattern)
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(36000)
                        .exposedHeaders("**")
                        .allowCredentials(true);
                //.exposedHeaders("Authorization");
            }
        };
    }*/

}