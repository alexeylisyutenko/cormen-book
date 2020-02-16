package ru.alexeylisyutenko.cormen.chapter14.intervaltree;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedSearchTreeNode;

public class DefaultIntervalTreeNode extends AbstractRedBlackBasedSearchTreeNode<Interval, IntervalTreeNode> implements IntervalTreeNode {
    private int max;

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public void setMax(int max) {
        this.max = max;
    }
}
