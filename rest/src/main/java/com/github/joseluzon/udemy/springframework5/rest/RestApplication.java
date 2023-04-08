package com.github.joseluzon.udemy.springframework5.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class RestApplication {

    public static void main(String[] args) {
        final var context = SpringApplication.run(RestApplication.class, args);
    }

}
