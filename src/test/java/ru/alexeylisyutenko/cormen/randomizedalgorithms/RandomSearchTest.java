package ru.alexeylisyutenko.cormen.randomizedalgorithms;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static ru.alexeylisyutenko.helper.Helpers.randomIntArray;

class RandomSearchTest {

    @Test
    void testRandomSearch() {
        int[] array = randomIntArray(20);
        System.out.println(Arrays.toString(array));

        int elementToSearch = array[RandomUtils.nextInt(0, 20)];
        System.out.println("Searching element: " + elementToSearch);

        Pair<Integer, Long> searchResult = RandomSearch.search(array, elementToSearch);
        System.out.println(searchResult);
    }

    @Test
    void testExpectedValueOfTrials() {
        int iterations = 1000000;
        int arraySize = 50;
        long totalTrials = 0;
        for (int i = 0; i < iterations; i++) {
            int[] array = randomIntArray(arraySize);
            Pair<Integer, Long> searchResult = RandomSearch.search(array, array[RandomUtils.nextInt(0, arraySize)]);
            totalTrials += searchResult.getRight();
        }
        double expectedValue = (double) totalTrials / iterations;
        System.out.println("Expected value: " + expectedValue);
    }

    @Test
    void testExpectedValueOfTrialsWhenThereIsNoSuchElement() {
        int iterations = 1000000;
        int arraySize = 50;
        long totalTrials = 0;
        for (int i = 0; i < iterations; i++) {
            int[] array = randomIntArray(arraySize);
            Pair<Integer, Long> searchResult = RandomSearch.search(array, 5000);
            totalTrials += searchResult.getRight();
        }
        double expectedValue = (double) totalTrials / iterations;
        System.out.println("Expected value: " + expectedValue);
    }

}