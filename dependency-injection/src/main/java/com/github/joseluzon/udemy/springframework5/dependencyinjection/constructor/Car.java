package com.github.joseluzon.udemy.springframework5.dependencyinjection.constructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.ToString;

@ToString
@Component(value = "CarByConstructor")
public class Car {
    private String brand;
    private Integer model;
    private Motor motor;

    public Car(@Value("Opel") final String brand, @Value("1985") final Integer model,
            @Autowired @Qualifier("MotorByConstuctor") final Motor motor) {
        this.brand = brand;
        this.model = model;
        this.motor = motor;
    }
}
