package ru.alexeylisyutenko.maximumsubarray;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.maximumsubarray.bruteforce.BruteforceMaximumSubarrayFinder;
import ru.alexeylisyutenko.maximumsubarray.bruteforcebad.BruteforceBadMaximumSubarrayFinder;
import ru.alexeylisyutenko.maximumsubarray.divideandconquer.DivideAndConquerMaximumSubarrayFinder;

import java.util.Arrays;

class MaximumSubarrayFinderTest {

    private static final int[] array = new int[]{13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7};

    private MaximumSubarrayFinder bruteforceBadMaximumSubarrayFinder = new BruteforceBadMaximumSubarrayFinder();
    private MaximumSubarrayFinder bruteforeceMaximumSubarrayFinder = new BruteforceMaximumSubarrayFinder();
    private MaximumSubarrayFinder divideAndConquerMaximumSubarrayFinder = new DivideAndConquerMaximumSubarrayFinder();

    volatile MaximumSubarrayInfo maximumSubarrayInfo;

    @Test
    void bruteforceBadShouldWorkProperly() {
        MaximumSubarrayInfo maximumSubarrayInfo = bruteforceBadMaximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    @Test
    void bruteforceShouldWorkProperly() {
        MaximumSubarrayInfo maximumSubarrayInfo = bruteforeceMaximumSubarrayFinder.find(array);
        printMaximumSubarrayInfo(maximumSubarrayInfo);
    }

    @Test
    void devideAndConquerSumArrayShouldWorkProperly() {
        MaximumSubarrayInfo maximumSubarrayInfo = divideAndConquerMaximumSubarrayFinder.find(array);
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
        MaximumSubarrayInfo bruteforceBadInfo = bruteforceBadMaximumSubarrayFinder.find(array);
        MaximumSubarrayInfo bruteforceInfo = bruteforeceMaximumSubarrayFinder.find(array);
        MaximumSubarrayInfo divideAndConquerInfo = divideAndConquerMaximumSubarrayFinder.find(array);
        if (!bruteforceBadInfo.equals(bruteforceInfo) ||
                !bruteforceBadInfo.equals(divideAndConquerInfo) ||
                !bruteforceInfo.equals(divideAndConquerInfo)) {
            throw new IllegalStateException();
        }

        long bruteforce = checkPerformance(randomIntArray, bruteforeceMaximumSubarrayFinder);
        System.out.println("Bruteforce: " + bruteforce);

        long divideAndConquer = checkPerformance(randomIntArray, divideAndConquerMaximumSubarrayFinder);
        System.out.println("Divide and conquer: " + divideAndConquer);
    }

    @Test
    void findArraySizeWhenDevideAndConquerBecomesFaster() {
        int size = 1;
        while (true) {
            int[] randomIntArray = randomIntArray(size);

            long bruteforce = checkPerformance(randomIntArray, bruteforeceMaximumSubarrayFinder);
            long divideAndConquer = checkPerformance(randomIntArray, divideAndConquerMaximumSubarrayFinder);
            System.out.println(String.format(" %s Size: %d, Bruteforce: %d, divide and conquer: %d", bruteforce > divideAndConquer ? " " : "*", size, bruteforce, divideAndConquer));

            if (bruteforce > 2 * divideAndConquer) {
                return;
            }

            size++;
        }
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