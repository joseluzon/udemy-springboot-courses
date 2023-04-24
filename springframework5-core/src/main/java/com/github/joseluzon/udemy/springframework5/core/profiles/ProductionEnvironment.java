package com.github.joseluzon.udemy.springframework5.core.profiles;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("production")
public class ProductionEnvironment implements EnvironmentService {

    @Override
    public String getEnvironment() {
        return "Production";
    }
    
}
