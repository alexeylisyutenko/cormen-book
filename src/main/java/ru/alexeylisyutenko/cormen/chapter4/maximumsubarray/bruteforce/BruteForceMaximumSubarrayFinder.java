package ru.alexeylisyutenko.cormen.chapter4.maximumsubarray.bruteforce;

import ru.alexeylisyutenko.cormen.chapter4.maximumsubarray.MaximumSubarrayFinder;
import ru.alexeylisyutenko.cormen.chapter4.maximumsubarray.MaximumSubarrayInfo;

public class BruteForceMaximumSubarrayFinder implements MaximumSubarrayFinder {
    @Override
    public MaximumSubarrayInfo find(int[] array) {
        int maxLeft = -1;
        int maxRight = -1;
        long sum = Long.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            long currentSum = 0;
            for (int j = i; j < array.length; j++) {
                currentSum = currentSum + array[j];
                if (currentSum > sum) {
                    sum = currentSum;
                    maxLeft = i;
                    maxRight = j;
                }
            }
        }
        return new MaximumSubarrayInfo(maxLeft, maxRight, sum);
    }
}
