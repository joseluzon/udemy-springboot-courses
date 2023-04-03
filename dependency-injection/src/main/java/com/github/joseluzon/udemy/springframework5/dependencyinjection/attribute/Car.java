package com.github.joseluzon.udemy.springframework5.dependencyinjection.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Car {
    @Value("VW")
    private String brand;
    @Value("1981")
    private Integer model;
    @Autowired
    private Motor motor;
}
