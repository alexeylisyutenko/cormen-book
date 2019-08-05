package ru.alexeylisyutenko.helper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimesTest {

    @Test
    void isPrimeShouldWorkProperly() {
        assertFalse(Primes.isPrime(4));
        assertFalse(Primes.isPrime(100));
        assertTrue(Primes.isPrime(701));
        assertTrue(Primes.isPrime(11));
    }

    @Test
    void smallestPrimeGreaterThanShouldWorkProperly() {
        assertEquals(5, Primes.smallestPrimeGreaterThan(4));
        assertEquals(11, Primes.smallestPrimeGreaterThan(11));
        assertEquals(13, Primes.smallestPrimeGreaterThan(12));
        assertEquals(701, Primes.smallestPrimeGreaterThan(700));
    }

}