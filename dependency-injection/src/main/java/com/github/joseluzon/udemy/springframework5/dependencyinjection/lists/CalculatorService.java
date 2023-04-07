package com.github.joseluzon.udemy.springframework5.dependencyinjection.lists;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    
    private List<Figure> figures;

    public CalculatorService(@Autowired final List<Figure> figures) {
        this.figures = figures;
    }

    public double computeArea() {
        return figures.stream().mapToDouble(Figure::computeArea).sum();
    }
}
