package ru.alexeylisyutenko.cormen.chapter8;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class CountingSortTest {

    @Test
    void countingSortDemo() {
        int[] array = {2, 5, 3, 0, 2, 3, 0, 3};
        System.out.println(Arrays.toString(array));

        CountingSort.sort(array, 5);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void countingSortWithRandomArrayDemo() {
        int k = 10000;
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = Helpers.randomPositiveIntArray(size, k);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            CountingSort.sort(array, k);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

}