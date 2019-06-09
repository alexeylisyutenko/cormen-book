package ru.alexeylisyutenko.cormen.chapter9;

import java.util.concurrent.ThreadLocalRandom;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

@SuppressWarnings("Duplicates")
public class OrderStatisticSelection {

    /**
     * Selects ith smallest element in array.
     *
     * @param array array to select an element from
     * @param index index of the ith smallest element
     * @return ith smallest element
     */
    public static int select(int[] array, int index) {
        if (index < 1 || index > array.length) {
            throw new IllegalArgumentException("Incorrect index argument");
        }
        return iterativeRandomizedSelect(array, 0, array.length - 1, index);
    }

    private static int recursiveRandomizedSelect(int[] array, int lo, int hi, int index) {
        if (lo == hi) {
            return array[lo];
        }
        int q = randomizedPartition(array, lo, hi);
        int k = q - lo + 1;
        if (k == index) {
            return array[q];
        }
        if (index < k) {
            return recursiveRandomizedSelect(array, lo, q - 1, index);
        } else {
            return recursiveRandomizedSelect(array, q + 1, hi, index - k);
        }
    }

    private static int iterativeRandomizedSelect(int[] array, int lo, int hi, int index) {
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
