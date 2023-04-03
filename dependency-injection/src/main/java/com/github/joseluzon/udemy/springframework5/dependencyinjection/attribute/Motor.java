package com.github.joseluzon.udemy.springframework5.dependencyinjection.attribute;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class Motor {
    private String brand;
    private Integer model;

    public Motor(@Value("xl1") String brand, @Value("1982") Integer model) {
        this.brand = brand;
        this.model = model;
    }
}
