package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.base.RedBlackBasedSearchTreeNode;

public interface PointOfMaximumOverlapTreeNode extends RedBlackBasedSearchTreeNode<Integer, PointOfMaximumOverlapTreeNode> {
    int getValue();

    void setValue(int value);

    int getSum();

    void setSum(int sum);

    int getPointOfMaximumOverlap();

    void setPointOfMaximumOverlap(int pointOfMaximumOverlap);

    int getMaximumOverlappingIntervals();

    void setMaximumOverlappingIntervals(int maximumOverlappingIntervals);

    int getLeftNegativeChain();

    void setLeftNegativeChain(int leftNegativeChain);

    int getPositiveValueSum();

    void setPositiveValueSum(int positiveValueSum);

    int getNegativeValueSum();

    void setNegativeValueSum(int negativeValueSum);
}
