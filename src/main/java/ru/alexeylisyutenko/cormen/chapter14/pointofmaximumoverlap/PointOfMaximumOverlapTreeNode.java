package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.base.RedBlackBasedSearchTreeNode;

public interface PointOfMaximumOverlapTreeNode extends RedBlackBasedSearchTreeNode<Integer, PointOfMaximumOverlapTreeNode> {
    int getValue();

    void setValue(int value);

    int getSum();

    void setSum(int sum);

    int getLowerChainSum();

    void setLowerChainSum(int lowerChainSum);
}
