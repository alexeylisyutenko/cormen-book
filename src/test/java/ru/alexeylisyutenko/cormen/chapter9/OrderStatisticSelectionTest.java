package ru.alexeylisyutenko.cormen.chapter9;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStatisticSelectionTest {

    @Test
    void orderStatisticSelectionDemo() {
//        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] array = Helpers.randomPositiveIntArray(10000000, 100000000);
        System.out.println(Arrays.toString(array));

        System.out.println(OrderStatisticSelection.select(array, 1));
    }

    @Test
    void orderStatisticSelectionShouldWorkProperly() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(1, 500);
            int[] array = Helpers.randomPositiveIntArray(size, 1000);
            int[] arrayCopy = Arrays.copyOf(array, array.length);

            int randomIndex = ThreadLocalRandom.current().nextInt(size);
            int ithOrderStatistic = OrderStatisticSelection.select(array, randomIndex + 1);

            Arrays.sort(arrayCopy);
            assertEquals(arrayCopy[randomIndex], ithOrderStatistic);
        }
    }


}