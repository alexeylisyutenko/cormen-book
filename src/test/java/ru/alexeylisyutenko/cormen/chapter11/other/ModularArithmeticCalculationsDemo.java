package ru.alexeylisyutenko.cormen.chapter11.other;

import org.junit.jupiter.api.Test;

public class ModularArithmeticCalculationsDemo {

    @Test
    void calculateTwoCharacterHashByDivisionMethod() {
        int s0 = 112;
        int s1 = 116;
        int m = 15;

        System.out.println(simpleMethodHash(s0, s1, m));
        System.out.println(sequentialMethod(s0, s1, m));

        System.out.println(stringSequentialHash("pt", m));
    }

    int stringSequentialHash(String str, int m) {
        int sum = 0;
        for (int i = 0; i < str.length(); i++) {
            sum = (sum * 128 + str.charAt(i)) % m;
        }
        return sum;
    }

    int simpleMethodHash(int s0, int s1, int m) {
        return (s0 * 128 + s1) % m;
    }

    int sequentialMethod(int s0, int s1, int m) {
        return ((s0 % m) * 128 + s1) % m;
    }

}
