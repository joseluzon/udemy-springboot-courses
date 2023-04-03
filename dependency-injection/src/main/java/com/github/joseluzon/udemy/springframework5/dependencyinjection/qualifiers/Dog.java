package com.github.joseluzon.udemy.springframework5.dependencyinjection.qualifiers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Dog extends Animal {

    public Dog(@Value("Thor") final String name, @Value("8") final Integer age) {
        super(name, age);
    }    
    
}
