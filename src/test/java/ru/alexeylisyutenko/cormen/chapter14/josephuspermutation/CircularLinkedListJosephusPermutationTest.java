package ru.alexeylisyutenko.cormen.chapter14.josephuspermutation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CircularLinkedListJosephusPermutationTest {

    private JosephusPermutation josephusPermutation;

    @BeforeEach
    void setup() {
        josephusPermutation = new CircularLinkedListJosephusPermutation();
    }

    @Disabled
    @Test
    void generatePermutationDemo() {
        int[] permutation = josephusPermutation.generate(41, 2);
        System.out.println(Arrays.toString(permutation));
    }

    @Test
    void generatePermutation() {
        assertArrayEquals(new int[]{1}, josephusPermutation.generate(1, 1));
        assertArrayEquals(new int[]{1}, josephusPermutation.generate(1, 2));

        assertArrayEquals(new int[]{1, 2}, josephusPermutation.generate(2, 1));
        assertArrayEquals(new int[]{2, 1}, josephusPermutation.generate(2, 2));

        assertArrayEquals(new int[]{1, 2, 3}, josephusPermutation.generate(3, 1));
        assertArrayEquals(new int[]{2, 1, 3}, josephusPermutation.generate(3, 2));
        assertArrayEquals(new int[]{3, 1, 2}, josephusPermutation.generate(3, 3));
        assertArrayEquals(new int[]{1, 3, 2}, josephusPermutation.generate(3, 4));
        assertArrayEquals(new int[]{2, 3, 1}, josephusPermutation.generate(3, 5));
        assertArrayEquals(new int[]{3, 2, 1}, josephusPermutation.generate(3, 6));
        assertArrayEquals(new int[]{1, 2, 3}, josephusPermutation.generate(3, 7));

        assertArrayEquals(new int[]{2, 4, 3, 1}, josephusPermutation.generate(4, 2));
        assertArrayEquals(new int[]{3, 6, 2, 7, 5, 1, 4}, josephusPermutation.generate(7, 3));
        assertArrayEquals(new int[]{3, 7, 6, 2, 4, 1, 5}, josephusPermutation.generate(7, 10));

        assertArrayEquals(new int[]{2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 1, 5,
                9, 13, 17, 21, 25, 29, 33, 37, 41, 7, 15, 23, 31, 39, 11, 27, 3, 35, 19}, josephusPermutation.generate(41, 2));
    }
}