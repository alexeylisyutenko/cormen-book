package ru.alexeylisyutenko.cormen.chapter13.avltree;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;

public interface AVLBinarySearchTreeNode<K> extends BinarySearchTreeNode<K, AVLBinarySearchTreeNode<K>> {
    int getHeight();

    void setHeight(int height);

    void incrementHeight();

    void decrementHeight();
}
