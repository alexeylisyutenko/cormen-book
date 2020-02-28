package ru.alexeylisyutenko.cormen.chapter14.josephuspermutation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OrderStatisticTreeJosephusPermutationTest {
    private JosephusPermutation josephusPermutation;

    @BeforeEach
    void setup() {
        josephusPermutation = new OrderStatisticTreeJosephusPermutation();
    }

    @Disabled
    @Test
    void generatePermutationDemo() {
        int[] permutation = josephusPermutation.generate(3, 3);
        System.out.println(Arrays.toString(permutation));
    }
}