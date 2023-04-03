package com.github.joseluzon.udemy.springframework5.dependencyinjection.attribute;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Motor {
    @Value("xl1")
    private String brand;
    @Value("1982")
    private Integer model;
}
