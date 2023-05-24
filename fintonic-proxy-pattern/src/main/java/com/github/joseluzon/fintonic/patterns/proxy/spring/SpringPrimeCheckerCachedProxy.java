package com.github.joseluzon.fintonic.patterns.proxy.spring;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import com.github.joseluzon.fintonic.patterns.proxy.PrimeChecker;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SpringPrimeCheckerCachedProxy implements PrimeChecker {

    private final SpringPrimeCheckerLogProxy primeChecker;

    @Override
    @Cacheable("primes")
    public boolean isPrime(int n) {
        return primeChecker.isPrime(n);
    }
    
}
