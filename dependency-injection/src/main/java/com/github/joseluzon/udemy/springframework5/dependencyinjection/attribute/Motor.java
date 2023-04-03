package com.github.joseluzon.udemy.springframework5.dependencyinjection.attribute;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component(value = "MotorByAttribute")
public class Motor {
    @Value("golf")
    private String brand;
    @Value("1980")
    private Integer model;
}
