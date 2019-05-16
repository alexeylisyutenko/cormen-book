package ru.alexeylisyutenko.cormen.chapter8;

import java.util.Arrays;

public class RadixSort {

    public static final int MAX_DIGITS = 9;

    public static void sort(int[] array, int digits) {
        if (digits < 1 || digits > MAX_DIGITS) {
            throw new IllegalArgumentException("Incorrect digits value: " + digits);
        }
        for (int i = 1; i <= digits; i++) {
            sortByOnDigit(array, i);
        }
    }

    public static void sortByOnDigit(int[] array, int n) {
        int[] counts = new int[10];
        Arrays.fill(counts, 0);

        for (int i = 0; i < array.length; i++) {
            int digit = getNthDigit(array[i], n);
            counts[digit]++;
        }

        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }

        int[] sortResult = new int[array.length];
        for (int i = array.length - 1; i >= 0 ; i--) {
            int digit = getNthDigit(array[i], n);
            sortResult[counts[digit] - 1] = array[i];
            counts[digit]--;
        }

        System.arraycopy(sortResult, 0, array, 0, array.length);
    }

    public static int getNthDigit(int number, int n) {
        return (int) ((number / Math.pow(10, n - 1)) % 10);
    }

}
