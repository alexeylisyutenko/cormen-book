package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.base.RedBlackBasedSearchTreeNode;

public interface PointOfMaximumOverlapTreeNode extends RedBlackBasedSearchTreeNode<PointOfMaximumOverlapTreeKey, PointOfMaximumOverlapTreeNode> {
    int getValue();

    void setValue(int value);

    int getSum();

    void setSum(int sum);

    int getMaximumSum();

    void setMaximumSum(int maximumSum);

    int getPointOfMaximumSum();

    void setPointOfMaximumSum(int pointOfMaximumSum);
}
