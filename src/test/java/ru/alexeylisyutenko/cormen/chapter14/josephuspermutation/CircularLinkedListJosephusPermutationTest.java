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
        int[] permutation = josephusPermutation.generate(41, 3);
        System.out.println(Arrays.toString(permutation));
    }

    @Test
    void generatePermutation() {
        assertArrayEquals(new int[]{3, 6, 2, 7, 5, 1, 4}, josephusPermutation.generate(7, 3));
        assertArrayEquals(new int[]{2, 4, 3, 1}, josephusPermutation.generate(4, 2));
    }
}