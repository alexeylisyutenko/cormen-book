package ru.alexeylisyutenko.cormen.chapter9;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static ru.alexeylisyutenko.cormen.chapter9.KQuantiles.kQuantiles;

class KQuantilesTest {

    @Test
    void quantilesDemo() {
//        int[] array = {1, 2, 3, 4, 5};
//        int[] array = {37, 69, 30, 19, 3, 26, 2, 1};
//        int[] array = sequentialIntArray(20);
        int[] array = Helpers.randomPositiveIntArray(10000, 100000);
        System.out.println(Arrays.toString(array));

        int[] arrayCopy = Arrays.copyOf(array, array.length);
        Arrays.sort(arrayCopy);
        System.out.println(Arrays.toString(arrayCopy));

        int k = 10;

        System.out.println();
        System.out.println(Arrays.toString(KQuantilesSorting.kQuantiles(array, k)));

        System.out.println();
        int[] quantiles = kQuantiles(array, k);
        System.out.println(Arrays.toString(quantiles));
    }

    @Test
    @Disabled
    void quantilesShouldWorkProperly() {
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j <= i + 1; j++) {
                int[] array = Helpers.randomPositiveIntArray(i, i * 10);
                int[] simpleQuantiles = KQuantilesSorting.kQuantiles(array, j);
                int[] quantiles = kQuantiles(Arrays.copyOf(array, array.length), j);

                String message = String.format("size: %d, k: %d, Array: %s, simple: %s, target: %s", i, j, Arrays.toString(array), Arrays.toString(simpleQuantiles), Arrays.toString(quantiles));
                assertArrayEquals(simpleQuantiles, quantiles, message);
            }
        }
    }

}