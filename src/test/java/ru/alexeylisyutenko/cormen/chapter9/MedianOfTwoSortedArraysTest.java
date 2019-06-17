package ru.alexeylisyutenko.cormen.chapter9;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.alexeylisyutenko.cormen.chapter9.MedianOfTwoSortedArrays.findMedian;

class MedianOfTwoSortedArraysTest {

    @Test
    void findMedianDemo() {
//        int size = ThreadLocalRandom.current().nextInt(1, 10000);
//        int[] array1 = Helpers.randomPositiveIntArray(size, size * 100);
//        Arrays.sort(array1);
//        int[] array2 = Helpers.randomPositiveIntArray(size, size * 100);
//        Arrays.sort(array2);

        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {1, 4, 6, 8};

//        int[] array1 = {107, 148, 203, 281, 367, 451};
//        int[] array2 = {141, 204, 257, 303, 384, 554};

//        int[] array1 = {99};
//        int[] array2 = {74};


        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));

        int[] mergedArray = mergeArrays(array1, array2);
        System.out.println(Arrays.toString(mergedArray));

        int median = findMedian(array1, array2);
        System.out.println(median);
    }

    @Test
    void findMedianShouldWorkProperly() {
        for (int i = 0; i < 100000; i++) {
            int size = ThreadLocalRandom.current().nextInt(1, 100);
            int[] array1 = Helpers.randomPositiveIntArray(size, size * 100);
            Arrays.sort(array1);
            int[] array2 = Helpers.randomPositiveIntArray(size, size * 100);
            Arrays.sort(array2);


            int[] mergedArray = mergeArrays(array1, array2);
            int expectedMedian = mergedArray[size - 1];

            int median = findMedian(array1, array2);


            String message = String.format("array1: %s, array2: %s, expected: %d, actual: %d", Arrays.toString(array1),
                    Arrays.toString(array2), expectedMedian, median);
            if (expectedMedian != median) {
                System.out.println(Arrays.toString(array1));
                System.out.println(Arrays.toString(array2));
                System.out.println(Arrays.toString(mergedArray));
                System.out.println("Expected: " + expectedMedian);
                System.out.println("Actual: " + median);
            }

            assertEquals(expectedMedian, median, message);
        }
    }

    private int[] mergeArrays(int[] array1, int[] array2) {
        int size = array1.length;
        int[] mergedArray = new int[size * 2];
        System.arraycopy(array1, 0, mergedArray, 0, size);
        System.arraycopy(array2, 0, mergedArray, size, size);
        Arrays.sort(mergedArray);
        return mergedArray;
    }

}