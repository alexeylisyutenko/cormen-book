package ru.alexeylisyutenko.cormen.chapter8;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static ru.alexeylisyutenko.helper.Helpers.randomDoubleArray;

class BucketSortTest {

    @Test
    void bucketSortDemo() {
        double[] array = randomDoubleArray(2000, 3);
        System.out.println(Arrays.toString(array));
        BucketSort.sort(array);
        System.out.println(Arrays.toString(array));
    }

    @Test
    void bucketSortMultipleTestsRandomArrays() {
        for (int i = 0; i < 100; i++) {
            int size = RandomUtils.nextInt(0, 1000000);
            double[] array = randomDoubleArray(size);
            double[] arrayCopy = Arrays.copyOf(array, array.length);

            BucketSort.sort(array);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }
    }

}