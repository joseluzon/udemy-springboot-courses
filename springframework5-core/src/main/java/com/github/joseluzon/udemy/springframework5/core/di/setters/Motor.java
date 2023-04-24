package com.github.joseluzon.udemy.springframework5.core.di.setters;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@Component(value = "MotorBySetters")
public class Motor {
    private String brand;
    private Integer model;

    @Value("Yaris") 
    public void setBrand(final String brand) {
        this.brand = brand;
    }

    @Value("1990")
    public void setModel(final Integer model) {
        this.model = model;
    }

    
}
