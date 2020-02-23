package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter14.intervaltree.Interval;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.fail;
import static ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap.DefaultPointOfMaximumOverlapTreeTest.IntervalHelper.*;

class DefaultPointOfMaximumOverlapTreeTest {

    private PointOfMaximumOverlapTree tree;

    @BeforeEach
    void setup() {
        tree = new DefaultPointOfMaximumOverlapTree();
    }

    @Test
    void demo() {
        tree.intervalInsert(new Interval(6, 12));
        tree.intervalInsert(new Interval(13, 15));
        tree.print();

        System.out.println();
        System.out.println(tree.findPom());
    }

    @Test
    void randomizedDemo() {
        int maximumX = 50;
        int intervalsNumber = 2;

        List<Interval> intervals = generateIntervalsWithAtLeastOneOverlap(maximumX, intervalsNumber);
        intervals = List.of(new Interval(34, 35), new Interval(35, 39), new Interval(42, 42));
        printIntervals(maximumX, intervals);

        List<Interval> allMaximumOverlapsSimple = findAllMaximumOverlapsSimple(intervals);
        printIntervals(maximumX, allMaximumOverlapsSimple);

        int expectedPom = findPointOfMaximumOverlapSimple(intervals);
        System.out.println("Expected pom: " + expectedPom);

        int actualPom = findPointOfMaximumOverlap(intervals);
        System.out.println("Returned pom: " + actualPom);

        System.out.println();
        tree.print();
    }

    @SuppressWarnings("rawtypes")
    private int findPointOfMaximumOverlap(List<Interval> intervals) {
        ((BinarySearchTree) tree).clear();
        intervals.forEach(tree::intervalInsert);
        return tree.findPom();
    }

    @Disabled
    @Test
    void demo2() {
        List<Interval> intervals = List.of(new Interval(27, 29), new Interval(29, 29), new Interval(6, 17));
        printIntervals(30, intervals);

        int expectedPom = findPointOfMaximumOverlapSimple(intervals);
        System.out.println("Expected pom: " + expectedPom);
    }

    @Test
    void someIntervalTests() {
        List<Interval> intervals = List.of(new Interval(12, 13), new Interval(27, 28), new Interval(5, 15));
        assertPointOfMaximumOverlapIsValid(intervals, findPointOfMaximumOverlap(intervals));

        intervals = List.of(new Interval(29, 29), new Interval(21, 23), new Interval(18, 27));
        assertPointOfMaximumOverlapIsValid(intervals, findPointOfMaximumOverlap(intervals));

        intervals = List.of(new Interval(16, 24), new Interval(3, 23), new Interval(23, 26));
        assertPointOfMaximumOverlapIsValid(intervals, findPointOfMaximumOverlap(intervals));

        intervals = List.of(new Interval(3, 14), new Interval(9, 25), new Interval(20, 21));
        assertPointOfMaximumOverlapIsValid(intervals, findPointOfMaximumOverlap(intervals));
    }

    @RepeatedTest(1000)
    void randomizedTest() {
        int maximumX = 50;
        int intervalsNumber = RandomUtils.nextInt(2, 10);

        List<Interval> intervals = generateIntervalsWithAtLeastOneOverlap(maximumX, intervalsNumber);
        assertPointOfMaximumOverlapIsValid(intervals, findPointOfMaximumOverlap(intervals));
    }

    void assertPointOfMaximumOverlapIsValid(List<Interval> intervals, int actualPointOfMaximumOverlap) {
        List<Interval> allMaximumOverlaps = findAllMaximumOverlapsSimple(intervals);

        boolean pointOfMaximumOverlapIsCorrect = false;
        for (Interval interval : allMaximumOverlaps) {
            if (interval.getLow() >= actualPointOfMaximumOverlap && actualPointOfMaximumOverlap <= interval.getHigh()) {
                pointOfMaximumOverlapIsCorrect = true;
                break;
            }
        }

        int maximumX = intervals.stream().mapToInt(Interval::getHigh).max().orElseThrow() + 10;

        if (!pointOfMaximumOverlapIsCorrect) {
            String message = String.format("Point of maximum overlap '%d' is not inside of any of maximum overlap intervals: '%s'.", actualPointOfMaximumOverlap, allMaximumOverlaps) + "\r\n" + "\r\n" +
                    "Intervals: " + "\r\n" +
                    constructStringRepresentation(maximumX, intervals) + "\r\n" +
                    "Maximum overlaps: " + "\r\n" +
                    constructStringRepresentation(maximumX, allMaximumOverlaps) + "\r\n" +
                    "Expected pom: " + findPointOfMaximumOverlapSimple(intervals) + "\r\n" +
                    "Actual pom: " + actualPointOfMaximumOverlap + "\r\n" + "\r\n";
            fail(message);
        }
    }

    public static class IntervalHelper {
        /**
         * Prints a list of intervals to standard output.
         *
         * @param maximumX  maximum X coordinate to be printed
         * @param intervals list of intervals to print
         */
        public static void printIntervals(int maximumX, List<Interval> intervals) {
            System.out.println(constructStringRepresentation(maximumX, intervals));
        }

        /**
         * Constructs a string representing intervals in a list.
         *
         * @param maximumX  maximum X coordinate to be represented
         * @param intervals list of intervals
         * @return representation of the intervals
         */
        public static String constructStringRepresentation(int maximumX, List<Interval> intervals) {
            StringBuilder sb = new StringBuilder();

            for (Interval interval : intervals) {
                String line = " ".repeat(interval.getLow())
                        + "*".repeat(interval.getHigh() - interval.getLow() + 1)
                        + " ".repeat(maximumX - interval.getHigh()) + interval.toString();
                sb.append(line).append("\r\n");
            }
            sb.append("|".repeat(maximumX)).append("\r\n");
            return sb.toString();
        }

        /**
         * Generate random intervals with at least on overlap.
         *
         * @param maximumX        maximum X coordinate (not included) used in generated intervals
         * @param intervalsNumber number of intervals to generate
         * @return generated intervals
         */
        public static List<Interval> generateIntervalsWithAtLeastOneOverlap(int maximumX, int intervalsNumber) {
            if (intervalsNumber < 2) {
                throw new IllegalArgumentException("intervalsNumber must be greater of equal than 2");
            }

            List<Interval> intervals;
            do {
                intervals = Stream.generate(() -> {
                    int low = RandomUtils.nextInt(0, maximumX);
                    int high = RandomUtils.nextInt(low, maximumX);
                    return new Interval(low, high);
                }).limit(intervalsNumber).collect(Collectors.toList());
            } while (!containsOverlappedIntervals(intervals));
            return intervals;
        }

        /**
         * Checks if a list of intervals contains at least a single pair of intervals that overlap.
         *
         * @param intervals list of intervals to check
         * @return true - there is overlap, false - no overlapping intervals
         */
        public static boolean containsOverlappedIntervals(List<Interval> intervals) {
            for (int i = 0; i < intervals.size() - 1; i++) {
                for (int j = i + 1; j < intervals.size(); j++) {
                    Interval i1 = intervals.get(i);
                    Interval i2 = intervals.get(j);
                    if (i1.getLow() <= i2.getHigh()
                            && i2.getLow() <= i1.getHigh()) {
                        return true;
                    }
                }
            }
            return false;
        }

        /**
         * Finds a point of a maximum overlap.
         *
         * @param intervals interval list
         * @return point of a maximum overlap
         */
        public static int findPointOfMaximumOverlapSimple(List<Interval> intervals) {
            return findPointOfMaximumOverlapWithNumberOfIntersectingIntervals(intervals).getLeft();
        }

        /**
         * Finds a point of a maximum overlap along with a number of intersecting intervals at this point.
         *
         * @param intervals list of intervals
         * @return point of a maximum overlap and a number of intersecting intervals at this point
         */
        public static Pair<Integer, Integer> findPointOfMaximumOverlapWithNumberOfIntersectingIntervals(List<Interval> intervals) {
            ArrayList<Pair<Integer, Integer>> endpoints = prepareAndSortIntervalsEndpoints(intervals);

            int maximumOverlappingIntervals = Integer.MIN_VALUE;
            int pointOfMaximumOverlap = Integer.MIN_VALUE;

            int currentSum = 0;
            for (Pair<Integer, Integer> endpoint : endpoints) {
                currentSum += endpoint.getRight();
                if (currentSum > maximumOverlappingIntervals) {
                    maximumOverlappingIntervals = currentSum;
                    pointOfMaximumOverlap = endpoint.getLeft();
                }
            }

            return Pair.of(pointOfMaximumOverlap, maximumOverlappingIntervals);
        }

        private static ArrayList<Pair<Integer, Integer>> prepareAndSortIntervalsEndpoints(List<Interval> intervals) {
            ArrayList<Pair<Integer, Integer>> endpoints = new ArrayList<>(intervals.size() * 2);
            for (Interval interval : intervals) {
                endpoints.add(Pair.of(interval.getLow(), +1));
                endpoints.add(Pair.of(interval.getHigh(), -1));
            }
            endpoints.sort(
                    Comparator.comparing(Pair<Integer, Integer>::getLeft)
                            .thenComparing(Comparator.comparing(Pair<Integer, Integer>::getRight).reversed())
            );
            return endpoints;
        }

        /**
         * Finds all segments of maximum overlap.
         *
         * @param intervals interval list
         * @return list of intervals, whose all points are the points of maximum overlap
         */
        public static List<Interval> findAllMaximumOverlapsSimple(List<Interval> intervals) {
            int maximumNumberOfIntersectingIntervals = findPointOfMaximumOverlapWithNumberOfIntersectingIntervals(intervals).getRight();
            ArrayList<Pair<Integer, Integer>> endpoints = prepareAndSortIntervalsEndpoints(intervals);

            ArrayList<Interval> maximumOverlaps = new ArrayList<>();

            int maxIntervalLeftEndpoint = Integer.MIN_VALUE;
            int currentSum = 0;
            for (Pair<Integer, Integer> endpoint : endpoints) {
                int newSum = currentSum + endpoint.getRight();

                if (newSum > currentSum) {
                    if (newSum == maximumNumberOfIntersectingIntervals) {
                        maxIntervalLeftEndpoint = endpoint.getLeft();
                    }
                } else {
                    if (currentSum == maximumNumberOfIntersectingIntervals) {
                        maximumOverlaps.add(new Interval(maxIntervalLeftEndpoint, endpoint.getLeft()));
                    }
                }

                currentSum = newSum;
            }

            return maximumOverlaps;
        }
    }

}