package com.github.joseluzon.udemy.springframework5.dependencyinjection.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import com.github.joseluzon.udemy.springframework5.dependencyinjection.lifecycle.ExplicitBean;

@Configuration
public class Beans {

    @Bean
    public String getAppName() {
        return "UDEMY - Spring Framework 5 + REST - DI";
    }
    
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public ExplicitBean getExplicitBean() {
        return new ExplicitBean();
    }
}
