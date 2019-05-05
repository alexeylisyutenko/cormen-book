package ru.alexeylisyutenko.cormen.chapter7;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alexeylisyutenko.cormen.chapter7.FuzzySort.*;
import static ru.alexeylisyutenko.helper.Helpers.round;

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
    }

    @Test
    void sortWithAllOverlappedIntervalsDemo() {
        Interval[] intervals = {new Interval(3.0, 7.5), new Interval(0.0, 3.5), new Interval(1.0, 9.0), new Interval(2.0, 5.0), new Interval(3.5, 5.0)};
        System.out.println(Arrays.toString(intervals));

        FuzzySort.sort(intervals);
        System.out.println(Arrays.toString(intervals));
    }

    @Test
    void randomOverlappedIntervalsDemo() {
        Interval[] intervals = generateRandomOverlappedIntervals(1000);
        System.out.println(Arrays.toString(intervals));
        FuzzySort.sort(intervals);
        System.out.println(Arrays.toString(intervals));
    }

    @Test
    void randomIntervalsDemo() {
        Interval[] intervals = generateRandomIntervals(1000, 1000.0);
        System.out.println(Arrays.toString(intervals));
        FuzzySort.sort(intervals);
        System.out.println(Arrays.toString(intervals));
    }

    private Interval[] generateRandomIntervals(int size, double maxDouble) {
        Interval[] intervals = new Interval[size];
        for (int i = 0; i < size; i++) {
            double left = round(RandomUtils.nextDouble(0.0, maxDouble), 1);
            double right;
            do {
                right = round(RandomUtils.nextDouble(0.0, maxDouble), 1);
            } while (right < left);
            intervals[i] = new Interval(left, right);
        }
        return intervals;
    }

    private Interval[] generateRandomOverlappedIntervals(int size) {
        Interval[] intervals = new Interval[size];
        for (int i = 0; i < size; i++) {
            intervals[i] = new Interval(round(RandomUtils.nextDouble(0.0, 5.0), 1), round(RandomUtils.nextDouble(5.0, 10.0), 1));
        }
        return intervals;
    }

    @Test
    void overlapShouldWorkProperly() {
        assertFalse(FuzzySort.overlap(new Interval(1.0, 2.0), new Interval(3.0, 4.0)));
        assertFalse(FuzzySort.overlap(new Interval(1.0, 2.0), new Interval(-1.0, 0.0)));
        assertTrue(FuzzySort.overlap(new Interval(1.0, 2.0), new Interval(1.5, 1.6)));
        assertTrue(FuzzySort.overlap(new Interval(1.0, 2.0), new Interval(1.5, 3.0)));
        assertTrue(FuzzySort.overlap(new Interval(1.0, 2.0), new Interval(2.0, 3.0)));
        assertTrue(FuzzySort.overlap(new Interval(1.0, 2.0), new Interval(-1.0, 1.5)));
        assertTrue(FuzzySort.overlap(new Interval(1.0, 2.0), new Interval(-1.0, 1.0)));
    }

}