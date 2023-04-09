package com.github.joseluzon.udemy.springframework5.core.aop;

import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TargetObject {
    
    @MyAnnotation
    public void hello(final String message) {
        log.info("" + getClass().getSimpleName() + " : " + message);
    }

    protected void foo() {
        log.info("" + getClass().getSimpleName() + " : foo");
    }
}
