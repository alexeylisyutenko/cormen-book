package ru.alexeylisyutenko.cormen.chapter7;

import com.google.common.collect.Ordering;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class QuickSortTest {

    @Test
    void quickSortShouldWorkProperly() {
        int[] array = Helpers.randomPositiveIntArray(20, 100);
        System.out.println(Arrays.toString(array));

        QuickSort.sort(array);
        System.out.println(Arrays.toString(array));

        List<Integer> arrayAsList = IntStream.of(array).boxed().collect(Collectors.toList());
        boolean ordered = Ordering.natural().isOrdered(arrayAsList);
        assertTrue(ordered);
    }

    @Test
    void quickSortMultipleTestsRandomArrays() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = Helpers.randomPositiveIntArray(size, 1000);

            QuickSort.sort(array);

            List<Integer> arrayAsList = IntStream.of(array).boxed().collect(Collectors.toList());
            boolean ordered = Ordering.natural().isOrdered(arrayAsList);
            assertTrue(ordered);
        }
    }

    @Test
    void quickSortOfEmptyArrayShouldWorkProperly() {
        QuickSort.sort(new int[0]);
    }

    @Test
    void quickSortWithSingleElementArrayShouldWorkProperly() {
        QuickSort.sort(new int[]{1});
    }

}