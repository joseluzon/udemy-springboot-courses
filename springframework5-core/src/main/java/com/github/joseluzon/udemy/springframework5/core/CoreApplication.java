package com.github.joseluzon.udemy.springframework5.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import com.github.joseluzon.udemy.springframework5.core.aop.TargetObject;
import com.github.joseluzon.udemy.springframework5.core.lifecycle.LifeCycleBean;
import com.github.joseluzon.udemy.springframework5.core.di.lists.CalculatorService;
import com.github.joseluzon.udemy.springframework5.core.profiles.EnvironmentService;
import com.github.joseluzon.udemy.springframework5.core.di.qualifiers.Animal;
import com.github.joseluzon.udemy.springframework5.core.di.qualifiers.Bird;
import com.github.joseluzon.udemy.springframework5.core.di.qualifiers.Dog;
import com.github.joseluzon.udemy.springframework5.core.di.qualifiers.Nest;
import com.github.joseluzon.udemy.springframework5.core.di.qualifiers.Plane;
import com.github.joseluzon.udemy.springframework5.core.di.scopes.ScopeService;
import com.github.joseluzon.udemy.springframework5.core.di.scopes.ScopeServicePrototype;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class CoreApplication {

    public static void main(String[] args) {
        final var context = SpringApplication.run(CoreApplication.class, args);
        log.info("----- DI by Attribute ------");
        final var carAtt = context.getBean("CarByAttribute",
                com.github.joseluzon.udemy.springframework5.core.di.attribute.Car.class);
        log.info("Car by Attribute : {}", carAtt);
        
        log.info("----- DI by Constructor ------");
        final var carCtor = context.getBean("CarByConstructor",
                com.github.joseluzon.udemy.springframework5.core.di.constructor.Car.class);
        log.info("Car by Constructor : {}", carCtor);
        
        log.info("----- DI by Setters ------");
        final var carSetters = context.getBean("CarBySetters",
                com.github.joseluzon.udemy.springframework5.core.di.setters.Car.class);
        log.info("Car by Setters : {}", carSetters);
        
        log.info("----- Qualifiers ------");
        final var dog = context.getBean(Dog.class);
        log.info("{}", dog);
        final var bird = context.getBean(Bird.class);
        log.info("{}", bird);
        final var plane = context.getBean(Plane.class);
        plane.fly();
        final var animal1 = context.getBean("PioPio", Animal.class);
        log.info("{}", animal1);
        final var animal2 = context.getBean(Animal.class);
        log.info("default {}", animal2);
        final var nest = context.getBean(Nest.class);
        log.info("{}", nest);

        log.info("----- PROFILES ------");
        final var envService = context.getBean(EnvironmentService.class);
        log.info("Environment Service : {}", envService.getEnvironment());

        log.info("----- SCOPES ------");
        final var scopeService1 = context.getBean(ScopeService.class);
        final var scopeService2 = context.getBean(ScopeService.class);
        log.info("Default/Singleton : Are beans equals ? : {}", scopeService1.equals(scopeService2));
        log.info("Default/Singleton : Are beans == ? : {}", scopeService1 == scopeService2);

        final var scopeServicePrototype1 = context.getBean(ScopeServicePrototype.class);
        final var scopeServicePrototype2 = context.getBean(ScopeServicePrototype.class);
        log.info("Prototype : Are beans equals ? : {}", scopeServicePrototype1.equals(scopeServicePrototype2));
        log.info("Prototype : Are beans == ? : {}", scopeServicePrototype1 == scopeServicePrototype2);

        log.info("----- BEANS ------");
        final var appName = context.getBean(String.class);
        log.info("App name : {}", appName);

        log.info("----- Inject List of objects -----");
        final var calculator = context.getBean(CalculatorService.class);
        log.info("Total area : {}", calculator.computeArea());

        log.info("----- SpEL expressions -----");
        final var parser = new SpelExpressionParser();
        final var expression1 = parser.parseExpression("10 + 20");
        final var expression2 = parser.parseExpression("10 < 20");
        log.info("SpEL Expression value1 : {}", expression1.getValue());
        log.info("SpEL Expression value2 : {}", expression2.getValue());

        log.info("----- Lifecycle -----");
        final var lifeCycleBean = context.getBean(LifeCycleBean.class);

        log.info("----- AOP -----");
        final var targetObject = context.getBean(TargetObject.class);
        targetObject.hello("Hello AOP");
    }

}
