package com.github.joseluzon.fintonic.patterns.proxy.spring;

import org.springframework.stereotype.Component;
import com.github.joseluzon.fintonic.patterns.proxy.PrimeChecker;
import com.github.joseluzon.fintonic.patterns.proxy.service.PrimeCheckerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class SpringPrimeCheckerLogProxy implements PrimeChecker{

    private final PrimeCheckerService primeChecker;

    @Override
    public boolean isPrime(int n) {
        log.info("request isPrime for {}", n);
        final var result = primeChecker.isPrime(n);
        log.info("request for {} done! = {}", n, result);
        return result;
    }
}
