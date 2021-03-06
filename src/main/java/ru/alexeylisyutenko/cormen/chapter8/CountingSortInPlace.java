package ru.alexeylisyutenko.cormen.chapter8;

import java.util.Arrays;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

@SuppressWarnings("Duplicates")
public class CountingSortInPlace {

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

        // counts[i] contains the number of elements less or equal i.
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i - 1] + counts[i];
        }

        // Array which helps to find if an array element is in the correct position.
        int[] ranges = Arrays.copyOf(counts, counts.length);

        int j = array.length - 1;
        while (j >= 1) {
            int element = array[j];
            int leftIndex = element > 0 ? ranges[element - 1]: 0;
            int rightIndex = ranges[element] - 1;

            boolean incorrectPosition = j < leftIndex || j > rightIndex;

            if (incorrectPosition) {
                exchange(array, j, counts[element] - 1);
                counts[element]--;
            } else {
                j--;
            }
        }
    }

}
