package com.github.joseluzon.udemy.springframework5.dependencyinjection.constructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component(value = "MotorByConstuctor")
public class Motor {
    private String brand;
    private Integer model;

    public Motor(@Value("Kaddett") final String brand, @Value("1985") final Integer model) {
        this.brand = brand;
        this.model = model;
    }
}
