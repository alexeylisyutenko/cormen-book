package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedSearchTreeNode;

public class DefaultPointOfMaximumOverlapTreeNode extends AbstractRedBlackBasedSearchTreeNode<Integer, PointOfMaximumOverlapTreeNode> implements PointOfMaximumOverlapTreeNode {
    private int value;
    private int sum;
    private int pointOfMaximumOverlap;
    private int maximumOverlappingIntervals;


    private int leftNegativeChain;
    private int positiveValueSum;
    private int negativeValueSum;

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
    public int getPointOfMaximumOverlap() {
        return pointOfMaximumOverlap;
    }

    @Override
    public void setPointOfMaximumOverlap(int pointOfMaximumOverlap) {
        this.pointOfMaximumOverlap = pointOfMaximumOverlap;
    }

    @Override
    public int getMaximumOverlappingIntervals() {
        return maximumOverlappingIntervals;
    }

    @Override
    public void setMaximumOverlappingIntervals(int maximumOverlappingIntervals) {
        this.maximumOverlappingIntervals = maximumOverlappingIntervals;
    }

    @Override
    public int getLeftNegativeChain() {
        return leftNegativeChain;
    }

    @Override
    public void setLeftNegativeChain(int leftNegativeChain) {
        this.leftNegativeChain = leftNegativeChain;
    }

    @Override
    public int getPositiveValueSum() {
        return positiveValueSum;
    }

    @Override
    public void setPositiveValueSum(int positiveValueSum) {
        this.positiveValueSum = positiveValueSum;
    }

    @Override
    public int getNegativeValueSum() {
        return negativeValueSum;
    }

    @Override
    public void setNegativeValueSum(int negativeValueSum) {
        this.negativeValueSum = negativeValueSum;
    }

    @Override
    public String toString() {
        String maximumOverlappingIntervalsString = maximumOverlappingIntervals == Integer.MIN_VALUE ? "U" : maximumOverlappingIntervals + "";
        String pointOfMaximumOverlapString = pointOfMaximumOverlap == Integer.MIN_VALUE ? "U" : pointOfMaximumOverlap + "";
        return String.format("%d:%s:%d:%s:%s:%d ", key, value == 1 ? "+" : "-", sum, maximumOverlappingIntervalsString, pointOfMaximumOverlapString, leftNegativeChain);
    }
}
