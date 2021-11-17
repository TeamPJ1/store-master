package com.mak.inventoryservice.config.cors;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Profile("development")
@Configuration
public class CorsConfiguration {

    @Value("${allowed.origin}")
    private String allowedOrigin;

    //@Value("#{${allowed.origin.pattern}}")
    private String allowedOriginPattern = "*";

    @Bean
    public WebMvcConfigurer getCorsConfiguration() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        //.allowedOrigins(allowedOrigin)
                     //   .allowedOriginPatterns(allowedOriginPattern)
                        .allowedOrigins(allowedOriginPattern)
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(3600)
                        .allowCredentials(true);
                        //exposedHeaders("*");
                //.exposedHeaders("Authorization");
            }
        };
    }


    /* Specific to @RepositoryRestResource*/
    @Bean
    public RepositoryRestConfigurer getRepositoryRestConfiguration() {
        return new RepositoryRestConfigurer() {
            public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
                cors.addMapping("/**")
                        //.allowedOrigins(allowedOrigin)
                       // .allowedOriginPatterns(allowedOriginPattern)
                        .allowedOrigins(allowedOriginPattern)
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .maxAge(3600)
                        .allowCredentials(true);
                //exposedHeaders("*");
                 config.setBasePath("/");
            }
        };
    }
}