package ru.alexeylisyutenko.cormen.chapter9;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static ru.alexeylisyutenko.cormen.chapter9.KQuantiles.kQuantiles;
import static ru.alexeylisyutenko.helper.Helpers.ceilToInt;

class KQuantilesTest {

    private static void printQuantilesPositions(int[] array, int k) {
        int n = array.length;
        int medianNumber = k / 2;
        int medianIndex = (int) Math.ceil(medianNumber * (double) n / k);
        System.out.println("Median index: " + medianIndex + ", element: " + array[medianIndex - 1]);
        for (int i = 1; i <= k - 1; i++) {
            int index = (int) Math.ceil(i * (double) n / k);
            System.out.println("Order statistics number: " + index + ", element: " + array[index - 1]);
        }
    }

    private static int[] simpleQuantiles(int[] array, int k) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        Arrays.sort(arrayCopy);
        int[] quantiles = new int[k - 1];
        int n = array.length;
        for (int i = 1; i <= k - 1; i++) {
            int index = ceilToInt(i * (double) n / k);
            quantiles[i - 1] = arrayCopy[index - 1];
        }
        return quantiles;
    }

    @Test
    void quantilesDemo() {
        int[] array = {37, 69, 30, 19, 3, 26, 2, 1};
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        Arrays.sort(arrayCopy);
//        int[] array = {1, 2, 3, 4, 5, 6, 7};
//        int[] array = sequentialIntArray(7);
//        int[] array = Helpers.randomPositiveIntArray(7, 100);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(arrayCopy));

//        int k = 3;
        int k = 5;

        System.out.println();
        System.out.println(Arrays.toString(simpleQuantiles(array, k)));

        System.out.println();
        int[] quantiles = kQuantiles(array, k);
        System.out.println(Arrays.toString(quantiles));
    }

    @Test
    void quantilesShouldWorkProperly() {
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j <= i + 1; j++) {
                int[] array = Helpers.randomPositiveIntArray(i, i * 10);
                int[] simpleQuantiles = simpleQuantiles(array, j);
                int[] quantiles = kQuantiles(Arrays.copyOf(array, array.length), j);

                String message = String.format("size: %d, k: %d, Array: %s, simple: %s, target: %s", i, j, Arrays.toString(array), Arrays.toString(simpleQuantiles), Arrays.toString(quantiles));
                assertArrayEquals(simpleQuantiles, quantiles, message);
            }
        }
    }

}