package ru.alexeylisyutenko.cormen.chapter7;

import com.google.common.collect.Ordering;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortParallelTest {

    @Test
    void quickSortShouldWorkProperly() {
        int[] array = Helpers.randomPositiveIntArray(1000000, 10000000);
        System.out.println(Arrays.toString(array));

        QuickSortParallel.sort(array);
        System.out.println(Arrays.toString(array));

        List<Integer> arrayAsList = IntStream.of(array).boxed().collect(Collectors.toList());
        boolean ordered = Ordering.natural().isOrdered(arrayAsList);
        assertTrue(ordered);
    }

    @Test
    void quickSortMultipleTestsRandomArrays() {
        for (int i = 0; i < 100; i++) {
            int size = RandomUtils.nextInt(0, 1000000);
            int[] array = Helpers.randomPositiveIntArray(size, 100000000);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            QuickSortParallel.sort(array);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }


}