package com.github.joseluzon.fintonic.patterns.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableCaching
@Slf4j
public class ProxyPatternApplication {

    public static void main(String[] args) {
        final var context = SpringApplication.run(ProxyPatternApplication.class, args);
        final var primeChecker = context.getBean("springPrimeCheckerCachedProxy", PrimeChecker.class);
        final var number = 1299541;
        log.info("is {} prime ?", number);
        var result = primeChecker.isPrime(number);
        log.info("{} is {} prime", number, result ? "" : "not");
        log.info("is {} prime ?", number);
        result = primeChecker.isPrime(number);
        log.info("{} is {} prime", number, result ? "" : "not");
    }

}
