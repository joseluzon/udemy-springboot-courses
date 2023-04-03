package com.github.joseluzon.udemy.springframework5.dependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.joseluzon.udemy.springframework5.dependencyinjection.qualifiers.Animal;
import com.github.joseluzon.udemy.springframework5.dependencyinjection.qualifiers.Bird;
import com.github.joseluzon.udemy.springframework5.dependencyinjection.qualifiers.Dog;
import com.github.joseluzon.udemy.springframework5.dependencyinjection.qualifiers.Nest;
import com.github.joseluzon.udemy.springframework5.dependencyinjection.qualifiers.Plane;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DependencyInjectionApplication {

    public static void main(String[] args) {
        final var context = SpringApplication.run(DependencyInjectionApplication.class, args);
        final var carAtt = context.getBean("CarByAttribute",
                com.github.joseluzon.udemy.springframework5.dependencyinjection.attribute.Car.class);
        log.info("Car by Attribute : {}", carAtt);
        final var carCtor = context.getBean("CarByConstructor",
                com.github.joseluzon.udemy.springframework5.dependencyinjection.constructor.Car.class);
        log.info("Car by Constructor : {}", carCtor);
        final var carSetters = context.getBean("CarBySetters",
                com.github.joseluzon.udemy.springframework5.dependencyinjection.setters.Car.class);
        log.info("Car by Setters : {}", carSetters);
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
    }

}
