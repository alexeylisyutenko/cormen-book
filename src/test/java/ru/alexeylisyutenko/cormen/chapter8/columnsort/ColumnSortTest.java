package ru.alexeylisyutenko.cormen.chapter8.columnsort;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ColumnSortTest {

    @Test
    void columnSortDemo() {
        int s = 3;
        int r = 2 * s * s;

        int[] array = Helpers.randomPositiveIntArray(r * s, 1000);
        System.out.println(Arrays.toString(array));

        ColumnSort.sort(array, r, s, true);

        System.out.println(Arrays.toString(array));
    }

    @Test
    void columnSortShouldWorkProperly() {
        for (int i = 0; i < 1000; i++) {
            int s = RandomUtils.nextInt(1, 10);
            int r = 2 * s * s;

            int[] array = Helpers.randomPositiveIntArray(s * r, s * r * 10);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            ColumnSort.sort(array, r, s, false);

            Arrays.sort(arrayCopy);
            assertArrayEquals(arrayCopy, array);
        }

    }

}