package com.github.joseluzon.udemy.springframework5.dependencyinjection.attribute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Component
public class Car {
    
    private String brand;
    private Integer model;
    private Motor motor;

    @Value("VW")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Value("1981")
    public void setModel(Integer model) {
        this.model = model;
    }

    @Autowired
    public void setMotor(Motor motor) {
        this.motor = motor;
    }


}
