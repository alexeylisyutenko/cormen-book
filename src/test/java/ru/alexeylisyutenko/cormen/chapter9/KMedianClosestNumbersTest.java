package ru.alexeylisyutenko.cormen.chapter9;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static ru.alexeylisyutenko.helper.Helpers.sequentialIntArray;

class KMedianClosestNumbersTest {

    @Test
    void kClosestDemo() {
        int[] array = Helpers.randomPositiveIntArray(10, 100);
        System.out.println(Arrays.toString(array));

        int[] arrayCopy = Arrays.copyOf(array, array.length);
        Arrays.sort(arrayCopy);
        System.out.println(Arrays.toString(arrayCopy));

        int[] closest = KMedianClosestNumbers.kClosest(array, 4);
        System.out.println(Arrays.toString(closest));
    }

}