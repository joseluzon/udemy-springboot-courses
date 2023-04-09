package com.github.joseluzon.udemy.springframework5.core.aop;

import org.aspectj.lang.annotation.Pointcut;

public class MyPointcut {
    
    //@Pointcut("execution(* com.github.joseluzon.udemy.springframework5.core.aop.TargetObject.*(..))")
    //@Pointcut("within(TargetObject)")
    @Pointcut("@annotation(MyAnnotation)")
    public void targetObjectMethods() {

    }

}
