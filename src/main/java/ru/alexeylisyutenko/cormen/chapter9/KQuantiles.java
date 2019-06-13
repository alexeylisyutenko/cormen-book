package ru.alexeylisyutenko.cormen.chapter9;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

@SuppressWarnings("Duplicates")
public class KQuantiles {

    public static int[] quantiles(int[] array, int k) {
        if (k < 1 || k > array.length + 1) {
            throw new IllegalArgumentException("Incorrect k value");
        }
        int[] resultingQuantiles = new int[k - 1];
        findQuantiles(array, 0, array.length - 1, k, resultingQuantiles, 0);
        return resultingQuantiles;
    }

    private static void findQuantiles(int[] array, int lo, int hi, int k, int[] resultingQuantiles, int offset) {
        if (k == 1) {
            return;
        }

        int n = hi - lo + 1;

        int currentQuantileNumber = k / 2;
        int currentQuantileIndex = (int) Math.ceil(currentQuantileNumber * (double) n / k);

        int selectedOrderStatistics = randomizedSelect(array, lo, hi, currentQuantileIndex);
        resultingQuantiles[offset + currentQuantileNumber - 1] = selectedOrderStatistics;

        int quantilesInLeftPart = (int) Math.floor((double) k / 2);
        findQuantiles(array, lo, offset + currentQuantileIndex - 1, quantilesInLeftPart,
                resultingQuantiles, offset);

        int quantilesInRightPart = (int) Math.ceil((double) k / 2);
        findQuantiles(array, offset + currentQuantileIndex, hi, quantilesInRightPart,
                resultingQuantiles, offset + currentQuantileNumber);
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
