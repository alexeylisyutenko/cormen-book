package ru.alexeylisyutenko.cormen.chapter2;

public class MergeSortWithInsertionSortBaseCase {

    public static final int BASE_CASE_THRESHOLD = 350;

    public static void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort(int[] array, int p, int r) {
        if (r - p < BASE_CASE_THRESHOLD) {
            insertionSort(array, p, r);
        } else {
            int q = (p + r) / 2;
            mergeSort(array, p, q);
            mergeSort(array, q + 1, r);
            MergeSort.merge(array, p, q, r);
        }
    }

    private static void insertionSort(int[] array, int p, int r) {
        int size = r - p + 1;
        if (size < 2) {
            return;
        }
        for (int i = p + 1; i <= r; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= p && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

}
