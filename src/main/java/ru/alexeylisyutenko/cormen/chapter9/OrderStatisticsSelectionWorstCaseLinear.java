package ru.alexeylisyutenko.cormen.chapter9;

import java.util.Arrays;

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
        return recursiveSelect(array, 0, array.length - 1, index);
    }

    private static int recursiveSelect(int[] array, int lo, int hi, int index) {
        int n = hi - lo + 1;

        // Base-case think later. Rethink!.
        if (n <= 5) {
            insertionSort(array, lo, hi);
            return array[lo + index - 1];
        }

        // Calculate number of groups of 5 elements.
        int groups = ceilToInt((double) n / 5);

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

//        System.out.println(Arrays.toString(array));

//        System.out.println("Medians: " + Arrays.toString(medians));

        // Find median of medians recursively.
        int centralMedianIndex = floorToInt((double) (medians.length + 1) / 2);
        int centralMedian = recursiveSelect(medians, 0, medians.length - 1, centralMedianIndex);

//        System.out.println("Central median: " + centralMedian);

        // Partition array around central median.
        int q = partition(array, lo, hi, centralMedian);
//        System.out.println("After partition: \r\n" + Arrays.toString(Arrays.copyOfRange(array, lo, hi + 1)));
//        System.out.println("q: " + q + ", array[q]: " + array[q]);

        int k = q - lo + 1;

        if (k == index) {
            return array[q];
        } else if (index < k) {
            return recursiveSelect(array, lo, q - 1, index);
        } else {
            return recursiveSelect(array, q + 1, hi, index - k);
        }
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
