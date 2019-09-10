package ru.alexeylisyutenko.cormen.chapter12.base;

public interface BinarySearchTreeNode<K, T extends BinarySearchTreeNode<K, T>> {
    T getParent();

    void setParent(T parent);

    T getLeft();

    void setLeft(T left);

    T getRight();

    void setRight(T right);

    K getKey();

    void setKey(K key);
}
