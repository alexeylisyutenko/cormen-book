package ru.alexeylisyutenko.cormen.chapter14.intervaltree;

import ru.alexeylisyutenko.cormen.chapter14.base.RedBlackBasedSearchTreeNode;

public interface IntervalTreeNode extends RedBlackBasedSearchTreeNode<Interval, IntervalTreeNode> {
    void setMax(int max);

    int getMax();
}
