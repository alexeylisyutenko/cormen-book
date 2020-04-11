package ru.alexeylisyutenko.cormen.chapter15.lcs;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.alexeylisyutenko.cormen.chapter15.lcs.LongestMonotonicallyIncreasingSubsequence.*;

class LongestMonotonicallyIncreasingSubsequenceTest {

    @Test
    void lengthOnly() {
        assertEquals(0, lengthOnlyRecursive(new int[]{}));
        assertEquals(1, lengthOnlyRecursive(new int[]{6}));
        assertEquals(2, lengthOnlyRecursive(new int[]{1, 2}));
        assertEquals(1, lengthOnlyRecursive(new int[]{2, 1}));
        assertEquals(3, lengthOnlyRecursive(new int[]{1, 9, 2, 3}));
    }

    @Test
    void lengthOnlyDynamic() {
        assertEquals(0, LongestMonotonicallyIncreasingSubsequence.lengthOnlyDynamic(new int[]{}));
        assertEquals(1, LongestMonotonicallyIncreasingSubsequence.lengthOnlyDynamic(new int[]{6}));
        assertEquals(2, LongestMonotonicallyIncreasingSubsequence.lengthOnlyDynamic(new int[]{1, 2}));
        assertEquals(1, LongestMonotonicallyIncreasingSubsequence.lengthOnlyDynamic(new int[]{2, 1}));
        assertEquals(3, LongestMonotonicallyIncreasingSubsequence.lengthOnlyDynamic(new int[]{1, 9, 2, 3}));
    }

    @RepeatedTest(1000)
    void lengthOnlyDynamicRandomized() {
        int size = RandomUtils.nextInt(0, 50);
        int[] sequence = new int[size];
        for (int i = 0; i < size; i++) {
            sequence[i] = RandomUtils.nextInt(0, 1000);
        }
        assertEquals(lengthOnlyRecursive(sequence), LongestMonotonicallyIncreasingSubsequence.lengthOnlyDynamic(sequence));
    }

    @Test
    void testFindLongestMonotonicallyIncreasingSubsequence() {
       assertArrayEquals(new int[] {}, findLongestMonotonicallyIncreasingSubsequence(new int[]{}));
       assertArrayEquals(new int[] {6}, findLongestMonotonicallyIncreasingSubsequence(new int[]{6}));
       assertArrayEquals(new int[] {1, 2}, findLongestMonotonicallyIncreasingSubsequence(new int[]{1, 2}));
       assertArrayEquals(new int[] {2}, findLongestMonotonicallyIncreasingSubsequence(new int[]{2, 1}));
       assertArrayEquals(new int[] {1, 2, 3}, findLongestMonotonicallyIncreasingSubsequence(new int[]{1, 9, 2, 3}));
    }

    @Test
    void findLongestMonotonicallyIncreasingSubsequenceDemo() {
        int size = RandomUtils.nextInt(0, 10);
        int[] sequence = new int[size];
        for (int i = 0; i < size; i++) {
            sequence[i] = RandomUtils.nextInt(0, 100);
        }
        System.out.println(Arrays.toString(sequence));

        int[] solution = findLongestMonotonicallyIncreasingSubsequence(sequence);
        System.out.println(Arrays.toString(solution));
    }

}