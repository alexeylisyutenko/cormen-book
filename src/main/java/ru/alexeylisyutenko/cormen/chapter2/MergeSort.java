package ru.alexeylisyutenko.cormen.chapter2;

public class MergeSort {

    public static void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    public static void mergeSort(int[] array, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            mergeSort(array, p, q);
            mergeSort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }

    public static void merge(int[] array, int p, int q, int r) {
        // Merging two sorted arrays: A[p..q] and A[q+1..r].
        int sizeLeft = q - p + 1;
        int sizeRight = r - q;

        int[] left = new int[sizeLeft + 1];
        for (int i = 0; i < left.length - 1; i++) {
            left[i] = array[p + i];
        }
        left[left.length - 1] = Integer.MAX_VALUE;

        int[] right = new int[sizeRight + 1];
        for (int i = 0; i < right.length - 1; i++) {
            right[i] = array[q + 1 + i];
        }
        right[right.length - 1] = Integer.MAX_VALUE;

        int currentLeft = 0;
        int currentRight = 0;
        for (int i = p; i <= r; i++) {
            if (left[currentLeft] <= right[currentRight]) {
                array[i] = left[currentLeft];
                currentLeft++;
            } else {
                array[i] = right[currentRight];
                currentRight++;
            }
        }
    }

}
