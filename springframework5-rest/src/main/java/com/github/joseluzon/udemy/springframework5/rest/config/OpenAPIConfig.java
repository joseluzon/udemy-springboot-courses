package com.github.joseluzon.udemy.springframework5.rest.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfig {
    
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .packagesToScan("com.github.joseluzon.udemy.springframework5.rest.controllers")
                .group("udemy-springframework5-public")
                //.pathsToMatch("/public/**")
                .build();
    }
    
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("udemy - Spring Framework 5 + REST - API")
                    .description("udemy Spring Framework 5 + REST course")
                    .version("v0.0.1")
                    .license(new License().name("GPLv3").url("https://www.gnu.org/licenses/gpl-3.0.html"))
                    .contact(new Contact().name("joseluzon").url("https://github.com/joseluzon")));
                // .externalDocs(new ExternalDocumentation()
                // .description("SpringShop Wiki Documentation")
                // .url("https://springshop.wiki.github.org/docs"));
    }
}
