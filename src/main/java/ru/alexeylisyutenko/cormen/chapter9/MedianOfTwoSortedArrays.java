package ru.alexeylisyutenko.cormen.chapter9;

public class MedianOfTwoSortedArrays {

    public static int findMedian(int[] array1, int[] array2) {
        if (array1.length != array2.length) {
            throw new IllegalArgumentException("Arrays must have equal length");
        }
        if (array1.length == 0) {
            throw new IllegalArgumentException("Each arrays must contain at least one element");
        }
        return doFindMedian(array1, array2, 0, array1.length - 1, 0, array2.length - 1);
    }

    private static int doFindMedian(int[] array1, int[] array2, int lo1, int hi1, int lo2, int hi2) {
        while (true) {
            int n = hi1 - lo1 + 1;

            if (n == 1) {
                return Math.min(array1[lo1], array2[lo2]);
            } else if (n == 2) {
                return Math.min(Math.max(array1[lo1], array2[lo2]), Math.min(array1[hi1], array2[hi2]));
            }

            int medianIndex = (n + 1) / 2 - 1;
            int median1 = array1[lo1 + medianIndex];
            int median2 = array2[lo2 + medianIndex];

            if (median1 == median2) {
                return median1;
            } else if (median1 < median2) {
                if (n % 2 == 0) {
                    lo1 = lo1 + medianIndex + 1;
                    hi2 = lo2 + medianIndex;
                } else {
                    lo1 = lo1 + medianIndex;
                    hi2 = lo2 + medianIndex;
                }
            } else {
                if (n % 2 == 0) {
                    hi1 = lo1 + medianIndex;
                    lo2 = lo2 + medianIndex + 1;
                } else {
                    hi1 = lo1 + medianIndex;
                    lo2 = lo2 + medianIndex;
                }
            }
        }

    }

}
