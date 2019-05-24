package ru.alexeylisyutenko.cormen.chapter8;

import java.util.Arrays;

@SuppressWarnings("Duplicates")
public class CountingSortInPlace2 {

    /**
     * Counting sort implementation that sorts n numbers each is in [0..k] interval.
     * <p>This implementation of the counting sort sorts in place.</p>
     *
     * @param array sorting array
     * @param k     max number
     * @return sorted array
     */
    public static void sort(int[] array, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Incorrect k value: " + k);
        }
        if (array.length < 2) {
            return;
        }

        // Initialize counts.
        int[] counts = new int[k + 1];
        Arrays.fill(counts, 0);

        // counts[i] contains number of elements i.
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > k) {
                throw new IllegalArgumentException(String.format("Incorrect input array value array[%d] == %d", i, array[i]));
            }
            counts[array[i]]++;
        }

        //
        int p = 0;
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < counts[i]; j++) {
                array[p] = i;
                p++;
            }
        }
    }

}
