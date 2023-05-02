package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.configuration;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenAPIConfiguration {

    // @Bean
    // public GroupedOpenApi publicApi() {
    //     return GroupedOpenApi.builder()
    //             .packagesToScan("com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.controllers")
    //             .group("udemy-springbootdevbootcamp-public")
    //             // .pathsToMatch("/public/**")
    //             .build();
    // }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(
                    new Info().title("Backend Module - Contacts API")
                        .description("Udemy Course - 'The Complete Spring Boot Development Bootcamp'")
                        .version("v0.0.1")
                        .license(new License().name("GPLv3")
                            .url("https://www.gnu.org/licenses/gpl-3.0.html"))
                        .contact(new Contact()
                            .name("joseluzon").url("https://github.com/joseluzon")));
        // .externalDocs(new ExternalDocumentation()
        // .description("SpringShop Wiki Documentation")
        // .url("https://springshop.wiki.github.org/docs"));
    }
}
