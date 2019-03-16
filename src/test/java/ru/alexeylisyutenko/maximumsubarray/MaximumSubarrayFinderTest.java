package ru.alexeylisyutenko.maximumsubarray;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.maximumsubarray.bruteforce.BruteforceMaximumSubarrayFinder;
import ru.alexeylisyutenko.maximumsubarray.bruteforcebad.BruteforceBadMaximumSubarrayFinder;
import ru.alexeylisyutenko.maximumsubarray.devideandconquer.DevideAndConquerMaximumSubarrayFinder;

import java.util.Arrays;

class MaximumSubarrayFinderTest {

    private static final int[] array = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

    @Test
    void bruteforceBadShouldWorkProperly() {
        MaximumSubarrayFinder maximumSubarrayFinder = new BruteforceBadMaximumSubarrayFinder();
        MaximumSubarrayInfo maximumSubarrayInfo = maximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    @Test
    void bruteforceShouldWorkProperly() {
        MaximumSubarrayFinder maximumSubarrayFinder = new BruteforceMaximumSubarrayFinder();
        MaximumSubarrayInfo maximumSubarrayInfo = maximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    @Test
    void devideAndConquerSumArrayShouldWorkProperly() {
        MaximumSubarrayFinder maximumSubarrayFinder = new DevideAndConquerMaximumSubarrayFinder();
        MaximumSubarrayInfo maximumSubarrayInfo = maximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    private void printMaximumSubarrayInfo(MaximumSubarrayInfo maximumSubarrayInfo) {
        System.out.println(maximumSubarrayInfo);
        int[] maximumSubarray = Arrays.copyOfRange(array, maximumSubarrayInfo.getMaxLeft(), maximumSubarrayInfo.getMaxRight() + 1);
        System.out.println(Arrays.toString(maximumSubarray));
    }

}