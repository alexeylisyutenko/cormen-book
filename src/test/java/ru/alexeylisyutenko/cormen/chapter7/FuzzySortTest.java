package ru.alexeylisyutenko.cormen.chapter7;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alexeylisyutenko.cormen.chapter7.FuzzySort.*;
import static ru.alexeylisyutenko.helper.Helpers.roundHalfUp;

class FuzzySortTest {

    @Test
    void partitionDemo() {
        Interval[] intervals = {new Interval(3.0, 6.0), new Interval(0.0, 1.0), new Interval(7.0, 9.0), new Interval(2.0, 5.0)};
        System.out.println(Arrays.toString(intervals));

        Pair<Integer, Integer> partition = partition(intervals, 0, intervals.length - 1);
        System.out.println(Arrays.toString(intervals));
        System.out.println(partition);
    }

    @Test
    void sortDemo() {
        Interval[] intervals = {new Interval(6.0, 7.0), new Interval(0.0, 1.0), new Interval(8.0, 9.0), new Interval(2.0, 5.0)};
        System.out.println(Arrays.toString(intervals));

        FuzzySort.sort(intervals);
        System.out.println(Arrays.toString(intervals));

        checkFuzzySort(intervals);
    }

    @Test
    void sortWithAllOverlappedIntervalsDemo() {
        Interval[] intervals = {new Interval(3.0, 7.5), new Interval(0.0, 3.5), new Interval(1.0, 9.0), new Interval(2.0, 5.0), new Interval(3.5, 5.0)};
        System.out.println(Arrays.toString(intervals));

        FuzzySort.sort(intervals);
        System.out.println(Arrays.toString(intervals));

        checkFuzzySort(intervals);
    }

    @Test
    void randomOverlappedIntervalsDemo() {
        Interval[] intervals = generateRandomOverlappedIntervals(1000000);
        System.out.println(Arrays.toString(intervals));
        FuzzySort.sort(intervals);
        System.out.println(Arrays.toString(intervals));

        checkFuzzySort(intervals);
    }

    @Test
    void randomIntervalsDemo() {
        Interval[] intervals = generateRandomIntervals(1000000, 1000000.0);
        System.out.println(Arrays.toString(intervals));
        FuzzySort.sort(intervals);
        System.out.println(Arrays.toString(intervals));

        checkFuzzySort(intervals);
    }

    void checkFuzzySort(Interval[] intervals) {
        if (intervals.length < 2) {
            return;
        }
        Interval previous = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            Interval current = intervals[i];
            boolean overlaps = overlaps(previous, current);
            if (overlaps) {
                previous = new Interval(Math.max(previous.getLeft(), current.getLeft()), Math.min(previous.getRight(), current.getRight()));
            } else {
                assertTrue(previous.getRight() < current.getLeft());
                previous = current;
            }
        }
    }

    private Interval[] generateRandomIntervals(int size, double maxDouble) {
        Interval[] intervals = new Interval[size];
        for (int i = 0; i < size; i++) {
            double left = roundHalfUp(RandomUtils.nextDouble(0.0, maxDouble), 1);
            double right;
            do {
                right = roundHalfUp(RandomUtils.nextDouble(0.0, maxDouble), 1);
            } while (right < left);
            intervals[i] = new Interval(left, right);
        }
        return intervals;
    }

    private Interval[] generateRandomOverlappedIntervals(int size) {
        Interval[] intervals = new Interval[size];
        for (int i = 0; i < size; i++) {
            intervals[i] = new Interval(roundHalfUp(RandomUtils.nextDouble(0.0, 5.0), 1), roundHalfUp(RandomUtils.nextDouble(5.0, 10.0), 1));
        }
        return intervals;
    }

    @Test
    void overlapShouldWorkProperly() {
        assertFalse(FuzzySort.overlaps(new Interval(1.0, 2.0), new Interval(3.0, 4.0)));
        assertFalse(FuzzySort.overlaps(new Interval(1.0, 2.0), new Interval(-1.0, 0.0)));
        assertTrue(FuzzySort.overlaps(new Interval(1.0, 2.0), new Interval(1.5, 1.6)));
        assertTrue(FuzzySort.overlaps(new Interval(1.0, 2.0), new Interval(1.5, 3.0)));
        assertTrue(FuzzySort.overlaps(new Interval(1.0, 2.0), new Interval(2.0, 3.0)));
        assertTrue(FuzzySort.overlaps(new Interval(1.0, 2.0), new Interval(-1.0, 1.5)));
        assertTrue(FuzzySort.overlaps(new Interval(1.0, 2.0), new Interval(-1.0, 1.0)));
    }

}