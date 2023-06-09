package com.github.joseluzon.udemy.springboot.automationtesting.microservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;

@Configuration
public class FakerBeanConfig {

    @Bean
    public Faker getFaker() {
        return new Faker();
    }
}
