package com.github.joseluzon.udemy.springframework5.core.di.setters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Component(value = "CarBySetters")
public class Car {
    private String brand;
    private Integer model;
    private Motor motor;

    @Value("Toyota")
    public void setBrand(final String brand) {
        this.brand = brand;
    }

    @Value("1990")
    public void setModel(final Integer model) {
        this.model = model;
    }

    @Autowired
    @Qualifier("MotorBySetters") 
    public void setMotor(final Motor motor) {
        this.motor = motor;
    }    
}
