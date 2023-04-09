package com.github.joseluzon.udemy.springframework5.core.di.qualifiers;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Plane implements Flyer {

    @Override
    public void fly() {
        log.info("I am a plane, and I am flying");
    }
    
}
