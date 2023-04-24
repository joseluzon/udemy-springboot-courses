package com.github.joseluzon.udemy.springframework5.core.di.qualifiers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import lombok.ToString;

@Component
@ToString
public class Nest {
    
    private Animal pajaro;

    public Nest(@Autowired @Qualifier("PioPio") final Animal pajaro) {
        this.pajaro = pajaro;
    }
    
}
