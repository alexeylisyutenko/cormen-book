package ru.alexeylisyutenko.cormen.chapter8;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter7.QuickSort;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CountingSortInPlaceTest {

    @Test
    void countingSortInPlaceDemo() {
        int[] array = {2, 5, 3, 0, 2, 3, 0, 3};
        System.out.println(Arrays.toString(array));

        CountingSortInPlace.sort(array, 5);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void countingSortMultipleTestsRandomArrays() {
        int k = 10;
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 20);
            int[] array = Helpers.randomPositiveIntArray(size, k);

            System.out.println(Arrays.toString(array));
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            CountingSortInPlace.sort(array, k);
            System.out.println(Arrays.toString(array));

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

}