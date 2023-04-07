package com.github.joseluzon.udemy.springframework5.dependencyinjection.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
// @Scope("prototype")
@Lazy(false) // (or none) by default for singletones. Prototypes are @Lazy by default.
public class LifeCycleBean implements BeanNameAware, InitializingBean, DisposableBean {
    
    /**
     * Invoked after DI performed.
     */
    @Override
    public void setBeanName(final String name) {
        log.info("LifeCycleBean Bean name : {}", name);
    }
 
    /**
     * After DI performed.
     * Not invoked for beans of scope 'prototype'
     */
    @PostConstruct
    public void init() {
        log.info("LifeCycleBean : PostConstruct");
    }

    /**
     * Before bean is detroyed.
     * Not invoked for beans of scope 'prototype'
     * Invoked on a JVM contolled shutdown
     */
    @PreDestroy
    public void preDestroy() {
        log.info("LifeCycleBean : PreDestroy");
    }

    /**
     * From InitializingBean
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("LifeCycleBean : afterPropertiesSet");
    }

    /**
     * From DisposableBean
     */
    @Override
    public void destroy() throws Exception {
        log.info("LifeCycleBean : destroy");
    }
}
