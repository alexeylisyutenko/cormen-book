package ru.alexeylisyutenko.cormen.chapter8;

public class CountingSort {

    /**
     * Counting sort implementation that sorts n numbers each is in [0..k] interval.
     *
     * @param array sorting array
     * @param k max number
     * @return sorted array 
     */
    public static void sort(int[] array, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Incorrect k value: " + k);
        }

        // Initialize counts.
        int[] counts = new int[k + 1];
        for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }

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


        int[] sortedArray = new int[array.length];
        for (int i = array.length - 1; i >= 0 ; i--) {
            int current = array[i];
            sortedArray[counts[current] - 1] = current;
            counts[current]--;
        }

        System.arraycopy(sortedArray, 0, array, 0, sortedArray.length);
    }

}
