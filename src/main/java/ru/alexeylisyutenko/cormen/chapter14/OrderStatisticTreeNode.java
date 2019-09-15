package ru.alexeylisyutenko.cormen.chapter14;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;
import ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor;

public interface OrderStatisticTreeNode<K> extends BinarySearchTreeNode<K, OrderStatisticTreeNode<K>> {
    RedBlackTreeNodeColor getColor();

    void setColor(RedBlackTreeNodeColor color);

    int getSize();

    void setSize(int size);
}
