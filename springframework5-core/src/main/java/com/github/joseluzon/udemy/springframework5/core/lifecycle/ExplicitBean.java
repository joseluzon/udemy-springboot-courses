package com.github.joseluzon.udemy.springframework5.core.lifecycle;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@Slf4j
public class ExplicitBean {
    
    public void init() {
        log.info("ExplicitBean : Init");
    }

    public void destroy() {
        log.info("ExplicitBean : Destroy");
    }
}
