package ru.alexeylisyutenko.cormen.chapter14.mingaptree;

import ru.alexeylisyutenko.cormen.chapter14.base.RedBlackBasedSearchTreeNode;

/**
 * A tree node form the exercise 14.3-6 Cormen.
 */
public interface MinGapTreeNode extends RedBlackBasedSearchTreeNode<Integer, MinGapTreeNode> {
    int getMinimum();

    void setMinimum(int minimum);

    int getMaximum();

    void setMaximum(int maximum);

    int getMinimumGap();

    void setMinimumGap(int minimumGap);
}
