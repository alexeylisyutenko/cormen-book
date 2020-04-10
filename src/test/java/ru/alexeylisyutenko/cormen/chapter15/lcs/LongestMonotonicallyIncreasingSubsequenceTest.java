package ru.alexeylisyutenko.cormen.chapter15.lcs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestMonotonicallyIncreasingSubsequenceTest {

    @Test
    void printSubsetDemo() {
        LongestMonotonicallyIncreasingSubsequence.printSubset(new int[]{1, 9, 2, 3}, 0, "");
        System.out.println();
        System.out.println();
        LongestMonotonicallyIncreasingSubsequence.printSubsetWithLowerBound(new int[]{1, 9, 2, 3}, 0, "", Integer.MIN_VALUE);
        System.out.println();
        System.out.println();
        LongestMonotonicallyIncreasingSubsequence.printSubsetWithLowerBound(new int[]{1, 2}, 0, "", Integer.MIN_VALUE);
    }

    @Test
    void lengthOnly() {
        assertEquals(0, LongestMonotonicallyIncreasingSubsequence.lengthOnlyRecursive(new int[]{}));
        assertEquals(1, LongestMonotonicallyIncreasingSubsequence.lengthOnlyRecursive(new int[]{6}));
        assertEquals(2, LongestMonotonicallyIncreasingSubsequence.lengthOnlyRecursive(new int[]{1, 2}));
        assertEquals(1, LongestMonotonicallyIncreasingSubsequence.lengthOnlyRecursive(new int[]{2, 1}));
        assertEquals(3, LongestMonotonicallyIncreasingSubsequence.lengthOnlyRecursive(new int[]{1, 9, 2, 3}));
    }

    @Test
    void lengthOnlyDynamic() {
        System.out.println(LongestMonotonicallyIncreasingSubsequence.lengthOnlyDynamic(new int[]{1, 9, 2, 3}));
    }

}