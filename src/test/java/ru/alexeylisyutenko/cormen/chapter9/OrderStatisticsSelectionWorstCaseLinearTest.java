package ru.alexeylisyutenko.cormen.chapter9;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderStatisticsSelectionWorstCaseLinearTest {

    @Test
    void selectDemo() {
        int[] array = {18, 56, 5, 56, 56, 11};
//        int[] array = Helpers.sequentialIntArray(15);
//        int[] array = Helpers.randomPositiveIntArray(14, 100);
        System.out.println(Arrays.toString(array));

        int orderStatistics = OrderStatisticsSelectionWorstCaseLinear.select(array, 2);
        System.out.println(orderStatistics);

    }

    @Test
    void orderStatisticSelectionShouldWorkProperly() {
        for (int i = 0; i < 100000; i++) {
            int size = RandomUtils.nextInt(1, 1000);
            int[] originalArray = Helpers.randomPositiveIntArray(size, size * 10);
            int randomIndex = ThreadLocalRandom.current().nextInt(size);

            int[] array = Arrays.copyOf(originalArray, originalArray.length);
            int expected = simpleOrderStatistics(array, randomIndex + 1);

            int ithOrderStatistic = OrderStatisticsSelectionWorstCaseLinear.select(array, randomIndex + 1);

            if (expected != ithOrderStatistic) {
                System.out.println(Arrays.toString(originalArray));
                System.out.println("Index: " + (randomIndex + 1));
            }
            assertEquals(expected, ithOrderStatistic);
        }
    }

    private int simpleOrderStatistics(int[] array, int index) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        Arrays.sort(arrayCopy);
        return arrayCopy[index - 1];
    }

}