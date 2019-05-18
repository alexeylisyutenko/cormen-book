package ru.alexeylisyutenko.cormen.chapter2;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class InsertionSortTest {

    @Test
    public void insertionSortDemo() {
        int[] array = Helpers.randomPositiveIntArray(10, 100);
        System.out.println(Arrays.toString(array));

        InsertionSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void insertionTestMultipleTestsRandomArrays() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = Helpers.randomPositiveIntArray(size, 1000);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            InsertionSort.sort(array);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

}