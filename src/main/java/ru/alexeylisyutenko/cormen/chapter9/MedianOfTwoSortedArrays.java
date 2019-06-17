package ru.alexeylisyutenko.cormen.chapter9;

public class MedianOfTwoSortedArrays {

    public static int findMedian(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            throw new IllegalArgumentException("Arrays must have equal length");
        }
        if (array1.length == 0) {
            throw new IllegalArgumentException("Each arrays must contain at least one element");
        }
        return findMedianRecursive(array1, array2, 0, array1.length - 1, 0, array2.length - 1);
    }

    private static int findMedianRecursive(int[] array1, int[] array2, int lo1, int hi1, int lo2, int hi2) {
        int n = hi1 - lo1 + 1;
        if (n == 1) {
            return Math.max(array1[lo1], array2[lo1]);
        }

        int k = n / 2;

        //!!!
        if (array1[lo1 + k - 1] < array2[lo2 + k - 1]) {
            return findMedianRecursive(array1, array2, lo1 + k - 1, hi1, lo2, lo2 + k);
        } else {
            return findMedianRecursive(array1, array2, lo1, lo1 + k - 1, lo2 + k, hi2);
        }

    }

}
