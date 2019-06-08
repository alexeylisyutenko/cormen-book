package ru.alexeylisyutenko.cormen.chapter9;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxTest {

    @Test
    void findMinMaxDemo() {
        int[] array = Helpers.randomPositiveIntArray(10, 100);
        System.out.println(Arrays.toString(array));

        Pair<Integer, Integer> minMax = MinMax.findMinMax(array);
        System.out.println(minMax);
    }

}