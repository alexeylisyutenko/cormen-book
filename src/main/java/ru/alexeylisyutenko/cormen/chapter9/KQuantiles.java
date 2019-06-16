package ru.alexeylisyutenko.cormen.chapter9;

import java.util.concurrent.ThreadLocalRandom;

import static ru.alexeylisyutenko.helper.Helpers.*;

@SuppressWarnings("Duplicates")
public class KQuantiles {

    public static int[] kQuantiles(int[] array, int k) {
        if (k < 1 || k > array.length) {
            throw new IllegalArgumentException("Incorrect k value");
        }
        int[] resultingQuantiles = new int[k - 1];
        findKQuantilesTailRecursion(array, 0, array.length - 1, k, resultingQuantiles, 0);
        return resultingQuantiles;
    }

    private static void findKQuantiles(int[] array, int lo, int hi, int k, int[] quantiles, int offset) {
        if (k == 1) {
            return;
        }

        int n = hi - lo + 1;

        int mediumQuantileNumber = k / 2;
        int mediumQuantileIndex = ceilToInt(mediumQuantileNumber * (double) n / k);

        int selectedOrderStatistics = randomizedSelect(array, lo, hi, mediumQuantileIndex);
        quantiles[offset + mediumQuantileNumber - 1] = selectedOrderStatistics;

        int quantilesInLeftPart = floorToInt((double) k / 2);
        if (quantilesInLeftPart > 1) {
            findKQuantiles(array, lo, lo + mediumQuantileIndex - 2, quantilesInLeftPart, quantiles, offset);
        }

        int quantilesInRightPart = ceilToInt((double) k / 2);
        if (quantilesInRightPart > 1) {
            findKQuantiles(array, lo + mediumQuantileIndex, hi, quantilesInRightPart, quantiles, offset + mediumQuantileNumber);
        }
    }

    private static void findKQuantilesTailRecursion(int[] array, int lo, int hi, int k, int[] quantiles, int offset) {
        while (k > 1) {
            int n = hi - lo + 1;

            int mediumQuantileNumber = k / 2;
            int mediumQuantileIndex = ceilToInt(mediumQuantileNumber * (double) n / k);

            int selectedOrderStatistics = randomizedSelect(array, lo, hi, mediumQuantileIndex);
            quantiles[offset + mediumQuantileNumber - 1] = selectedOrderStatistics;

            int quantilesInLeftPart = floorToInt((double) k / 2);
            if (quantilesInLeftPart > 1) {
                findKQuantilesTailRecursion(array, lo, lo + mediumQuantileIndex - 2, quantilesInLeftPart, quantiles, offset);
            }

            int quantilesInRightPart = ceilToInt((double) k / 2);
            lo += mediumQuantileIndex;
            k = quantilesInRightPart;
            offset += mediumQuantileNumber;
        }
    }

    private static int randomizedSelect(int[] array, int lo, int hi, int index) {
        while (true) {
            if (lo == hi) {
                return array[lo];
            }
            int q = randomizedPartition(array, lo, hi);
            int k = q - lo + 1;
            if (k == index) {
                return array[q];
            }
            if (index < k) {
                hi = q - 1;
            } else {
                lo = q + 1;
                index -= k;
            }
        }
    }

    private static int randomizedPartition(int[] array, int lo, int hi) {
        int randomIndex = lo + ThreadLocalRandom.current().nextInt(hi - lo + 1);
        exchange(array, randomIndex, hi);
        return partition(array, lo, hi);
    }

    private static int partition(int[] array, int lo, int hi) {
        int pivot = array[hi];
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (array[j] <= pivot) {
                i++;
                exchange(array, i, j);
            }
        }
        exchange(array, i + 1, hi);
        return i + 1;
    }

}
