package ru.alexeylisyutenko.cormen.chapter14.base;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTreeNode;
import ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor;

public abstract class AbstractRedBlackBasedSearchTreeNode<K, N extends RedBlackBasedSearchTreeNode<K, N>>
        extends AbstractBinarySearchTreeNode<K, N> implements RedBlackBasedSearchTreeNode<K, N> {

    protected RedBlackTreeNodeColor color;

    @Override
    public RedBlackTreeNodeColor getColor() {
        return color;
    }

    @Override
    public void setColor(RedBlackTreeNodeColor color) {
        this.color = color;
    }
}
