package com.github.joseluzon.udemy.springframework5.dependencyinjection.lists;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.ToString;

@ToString
@Component
public class Circle implements Figure {
    private double radius;

    public Circle(@Value("${circle.area:2.5}") final double radius) {
        this.radius = radius;
    }

    @Override
    public double computeArea() {
        return Math.PI * radius * radius;
    }
}
