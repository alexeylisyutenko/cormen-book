package ru.alexeylisyutenko.cormen.chapter7;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

public class HoareQuickSort {

    public static int partition(int[] array, int p, int r) {
        int x = array[p];
        int i = p - 1;
        int j = r + 1;
        while (true) {
            do {
                j--;
            } while (array[j] > x);
            do {
                i++;
            } while(array[i] < x);
            if (i < j) {
                exchange(array, i, j);
            } else {
                return j;
            }
        }
    }

    public static void quickSort(int[] array, int p, int r) {
        if (p < r) {
            int q = partition(array, p, r);
            quickSort(array, p, q);
            quickSort(array, q + 1, r);
        }
    }

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

}
