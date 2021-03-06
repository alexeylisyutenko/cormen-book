package ru.alexeylisyutenko.cormen.chapter8;

import com.google.common.collect.Ordering;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alexeylisyutenko.helper.Helpers.randomPositiveIntArray;

class RadixSortTest {

    @Test
    void radixSortDemo() {
        int[] array = {329, 457, 657, 839, 436, 720, 355};
        System.out.println(Arrays.toString(array));

        RadixSort.sort(array, 3);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void radixSortWithRandomArrayDemo() {
        int digits = 9;
        int[] array = randomPositiveIntArray(100, (int) Math.pow(10, digits) - 1);
        System.out.println(Arrays.toString(array));

        RadixSort.sort(array, digits);
        System.out.println(Arrays.toString(array));

        List<Integer> arrayAsList = IntStream.of(array).boxed().collect(Collectors.toList());
        boolean ordered = Ordering.natural().isOrdered(arrayAsList);
        assertTrue(ordered);
    }

    @Test
    void radixSortMultipleTestsRandomArrays() {
        int digits = 9;
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = randomPositiveIntArray(size, (int) Math.pow(10, digits) - 1);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            RadixSort.sort(array, digits);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

    @Test
    void getNthDigitShouldWorkProperly() {
        assertEquals(3, RadixSort.getNthDigit(123, 1));
        assertEquals(2, RadixSort.getNthDigit(123, 2));
        assertEquals(1, RadixSort.getNthDigit(123, 3));
        assertEquals(0, RadixSort.getNthDigit(123, 4));
    }

}