package ru.alexeylisyutenko.cormen.chapter13.treap;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;

public interface TreapNode<K> extends BinarySearchTreeNode<K> {
    int getPriority();

    void setPriority(int priority);

    @Override
    TreapNode<K> getParent();

    @Override
    TreapNode<K> getLeft();

    @Override
    TreapNode<K> getRight();
}
