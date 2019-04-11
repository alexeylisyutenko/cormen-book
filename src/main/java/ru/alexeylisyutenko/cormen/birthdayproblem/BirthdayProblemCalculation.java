package ru.alexeylisyutenko.cormen.birthdayproblem;

import java.math.BigDecimal;
import java.math.MathContext;

public class BirthdayProblemCalculation {
    public static void main(String[] args) {
//        int k = 1;
//        BigDecimal probability = BigDecimal.ZERO;
//        while (probability.compareTo(BigDecimal.valueOf(0.5)) < 0) {
//            probability = calculateProbabilityForK(k);
//            System.out.println(String.format("Probability for k = %d is %s", k, probability));
//            k++;
//        }
//        System.out.println("K = " + k);

        BigDecimal probability = calculateProbabilityForK(2000);
        System.out.println(probability);
    }

    private static BigDecimal calculateProbabilityForK(int k) {
        int n = 365;
        BigDecimal firstTerm = BigDecimal.valueOf(n - 1).divide(BigDecimal.valueOf(n), MathContext.DECIMAL128)
                .pow(k - 1).multiply(BigDecimal.valueOf(k)).divide(BigDecimal.valueOf(n), MathContext.DECIMAL128);
        BigDecimal secondTerm = BigDecimal.valueOf(n - 1).divide(BigDecimal.valueOf(n), MathContext.DECIMAL128).pow(k);
        return BigDecimal.ONE.subtract(firstTerm).subtract(secondTerm);
    }
}
