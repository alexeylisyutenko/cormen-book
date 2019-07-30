package ru.alexeylisyutenko.cormen.chapter11.other;

import org.junit.jupiter.api.Test;

/**
 * Cormen exercise 11.3-4
 */
class MultiplicationMethodHashCalculations {

    @Test
    void exerciseCalculations() {
        System.out.println("h(61)=" + calculateHash(61));
        System.out.println("h(62)=" + calculateHash(62));
        System.out.println("h(63)=" + calculateHash(63));
        System.out.println("h(64)=" + calculateHash(64));
        System.out.println("h(65)=" + calculateHash(65));
    }

    private int calculateHash(int key) {
        int m = 1000;
        double A = (Math.sqrt(5) - 1) / 2;

        double kA = (key * A) - Math.floor(key * A);
        int hash = (int)Math.floor(m * kA);
        return hash;
    }

}