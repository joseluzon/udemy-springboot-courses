package com.github.joseluzon.udemy.springframework5.core.di.lists;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.ToString;

@ToString
@Component
public class Square implements Figure {

    private double side;

    public Square(@Value("${square.area:2.5}") final double side) {
        this.side = side;
    }

    @Override
    public double computeArea() {
        return side * side;
    }
}
