package ru.alexeylisyutenko.cormen.chapter14.intervaltree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class DefaultIntervalTreeTest {

    private IntervalTree tree;

    @BeforeEach
    void setup() {
        tree = new DefaultIntervalTree();
    }

    @Test
    void demo() {
        tree.intervalInsert(new Interval(0, 3));
        tree.intervalInsert(new Interval(5, 8));
        tree.intervalInsert(new Interval(6, 10));
        tree.intervalInsert(new Interval(8, 9));
        tree.intervalInsert(new Interval(15, 23));
        tree.intervalInsert(new Interval(16, 21));
        tree.intervalInsert(new Interval(17, 19));
        tree.intervalInsert(new Interval(19, 20));
        tree.intervalInsert(new Interval(25, 30));
        tree.intervalInsert(new Interval(26, 26));

        tree.print();
        System.out.println();

        System.out.println(tree.intervalSearchOverlapping(new Interval(26, 26)));
    }

    @Test
    void intervalSearchOverlapping() {
        tree.intervalInsert(new Interval(0, 3));
        tree.intervalInsert(new Interval(5, 8));
        tree.intervalInsert(new Interval(6, 10));
        tree.intervalInsert(new Interval(8, 9));
        tree.intervalInsert(new Interval(15, 23));
        tree.intervalInsert(new Interval(16, 21));
        tree.intervalInsert(new Interval(17, 19));
        tree.intervalInsert(new Interval(19, 20));
        tree.intervalInsert(new Interval(25, 30));
        tree.intervalInsert(new Interval(26, 26));

        Optional<Interval> intervalOptional1 = tree.intervalSearchOverlapping(new Interval(22, 25));
        assertTrue(intervalOptional1.isPresent());
        assertEquals(15, intervalOptional1.get().getLow());
        assertEquals(23, intervalOptional1.get().getHigh());

        Optional<Interval> intervalOptional2 = tree.intervalSearchOverlapping(new Interval(11, 13));
        assertFalse(intervalOptional2.isPresent());

        Optional<Interval> intervalOptional3 = tree.intervalSearchOverlapping(new Interval(30, 30));
        assertTrue(intervalOptional3.isPresent());
        assertEquals(25, intervalOptional3.get().getLow());
        assertEquals(30, intervalOptional3.get().getHigh());
    }

    @Test
    void intervalContains() {
        List<Interval> intervals = List.of(
                new Interval(0, 3),
                new Interval(5, 8),
                new Interval(6, 10),
                new Interval(8, 9),
                new Interval(15, 23),
                new Interval(16, 21),
                new Interval(17, 19),
                new Interval(19, 20),
                new Interval(25, 30),
                new Interval(26, 26)
        );

        for (Interval interval : intervals) {
            assertFalse(tree.intervalContains(interval));
        }

        for (Interval interval : intervals) {
            tree.intervalInsert(interval);
        }

        for (Interval interval : intervals) {
            assertTrue(tree.intervalContains(interval));
        }

        assertFalse(tree.intervalContains(new Interval(5, 9)));
        assertFalse(tree.intervalContains(new Interval(0, 30)));
        assertFalse(tree.intervalContains(new Interval(17, 21)));
        assertFalse(tree.intervalContains(new Interval(24, 30)));
        assertFalse(tree.intervalContains(new Interval(8, 60)));
    }

    @Test
    void intervalDelete() {
        List<Interval> intervals = List.of(
                new Interval(0, 3),
                new Interval(5, 8),
                new Interval(6, 10),
                new Interval(8, 9),
                new Interval(15, 23),
                new Interval(16, 21),
                new Interval(17, 19),
                new Interval(19, 20),
                new Interval(25, 30),
                new Interval(26, 26)
        );
        for (Interval interval : intervals) {
            tree.intervalInsert(interval);
        }

        for (int i = 0; i < intervals.size(); i++) {
            tree.intervalDelete(intervals.get(i));

            for (int j = 0; j < intervals.size(); j++) {
                if (j <= i) {
                    assertFalse(tree.intervalContains(intervals.get(j)));
                } else {
                    assertTrue(tree.intervalContains(intervals.get(j)));
                }
            }

        }
    }

}