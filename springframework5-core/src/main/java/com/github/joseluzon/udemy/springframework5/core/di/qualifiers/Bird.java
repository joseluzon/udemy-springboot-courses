package com.github.joseluzon.udemy.springframework5.core.di.qualifiers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = "PioPio")
public class Bird extends Animal implements Flyer {

    public Bird(@Value("PioPio") final String name, @Value("4") final Integer age) {
        super(name, age);
    }

    @Override
    public void fly() {
        log.info("My name is {}, I am {} years old, and I am flying", getName(), getAge());
    }
    
}
