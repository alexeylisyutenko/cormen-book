package ru.alexeylisyutenko.cormen.chapter14.intervaltree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter14.mingaptree.DefaultMinGapTree;
import ru.alexeylisyutenko.cormen.chapter14.mingaptree.MinGapTree;

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
    }

}