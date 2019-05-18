package ru.alexeylisyutenko.cormen.chapter7;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

public final class QuickSort {

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int p, int r) {
        while (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q - 1);
            p = q + 1;
        }
    }

    public static int partition(int[] array, int p, int r) {
        int x = array[r];
        int i = p - 1;
        for (int j = p; j < r; j++) {
            if (array[j] <= x) {
                i++;
                exchange(array, i, j);
            }
        }
        exchange(array, i + 1, r);
        return i + 1;
    }

}
