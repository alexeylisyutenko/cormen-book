package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedSearchTreeNode;

public class DefaultPointOfMaximumOverlapTreeNode extends AbstractRedBlackBasedSearchTreeNode<PointOfMaximumOverlapTreeKey, PointOfMaximumOverlapTreeNode> implements PointOfMaximumOverlapTreeNode {
    private int value;
    private int sum;
    private int maximumSum;
    private int pointOfMaximumSum;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getSum() {
        return sum;
    }

    @Override
    public void setSum(int sum) {
        this.sum = sum;
    }

    @Override
    public int getMaximumSum() {
        return maximumSum;
    }

    @Override
    public void setMaximumSum(int maximumSum) {
        this.maximumSum = maximumSum;
    }

    @Override
    public int getPointOfMaximumSum() {
        return pointOfMaximumSum;
    }

    @Override
    public void setPointOfMaximumSum(int pointOfMaximumSum) {
        this.pointOfMaximumSum = pointOfMaximumSum;
    }

    @Override
    public String toString() {
        return String.format("%d:%s:%d:%d:%d ", key.getEndpoint(), value == 1 ? "+" : "-", sum, maximumSum, pointOfMaximumSum);
    }
}
