package ru.alexeylisyutenko.cormen.chapter14.overlappedrectangles;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alexeylisyutenko.cormen.chapter14.overlappedrectangles.OverlappedRectanglesAlgorithm.containsOverlappedRectangles;

class OverlappedRectanglesAlgorithmTest {

    @Test
    void twoXYSeparated() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 5, 0, 5),
                new Rectangle(10, 15, 10, 15)
        );
        assertFalse(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoYOnlySeparated() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 5, 0, 5),
                new Rectangle(3, 10, 10, 15)
        );
        assertFalse(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoOverlapped() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 5, 0, 5),
                new Rectangle(3, 10, 2, 15)
        );
        assertTrue(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoOverlappedYTouching() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 5, 0, 5),
                new Rectangle(3, 10, 5, 15)
        );
        assertTrue(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoXSeparatedYTouching() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 5, 0, 5),
                new Rectangle(10, 15, 5, 15)
        );
        assertFalse(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoOverlappedXTouching() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 5, 0, 5),
                new Rectangle(5, 10, 0, 5)
        );
        assertTrue(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoYSeparatedXTouching() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 5, 0, 5),
                new Rectangle(5, 10, 0, 5)
        );
        assertTrue(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoTheSame() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 5, 0, 5),
                new Rectangle(0, 5, 0, 5)
        );
        assertTrue(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoNarrowXYSeparated() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(0, 0, 0, 5),
                new Rectangle(10, 15, 10, 15)
        );
        assertFalse(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoNarrowYSeparated() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(5, 5, 0, 5),
                new Rectangle(0, 10, 10, 15)
        );
        assertFalse(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoNarrowXSeparated() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(5, 5, 0, 5),
                new Rectangle(10, 15, 0, 5)
        );
        assertFalse(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoNarrowOverlapped() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(5, 5, 0, 10),
                new Rectangle(0, 10, 5, 15)
        );
        assertTrue(containsOverlappedRectangles(rectangles));
    }

    @Test
    void twoNarrowTheSame() {
        Set<Rectangle> rectangles = Set.of(
                new Rectangle(5, 5, 0, 10),
                new Rectangle(5, 5, 0, 10)
        );
        assertTrue(containsOverlappedRectangles(rectangles));
    }

    @RepeatedTest(10000)
    void randomizedTest() {
        int numberOfRectangles = 3;
        Set<Rectangle> rectangles = new HashSet<>();
        for (int i = 0; i < numberOfRectangles; i++) {
            int xLow = RandomUtils.nextInt(0, 1000);
            int xHigh = RandomUtils.nextInt(xLow, 1000);
            int yLow = RandomUtils.nextInt(0, 1000);
            int yHigh = RandomUtils.nextInt(yLow, 1000);

            rectangles.add(new Rectangle(xLow, xHigh, yLow, yHigh));
        }

        assertEquals(containsOverlappedRectanglesSimple(rectangles), containsOverlappedRectangles(rectangles));
    }

    private boolean containsOverlappedRectanglesSimple(Set<Rectangle> rectangles) {
        ArrayList<Rectangle> rectangleList = new ArrayList<>(rectangles);

        for (int i = 0; i < rectangleList.size() - 1; i++) {
            for (int j = i + 1; j < rectangleList.size(); j++) {
                Rectangle r1 = rectangleList.get(i);
                Rectangle r2 = rectangleList.get(j);
                if (r1.getxLow() <= r2.getxHigh()
                        && r2.getxLow() <= r1.getxHigh()
                        && r1.getyLow() <= r2.getyHigh()
                        && r2.getyLow() <= r1.getyHigh()) {

                    return true;
                }
            }
        }

        return false;
    }


}