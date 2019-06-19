package ru.alexeylisyutenko.cormen.chapter9;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.stream.IntStream;

public class OilTowersProblemTest {

    @Test
    void checkVariousCalculationMethodsMethods() {
        int size = 100;
        int[] yCoordinates = Helpers.randomPositiveIntArray(size, size * 100);
        System.out.println(Arrays.toString(yCoordinates));

        double mean = (double) IntStream.of(yCoordinates).sum() / yCoordinates.length;
        System.out.println("Mean: " + mean);

        int median = OrderStatisticSelection.select(yCoordinates, (size + 1) / 2 - 1);
        System.out.println("Median:" + median);

        System.out.println();

        System.out.println("Total distance mean: " + calculateTotalDistance(yCoordinates, mean));
        System.out.println("Total distance median: " + calculateTotalDistance(yCoordinates, median));
    }

    @Test
    void calculateTimesOneIsBetter() {
        int size = 1000;

        int medianIsBetter = 0;
        int meanIsBetter = 0;
        int bothEqual = 0;

        for (int i = 0; i < 10000; i++) {
            int[] yCoordinates = Helpers.randomPositiveIntArray(size, size * 100);

            double mean = (double) IntStream.of(yCoordinates).sum() / yCoordinates.length;
            int median = OrderStatisticSelection.select(yCoordinates, (size + 1) / 2 - 1);

            BigDecimal meanTotalDistance = calculateTotalDistance(yCoordinates, mean);
            BigDecimal medianTotalDistance = calculateTotalDistance(yCoordinates, median);

            if (meanTotalDistance.equals(medianTotalDistance)) {
                bothEqual++;
            } else if (meanTotalDistance.compareTo(medianTotalDistance) < 0) {
                meanIsBetter++;
            } else {
                medianIsBetter++;
            }
        }

        System.out.println("Median: " + medianIsBetter);
        System.out.println("Mean: " + meanIsBetter);
        System.out.println("Equal: " + bothEqual);
    }

    private static BigDecimal calculateTotalDistance(int[] yCoordinates, double c) {
        BigDecimal totalDistance = BigDecimal.ZERO;
        for (int i = 0; i < yCoordinates.length; i++) {
            BigDecimal currentDistance = BigDecimal.valueOf(Math.abs(c - yCoordinates[i]));
            totalDistance = totalDistance.add(currentDistance);
        }
        return totalDistance.setScale(0, RoundingMode.UP);
    }

}
