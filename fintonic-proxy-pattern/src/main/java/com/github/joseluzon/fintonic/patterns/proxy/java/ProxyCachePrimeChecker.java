package com.github.joseluzon.fintonic.patterns.proxy.java;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.github.joseluzon.fintonic.patterns.proxy.PrimeChecker;
import com.github.joseluzon.fintonic.patterns.proxy.service.PrimeCheckerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyCachePrimeChecker implements PrimeChecker {

    private PrimeChecker realService = null;
    private Map<BigDecimal, Boolean> primesCache = new HashMap<>();

    @Override
    public boolean isPrime(int n) {
        log.info("is {} prime ?", n);
        //lazy initialization
        if (realService == null) {
            realService = new PrimeCheckerService();
        }

        var number = BigDecimal.valueOf(n);
        var result = primesCache.getOrDefault(number, null);
        if (result == null) {
            result = Boolean.valueOf(realService.isPrime(n));
            primesCache.put(number, result);
        }
        log.info("{} is {} prime", n, result.equals(Boolean.FALSE) ? "not" : "");
        return result.booleanValue();
    }

    public static void main(final String args[]) {
        final var primeCheckerService = new ProxyCachePrimeChecker();
        primeCheckerService.isPrime(1299541);
        primeCheckerService.isPrime(1299541);
    }

}
