package com.meli.mutant.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(usersApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.meli.mutant.api.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo usersApiInfo() {
        return new ApiInfoBuilder()
                .title("Mutant Service")
                .contact(new Contact("Cristian Robayo", "", "milor.roco@gmail.com"))
                .version("1.0")
                .license("Apache License Version 2.0")
                .build();
    }
}
