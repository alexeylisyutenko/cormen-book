package ru.alexeylisyutenko.cormen.chapter12.base;

public interface BinarySearchTreeNode<K> {
    BinarySearchTreeNode<K> getParent();

//    void setParent();

    BinarySearchTreeNode<K> getLeft();

    BinarySearchTreeNode<K> getRight();

    K getKey();
}
