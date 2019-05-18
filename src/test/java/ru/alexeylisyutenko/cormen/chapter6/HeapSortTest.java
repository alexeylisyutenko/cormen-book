package ru.alexeylisyutenko.cormen.chapter6;

import com.google.common.collect.Ordering;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter6.HeapSort;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeapSortTest {

    @Test
    void sortDemo() {
        int[] array = Helpers.randomPositiveIntArray(20, 1000);
        System.out.println(Arrays.toString(array));

        HeapSort.sort(array);
        System.out.println(Arrays.toString(array));

        List<Integer> arrayAsList = IntStream.of(array).boxed().collect(Collectors.toList());
        boolean ordered = Ordering.natural().isOrdered(arrayAsList);
        assertTrue(ordered);
    }

    @Test
    void heapSortMultipleTestsRandomArrays() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = Helpers.randomPositiveIntArray(size, 1000);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            HeapSort.sort(array);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

}