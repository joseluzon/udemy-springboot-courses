package com.github.joseluzon.udemy.springframework5.dependencyinjection.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Override
    @Nullable
    public Object postProcessBeforeInitialization(final Object bean, final String beanName)
            throws BeansException {
        if (bean instanceof LifeCycleBean) {
            log.info("MyBeanPostProcessor : postProcessBeforeInitialization {}", beanName);
        }
        return bean;
    }

    @Override
    @Nullable
    public Object postProcessAfterInitialization(final Object bean, final String beanName)
            throws BeansException {
        if (bean instanceof LifeCycleBean) {
            log.info("MyBeanPostProcessor : postProcessAfterInitialization {}", beanName);
        }
        return bean;
    }
    
}
