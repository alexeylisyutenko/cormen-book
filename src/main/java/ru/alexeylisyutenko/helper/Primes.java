package ru.alexeylisyutenko.helper;

public final class Primes {

    public static boolean isPrime(int number) {
        // Corner cases.
        if (number <= 1) {
            return false;
        }
        if (number <= 3) {
            return true;
        }

        // This is checked so that we can skip
        // middle five numbers in below loop
        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= number; i = i + 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    public static int smallestPrimeGreaterThan(int number) {
        int n = number;
        while (true) {
            if (isPrime(n)) {
                return n;
            }
            n++;
        }
    }

}
