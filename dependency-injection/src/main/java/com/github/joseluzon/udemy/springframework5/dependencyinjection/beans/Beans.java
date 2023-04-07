package com.github.joseluzon.udemy.springframework5.dependencyinjection.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public String getAppName() {
        return "UDEMY - Spring Framework 5 + REST - DI";
    }
    
}
