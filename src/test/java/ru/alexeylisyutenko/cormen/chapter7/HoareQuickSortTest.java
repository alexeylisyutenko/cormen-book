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

class HoareQuickSortTest {

    @Test
    void hoarePartitionDemo() {
        int[] array = {13, 19, 9, 5, 12, 8, 7, 4, 11, 2, 6, 21};
        System.out.println(Arrays.toString(array));

        int partitionIndex = HoareQuickSort.partition(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
        System.out.println(partitionIndex);
    }

    @Test
    void hoareQuickSortShouldWorkProperly() {
        int[] array = Helpers.randomPositiveIntArray(20, 100);
        System.out.println(Arrays.toString(array));

        HoareQuickSort.sort(array);
        System.out.println(Arrays.toString(array));

        List<Integer> arrayAsList = IntStream.of(array).boxed().collect(Collectors.toList());
        boolean ordered = Ordering.natural().isOrdered(arrayAsList);
        assertTrue(ordered);
    }

    @Test
    void hoareQuickSortMultipleTestsRandomArrays() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = Helpers.randomPositiveIntArray(size, 1000);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            HoareQuickSort.sort(array);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

}