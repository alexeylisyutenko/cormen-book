package ru.alexeylisyutenko.cormen.chapter2;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MergeSortWithInsertionSortBaseCaseTest {

    @Test
    void mergeSortTestMultipleTestsRandomArrays() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(0, 500);
            int[] array = Helpers.randomPositiveIntArray(size, 1000);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            MergeSortWithInsertionSortBaseCase.sort(array);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

    @Test
    void mergeSortWithEmptyArrayShouldWorkProperly() {
        int[] array = new int[0];
        MergeSortWithInsertionSortBaseCase.sort(array);
    }

    @Test
    void mergeSortWithOneElementArrayShouldWorkProperly() {
        int[] array = {1};
        MergeSortWithInsertionSortBaseCase.sort(array);
        assertArrayEquals(new int[]{1}, array);
    }

}