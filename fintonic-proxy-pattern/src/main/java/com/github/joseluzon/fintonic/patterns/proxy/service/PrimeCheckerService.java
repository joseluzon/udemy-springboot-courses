package com.github.joseluzon.fintonic.patterns.proxy.service;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.github.joseluzon.fintonic.patterns.proxy.PrimeChecker;

@Service
public class PrimeCheckerService implements PrimeChecker {

    private static final BigDecimal ONE = new BigDecimal("1");
    private static final BigDecimal TWO = new BigDecimal("2");
    private static final BigDecimal THREE = new BigDecimal("3");

    @Override
    public boolean isPrime(int n) {
        var number = BigDecimal.valueOf(n);
        if (number.compareTo(ONE) <= 0) {
            return false;
        }
        if (number.compareTo(THREE) <= 0) {
            return true;
        }
        if (number.remainder(TWO).equals(BigDecimal.ZERO)) {
            return false;
        }
        for (BigDecimal divisor = new BigDecimal("3"); divisor.compareTo(number.divide(TWO)) < 0; divisor = divisor.add(TWO)) {
            if (number.remainder(divisor).equals(BigDecimal.ZERO)) {
                return false;
            }
        }
        return true;
    }

}
