package com.mak.inventoryservice.config.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.PathSelectors.regex;

//@Profile("swagger-enabled-for-qa")
@Profile("test")
@Configuration
@EnableSwagger2
@EnableWebMvc
public class SwaggerConfig {

    public static final Contact DEFAULT_CONTACT = new Contact(
            "Sample App", "http://www.sample.com", "sample@gmail.com");

    public static final ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Awesome API Title", "Awesome API Description", "1.0",
            "urn:tos", DEFAULT_CONTACT,
            "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0", Arrays.asList());

    private static final Set<String> DEFAULT_PRODUCES_AND_CONSUMES =
            new HashSet<String>(Arrays.asList("application/json",
                    "application/xml", "*/*"));

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                /* .groupName("public-api")*/
                .apiInfo(DEFAULT_API_INFO)
                // .apiInfo(apiInfo())
                .produces(DEFAULT_PRODUCES_AND_CONSUMES)
                .consumes(DEFAULT_PRODUCES_AND_CONSUMES)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(postPaths())
                .build();
    }

    private Predicate<String> postPaths() {
        return PathSelectors.any();
      /*  return regex("/api/custom.*").or(
                regex("/api/custom/product.*"));*/
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Inventory API")
                .description("Inventory API reference for developers")
                .termsOfServiceUrl("http://inventory-service.com")
                .contact(new Contact("sedokray", "http://inventory-service.com", "sedokray@gmail.com")).license("Inventory License")
                .licenseUrl("sedokray@gmail.com").version("1.0").build();
    }


    /* Get WebMvcConfigurer for Swagger UI */
    @Bean
    public WebMvcConfigurer getSwaggerUiWebMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                String baseUrl = StringUtils.trimTrailingCharacter("", '/');
                registry.
                        addResourceHandler(baseUrl + "/swagger-ui/**")
                        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
                        .resourceChain(false);
            }

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("" + "/swagger-ui/")
                        .setViewName("forward:" + "" + "/swagger-ui/index.html");
            }
        };
    }


}