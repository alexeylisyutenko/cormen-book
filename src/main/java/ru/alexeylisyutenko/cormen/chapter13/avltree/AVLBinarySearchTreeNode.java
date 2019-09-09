package ru.alexeylisyutenko.cormen.chapter13.avltree;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;

public interface AVLBinarySearchTreeNode<K> extends BinarySearchTreeNode<K> {
    AVLBinarySearchTreeNode<K> getParent();

    void setParent(AVLBinarySearchTreeNode<K> parent);

    AVLBinarySearchTreeNode<K> getLeft();

    void setLeft(AVLBinarySearchTreeNode<K> left);

    AVLBinarySearchTreeNode<K> getRight();

    void setRight(AVLBinarySearchTreeNode<K> right);

    K getKey();

    void setKey(K key);

    int getHeight();

    void setHeight(int height);

    void incrementHeight();

    void decrementHeight();
}
