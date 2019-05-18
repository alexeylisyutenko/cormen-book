package ru.alexeylisyutenko.cormen.chapter2;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class MergeSortTest {

    @Test
    void mergeSortDemo() {
        int[] array = Helpers.randomPositiveIntArray(10, 100);
        System.out.println(Arrays.toString(array));

        MergeSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void mergeSortTestMultipleTestsRandomArrays() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = Helpers.randomPositiveIntArray(size, 1000);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            MergeSort.sort(array);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

}