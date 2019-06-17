package ru.alexeylisyutenko.cormen.chapter9;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alexeylisyutenko.cormen.chapter9.MedianOfTwoSortedArrays.findMedian;

class MedianOfTwoSortedArraysTest {

    @Test
    void findMedianDemo() {
        int[] array1 = {1, 3, 5, 7};
        int[] array2 = {1, 4, 6, 8};

//        int[] array1 = {77, 157, 195, 326};
//        int[] array2 = {59, 80, 132, 169};

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));

        int median = findMedian(array1, array2);
        System.out.println(median);
    }

    @Test
    void findMedianShouldWorkProperly() {
        int size = 2;
        int[] array1 = Helpers.randomPositiveIntArray(size, size * 100);
        Arrays.sort(array1);
        int[] array2 = Helpers.randomPositiveIntArray(size, size * 100);
        Arrays.sort(array2);

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));

        int[] mergedArray = new int[size * 2];
        System.arraycopy(array1, 0, mergedArray, 0, size);
        System.arraycopy(array2, 0, mergedArray, size, size);
        Arrays.sort(mergedArray);
        int expectedMedian = mergedArray[size - 1];

        System.out.println("Exected median: " + expectedMedian);

        int median = findMedian(array1, array2);
        assertEquals(expectedMedian, median);
    }

}