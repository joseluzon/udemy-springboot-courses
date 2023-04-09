package com.github.joseluzon.udemy.springframework5.core.di.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component(value = "CarByAttribute")
public class Car {
    @Value("VW")
    private String brand;
    @Value("1980")
    private Integer model;
    @Autowired
    private Motor motor;
}
