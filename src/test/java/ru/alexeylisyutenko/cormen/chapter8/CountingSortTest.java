package ru.alexeylisyutenko.cormen.chapter8;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CountingSortTest {

    @Test
    void countingSortDemo() {
        int[] array = {2, 5, 3, 0, 2, 3, 0, 3};
        System.out.println(Arrays.toString(array));

        int[] result = CountingSort.sort(array, 5);
        System.out.println(Arrays.toString(result));
    }

    @Test
    void countingSortWithRandomArrayDemo() {
        int k = 100000;
        int[] array = Helpers.randomPositiveIntArray(k, k);
        System.out.println(Arrays.toString(array));

        int[] result = CountingSort.sort(array, k);
        System.out.println(Arrays.toString(result));
    }

}