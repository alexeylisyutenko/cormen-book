package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter14.intervaltree.Interval;

import static org.junit.jupiter.api.Assertions.*;

class DefaultPointOfMaximumOverlapTreeTest {

    private PointOfMaximumOverlapTree tree;

    @BeforeEach
    void setup() {
        tree = new DefaultPointOfMaximumOverlapTree();
    }

    @Test
    void demo() {
        tree.intervalInsert(new Interval(6, 12));
        tree.print();
    }

}