package ru.alexeylisyutenko.cormen.chapter14;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTreeNode;
import ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor;

public class DefaultOrderStatisticTreeNode<K> extends AbstractBinarySearchTreeNode<K, OrderStatisticTreeNode<K>> implements OrderStatisticTreeNode<K> {
    private RedBlackTreeNodeColor color;
    private int size;

    @Override
    public RedBlackTreeNodeColor getColor() {
        return color;
    }

    @Override
    public void setColor(RedBlackTreeNodeColor color) {
        this.color = color;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return key + ":" + size;
    }
}
