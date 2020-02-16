package ru.alexeylisyutenko.cormen.chapter14.mingaptree;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedSearchTreeNode;

import java.io.Serializable;

public class DefaultMinGapTreeNode extends AbstractRedBlackBasedSearchTreeNode<Integer, MinGapTreeNode> implements MinGapTreeNode {
    private int minimum;
    private int maximum;
    private int minimumGap;

    @Override
    public int getMinimum() {
        return minimum;
    }

    @Override
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    @Override
    public int getMaximum() {
        return maximum;
    }

    @Override
    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    @Override
    public int getMinimumGap() {
        return minimumGap;
    }

    @Override
    public void setMinimumGap(int minimumGap) {
        this.minimumGap = minimumGap;
    }

    @Override
    public String toString() {
        String minimumGapString = minimumGap == Integer.MAX_VALUE ? "i" : minimumGap + "";
        return String.format("%d:%s:%d:%d ", key, minimumGapString, minimum, maximum);
    }
}
