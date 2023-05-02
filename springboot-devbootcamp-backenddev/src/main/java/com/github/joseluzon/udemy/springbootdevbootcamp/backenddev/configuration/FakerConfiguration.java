package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.javafaker.Faker;

@Configuration
public class FakerConfiguration {    
    @Bean
    public Faker faker() {
        return new Faker();
    }
}
