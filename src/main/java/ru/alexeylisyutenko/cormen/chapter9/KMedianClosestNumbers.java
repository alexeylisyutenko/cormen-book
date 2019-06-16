package ru.alexeylisyutenko.cormen.chapter9;

import java.util.concurrent.ThreadLocalRandom;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

public class KMedianClosestNumbers {

    public static int[] kClosest(int[] array, int k) {
        if (k < 0 || k > array.length - 1) {
            throw new IllegalArgumentException("Invalid k: " + k);
        }

        // Find the median.
        int medianIndex = (array.length + 1) / 2;
        int median = OrderStatisticSelection.select(array, medianIndex);

        // Put median element to the last position in the array, so it won't participate in further array operations.
        exchange(array, medianIndex - 1, array.length - 1);

        // Find index of k closest element to the median.
        partitionAccordingToKthClosestElements(array, 0, array.length - 2, k, median);

        // The array is already partitioned the way we need. So we can just copy elements to the resulting array.
        int[] result = new int[k];
        System.arraycopy(array, 0, result, 0, k);
        return result;
    }

    private static void partitionAccordingToKthClosestElements(int[] array, int lo, int hi, int index, int median) {
        if (hi < lo) {
            return;
        }
        while (true) {
            if (lo == hi) {
                return;
            }
            int q = randomizedPartition(array, lo, hi, median);
            int k = q - lo + 1;
            if (k == index) {
                return;
            }
            if (index < k) {
                hi = q - 1;
            } else {
                lo = q + 1;
                index -= k;
            }
        }
    }

    private static int randomizedPartition(int[] array, int lo, int hi, int median) {
        int randomIndex = lo + ThreadLocalRandom.current().nextInt(hi - lo + 1);
        exchange(array, randomIndex, hi);
        return partition(array, lo, hi, median);
    }

    private static int partition(int[] array, int lo, int hi, int median) {
        int pivot = diffWithMedian(array[hi], median);
        int i = lo - 1;
        for (int j = lo; j < hi; j++) {
            if (diffWithMedian(array[j], median) <= pivot) {
                i++;
                exchange(array, i, j);
            }
        }
        exchange(array, i + 1, hi);
        return i + 1;
    }

    private static int diffWithMedian(int value, int median) {
        return Math.abs(median - value);
    }

}
