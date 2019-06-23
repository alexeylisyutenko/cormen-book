package ru.alexeylisyutenko.cormen.chapter9;

import static ru.alexeylisyutenko.helper.Helpers.*;

@SuppressWarnings("Duplicates")
public class OrderStatisticsSelectionWorstCaseLinear {

    /**
     * This algorithm selects ith order statistics with worst-case runtime O(n).
     *
     * @param array array to select an element from
     * @param index index of the ith smallest element
     * @return ith smallest element
     */
    public static int select(int[] array, int index) {
        if (index < 1 || index > array.length) {
            throw new IllegalArgumentException("Incorrect index argument");
        }
        return doSelect(array, 0, array.length - 1, index);
    }

    private static int doSelect(int[] array, int lo, int hi, int index) {
        int n = hi - lo + 1;
        if (n == 1) {
            return array[lo];
        }

        // Find central median that we can use as a good pivot element.
        int centralMedian = findCentralMedian(array, lo, hi, n);

        // Partition array around central median.
        int q = partition(array, lo, hi, centralMedian);

        int k = q - lo + 1;
        if (k == index) {
            return array[q];
        } else if (index < k) {
            return doSelect(array, lo, q - 1, index);
        } else {
            return doSelect(array, q + 1, hi, index - k);
        }
    }

    private static int findCentralMedian(int[] array, int lo, int hi, double n) {
        // Calculate number of groups of 5 elements.
        int groups = ceilToInt(n / 5);

        // Array for medians in each group.
        int[] medians = new int[groups];

        // find the median for each group of 5 elements.
        for (int i = 0; i < groups; i++) {
            // Calculate lo and hi indexes of the current group.
            int groupLo = lo + i * 5;
            int groupHi = groupLo + 4 <= hi ? groupLo + 4 : hi;

            // Sort elements in each group.
            insertionSort(array, groupLo, groupHi);

            // Take median from each group.
            int medianIndex = groupLo + floorToInt((double) (groupHi - groupLo) / 2);
            medians[i] = array[medianIndex];
        }

        // Find median of medians recursively.
        int centralMedianIndex = floorToInt((double) (medians.length + 1) / 2);
        return doSelect(medians, 0, medians.length - 1, centralMedianIndex);
    }

    private static void insertionSort(int[] array, int lo, int hi) {
        int n = hi - lo + 1;
        if (n < 2) {
            return;
        }
        for (int i = lo + 1; i <= hi; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= lo && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

    private static int partition(int[] array, int lo, int hi, int pivot) {
        int i = lo;
        int lt = lo;
        int gt = hi;
        while (i <= gt) {
            if (array[i] < pivot) {
                exchange(array, lt, i);
                lt++;
                i++;
            } else if (array[i] > pivot) {
                exchange(array, i, gt);
                gt--;
            } else if (array[i] == pivot) {
                i++;
            }
        }
        return lt;
    }

}
