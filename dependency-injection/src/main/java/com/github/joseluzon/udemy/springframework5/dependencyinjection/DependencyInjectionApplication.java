package com.github.joseluzon.udemy.springframework5.dependencyinjection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.joseluzon.udemy.springframework5.dependencyinjection.attribute.Car;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class DependencyInjectionApplication {

	public static void main(String[] args) {
		final var context = SpringApplication.run(DependencyInjectionApplication.class, args);
		final var car = context.getBean(Car.class);
		log.info("{}", car);
	}

}
