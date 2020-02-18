package ru.alexeylisyutenko.cormen.chapter14.overlappedrectangles;

import org.apache.commons.lang3.tuple.Pair;
import ru.alexeylisyutenko.cormen.chapter14.intervaltree.DefaultIntervalTree;
import ru.alexeylisyutenko.cormen.chapter14.intervaltree.Interval;
import ru.alexeylisyutenko.cormen.chapter14.intervaltree.IntervalTree;

import java.util.*;

public final class OverlappedRectanglesAlgorithm {
    private OverlappedRectanglesAlgorithm() {
    }

    /**
     * Checks if there are two rectangles that overlap in a set. Exercise 14.3-7 form Cormen.
     *
     * @param rectangles set of rectangles we're checking
     * @return true - there are rectangles that overlap, false - there are no such rectangles
     */
    public static boolean containsOverlappedRectangles(Set<Rectangle> rectangles) {
        Objects.requireNonNull(rectangles, "rectangles cannot be null");
        if (rectangles.size() < 2) {
            return false;
        }

        // Populate a list with low X coordinates of the rectangles and a list with high X coordinates of the rectangles.
        List<Pair<Integer, Interval>> lowXCoordinates = new ArrayList<>();
        List<Pair<Integer, Interval>> highXCoordinates = new ArrayList<>();
        for (Rectangle rectangle : rectangles) {
            lowXCoordinates.add(Pair.of(rectangle.getxLow(), rectangle.getVerticalInterval()));
            highXCoordinates.add(Pair.of(rectangle.getxHigh(), rectangle.getVerticalInterval()));
        }

        // Sort this low and high coordinates.
        lowXCoordinates.sort(Comparator.comparing(Pair::getLeft));
        highXCoordinates.sort(Comparator.comparing(Pair::getLeft));

        IntervalTree intervalTree = new DefaultIntervalTree();

        int lowIndex = 0;
        int highIndex = 0;

        while (lowIndex < rectangles.size()) {
            int currentLow = lowXCoordinates.get(lowIndex).getLeft();
            int currentHigh = highXCoordinates.get(highIndex).getLeft();

            if (currentLow <= currentHigh) {

                Interval lowVerticalInterval = lowXCoordinates.get(lowIndex).getRight();
                boolean overlapFound = intervalTree.intervalSearchOverlapping(lowVerticalInterval).isPresent();
                if (overlapFound) {
                    return true;
                } else {
                    intervalTree.intervalInsert(lowVerticalInterval);
                }

                lowIndex++;
            } else {
                Interval highVerticalInterval = highXCoordinates.get(highIndex).getRight();
                intervalTree.intervalDelete(highVerticalInterval);
                highIndex++;
            }
        }

        return false;
    }
}
