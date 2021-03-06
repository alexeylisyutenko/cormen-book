package ru.alexeylisyutenko.cormen.chapter4.maximumsubarray.divideandconquer;

import ru.alexeylisyutenko.cormen.chapter4.maximumsubarray.MaximumSubarrayFinder;
import ru.alexeylisyutenko.cormen.chapter4.maximumsubarray.MaximumSubarrayInfo;

public class DivideAndConquerMaximumSubarrayFinder implements MaximumSubarrayFinder {
    @Override
    public MaximumSubarrayInfo find(int[] array) {
        return findMaxSubarray(array, 0, array.length - 1);
    }

    private MaximumSubarrayInfo findMaxSubarray(int[] array, int low, int high) {
        if (low == high) {
            return new MaximumSubarrayInfo(low, high, array[low]);
        } else {
            int mid = (low + high) / 2;
            MaximumSubarrayInfo leftMaxSubarray = findMaxSubarray(array, low, mid);
            MaximumSubarrayInfo rightMaxSubarray = findMaxSubarray(array, mid + 1, high);
            MaximumSubarrayInfo crossingMaxSubarray = findMaxCrossingSubarray(array, low, high, mid);

            if (leftMaxSubarray.getSum() >= rightMaxSubarray.getSum()
                    && leftMaxSubarray.getSum() >= crossingMaxSubarray.getSum()) {
                return leftMaxSubarray;
            } else if (rightMaxSubarray.getSum() >= leftMaxSubarray.getSum()
                    && rightMaxSubarray.getSum() >= crossingMaxSubarray.getSum()) {
                return rightMaxSubarray;
            } else {
                return crossingMaxSubarray;
            }
        }
    }

    private MaximumSubarrayInfo findMaxCrossingSubarray(int[] array, int low, int high, int mid) {
        long leftSum = Long.MIN_VALUE;
        int maxLeft = -1;
        long sum = 0L;
        for (int i = mid; i >= low; i--) {
            sum += array[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        long rightSum = Long.MIN_VALUE;
        int maxRight = -1;
        sum = 0L;
        for (int i = mid + 1; i <= high; i++) {
            sum += array[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }
        return new MaximumSubarrayInfo(maxLeft, maxRight, leftSum + rightSum);
    }

}
