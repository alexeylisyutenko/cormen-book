package ru.alexeylisyutenko.cormen.chapter13.treap;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;

public interface TreapNode<K> extends BinarySearchTreeNode<K, TreapNode<K>> {
    int getPriority();

    void setPriority(int priority);
}
