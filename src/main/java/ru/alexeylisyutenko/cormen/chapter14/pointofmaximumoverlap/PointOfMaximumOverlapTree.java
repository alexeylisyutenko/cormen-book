package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.intervaltree.Interval;

public interface PointOfMaximumOverlapTree {
    void intervalInsert(Interval interval);

    void intervalDelete(Interval interval);

    int findPom();
}
