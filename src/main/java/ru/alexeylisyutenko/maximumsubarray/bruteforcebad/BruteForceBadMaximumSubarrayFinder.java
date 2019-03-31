package ru.alexeylisyutenko.maximumsubarray.bruteforcebad;

import ru.alexeylisyutenko.maximumsubarray.MaximumSubarrayFinder;
import ru.alexeylisyutenko.maximumsubarray.MaximumSubarrayInfo;

public class BruteForceBadMaximumSubarrayFinder implements MaximumSubarrayFinder {
    @Override
    public MaximumSubarrayInfo find(int[] array) {
        int maxLeft = -1;
        int maxRight = -1;
        long sum = Long.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            for (int j = i; j < array.length; j++) {
                long currentSum = findSubarraySum(array, i, j);
                if (currentSum > sum) {
                    sum = currentSum;
                    maxLeft = i;
                    maxRight = j;
                }
            }
        }
        return new MaximumSubarrayInfo(maxLeft, maxRight, sum);
    }

    private long findSubarraySum(int[] array, int left, int right) {
        long sum = 0;
        for (int i = left; i <= right; i++) {
            sum += array[i];
        }
        return sum;
    }
}
