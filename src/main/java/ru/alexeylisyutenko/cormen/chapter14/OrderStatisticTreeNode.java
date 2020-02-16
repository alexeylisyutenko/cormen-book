package ru.alexeylisyutenko.cormen.chapter14;

import ru.alexeylisyutenko.cormen.chapter14.base.RedBlackBasedSearchTreeNode;

public interface OrderStatisticTreeNode<K> extends RedBlackBasedSearchTreeNode<K, OrderStatisticTreeNode<K>> {
    int getSize();

    void setSize(int size);
}
