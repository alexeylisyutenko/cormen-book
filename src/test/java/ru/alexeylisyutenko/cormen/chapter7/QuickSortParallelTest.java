package ru.alexeylisyutenko.cormen.chapter7;

import com.google.common.collect.Ordering;
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


}