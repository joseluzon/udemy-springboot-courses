package com.github.joseluzon.udemy.springframework5.core.aop;

import java.lang.reflect.Modifier;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
@Order(0)
public class MyAspect {
    
    @Before("MyPointcut.targetObjectMethods()")
    public void before(final JoinPoint joinPoint) {
        log.info("" + getClass().getSimpleName() + " joinPoint object type : " + joinPoint.getSignature().getDeclaringTypeName());
        log.info("" + getClass().getSimpleName() + " joinPoint method name : " + joinPoint.getSignature().getName());
        log.info("" + getClass().getSimpleName() + " joinPoint method is public : " + Modifier.isPublic(joinPoint.getSignature().getModifiers()));
        log.info("" + getClass().getSimpleName() + " joinPoint arguments : " + joinPoint.getArgs());
        log.info("" + getClass().getSimpleName() + " : before advice");
    }
}
