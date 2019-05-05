package ru.alexeylisyutenko.cormen.chapter7;

import lombok.Value;
import org.apache.commons.lang3.tuple.Pair;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

public class FuzzySort {

    public static void sort(Interval[] intervals) {
        fuzzyQuickSort(intervals, 0, intervals.length - 1);
    }

    public static void fuzzyQuickSort(Interval[] intervals, int p, int r) {
        while (p < r) {
            System.out.println("Running fuzzyQuickSort()");
            Pair<Integer, Integer> partition = partition(intervals, p, r);
            fuzzyQuickSort(intervals, p, partition.getLeft() - 1);
            p = partition.getRight() + 1;
        }
    }

    public static Pair<Integer, Integer> partition(Interval[] intervals, int p, int r) {
        Interval x = intervals[p];
        int i = p;
        int lt = p;
        int gt = r;
        while (i <= gt) {
            if (overlap(intervals[i], x)) {
                x = new Interval(Math.max(intervals[i].getLeft(), x.getLeft()), Math.min(intervals[i].getRight(), x.getRight()));
                i++;
            } else if (intervals[i].getLeft() < x.getLeft()) {
                exchange(intervals, lt, i);
                i++;
                lt++;
            } else if (intervals[i].getLeft() > x.getLeft()) {
                exchange(intervals, i, gt);
                gt--;
            }
        }
        return Pair.of(lt, gt);
    }

    public static boolean overlap(Interval interval1, Interval interval2) {
        return !(interval1.getRight() < interval2.getLeft() || interval2.getRight() < interval1.getLeft());
    }

    @Value
    public static class Interval {
        private double left;
        private double right;

        @Override
        public String toString() {
            return "(" + left + ", " + right + ")";
        }
    }

}
