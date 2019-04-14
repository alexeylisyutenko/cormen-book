package ru.alexeylisyutenko.cormen.maximumsubarray;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.maximumsubarray.bruteforce.BruteForceMaximumSubarrayFinder;
import ru.alexeylisyutenko.cormen.maximumsubarray.bruteforcebad.BruteForceBadMaximumSubarrayFinder;
import ru.alexeylisyutenko.cormen.maximumsubarray.divideandconquer.DivideAndConquerMaximumSubarrayFinder;
import ru.alexeylisyutenko.cormen.maximumsubarray.divideandconquerbasecase.DivideAndConquerBaseCaseMaximumSubarrayFinder;

import java.util.Arrays;

class MaximumSubarrayFinderTest {

    private static final int[] array = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

    private MaximumSubarrayFinder bruteForceBadMaximumSubarrayFinder = new BruteForceBadMaximumSubarrayFinder();
    private MaximumSubarrayFinder bruteForceMaximumSubarrayFinder = new BruteForceMaximumSubarrayFinder();
    private MaximumSubarrayFinder divideAndConquerMaximumSubarrayFinder = new DivideAndConquerMaximumSubarrayFinder();
    private MaximumSubarrayFinder divideAndConquerBaseCaseMaximumSubarrayFinder = new DivideAndConquerBaseCaseMaximumSubarrayFinder();

    volatile MaximumSubarrayInfo maximumSubarrayInfo;

    @Test
    void bruteForceBadShouldWorkProperly() {
        MaximumSubarrayInfo maximumSubarrayInfo = bruteForceBadMaximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    @Test
    void bruteForceShouldWorkProperly() {
        MaximumSubarrayInfo maximumSubarrayInfo = bruteForceMaximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    @Test
    void divideAndConquerSumArrayShouldWorkProperly() {
        MaximumSubarrayInfo maximumSubarrayInfo = divideAndConquerMaximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    @Test
    void divideAndConquerBaseCaseSumArrayShouldWorkProperly() {
        MaximumSubarrayInfo maximumSubarrayInfo = divideAndConquerBaseCaseMaximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    private void printMaximumSubarrayInfo(MaximumSubarrayInfo maximumSubarrayInfo) {
        System.out.println(maximumSubarrayInfo);
        int[] maximumSubarray = Arrays.copyOfRange(array, maximumSubarrayInfo.getMaxLeft(), maximumSubarrayInfo.getMaxRight() + 1);
        System.out.println(Arrays.toString(maximumSubarray));
    }

    @Test
    void poorQualityPerformanceTest() {
        int[] randomIntArray = randomIntArray(150);

        // Check validity.
        MaximumSubarrayInfo bruteForceBadInfo = bruteForceBadMaximumSubarrayFinder.find(array);
        MaximumSubarrayInfo bruteForceInfo = bruteForceMaximumSubarrayFinder.find(array);
        MaximumSubarrayInfo divideAndConquerInfo = divideAndConquerMaximumSubarrayFinder.find(array);
        if (!bruteForceBadInfo.equals(bruteForceInfo) ||
                !bruteForceBadInfo.equals(divideAndConquerInfo) ||
                !bruteForceInfo.equals(divideAndConquerInfo)) {
            throw new IllegalStateException();
        }

        long bruteForce = checkPerformance(randomIntArray, bruteForceMaximumSubarrayFinder);
        System.out.println("Bruteforce: " + bruteForce);

        long divideAndConquer = checkPerformance(randomIntArray, divideAndConquerMaximumSubarrayFinder);
        System.out.println("Divide and conquer: " + divideAndConquer);
    }

    private long checkPerformance(int[] array, MaximumSubarrayFinder maximumSubarrayFinder) {
        for (int i = 0; i < 10000; i++) {
            maximumSubarrayInfo = maximumSubarrayFinder.find(array);
        }
        int repeat = 100000;
        long before = System.nanoTime();
        for (int i = 0; i < repeat; i++) {
            maximumSubarrayInfo = maximumSubarrayFinder.find(array);
        }
        return (System.nanoTime() - before) / repeat;
    }

    private int[] randomIntArray(int size) {
        int[] randomArray = new int[size];
        for (int i = 0; i < size; i++) {
            randomArray[i] = RandomUtils.nextInt(0, 100) - 50;
        }
        return randomArray;
    }

}