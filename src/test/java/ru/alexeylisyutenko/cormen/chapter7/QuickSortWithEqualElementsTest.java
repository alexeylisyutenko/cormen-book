package ru.alexeylisyutenko.cormen.chapter7;

import com.google.common.collect.Ordering;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class QuickSortWithEqualElementsTest {

    @Test
    void partitionDemo() {
        int[] array = {2, 8, 4, 1, 4, 5, 6, 4};
        System.out.println(Arrays.toString(array));

        Pair<Integer, Integer> partitionResult = QuickSortWithEqualElements.partition(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
        System.out.println(partitionResult);
    }

    @Test
    void sortDemo() {
        int[] array = {2, 8, 4, 1, 4, 5, 6, 4};
        System.out.println(Arrays.toString(array));
        QuickSortWithEqualElements.sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void randomArraySortDemo() {
        int[] array = Helpers.randomPositiveIntArray(20, 10);
        System.out.println(Arrays.toString(array));
        QuickSortWithEqualElements.sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void quickSortMultipleTestsRandomArrays() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = Helpers.randomPositiveIntArray(size, 1000);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            QuickSortWithEqualElements.sort(array);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

}