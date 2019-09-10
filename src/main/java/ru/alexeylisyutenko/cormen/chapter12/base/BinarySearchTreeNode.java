package ru.alexeylisyutenko.cormen.chapter12.base;

public interface BinarySearchTreeNode<K, N extends BinarySearchTreeNode<K, N>> {
    N getParent();

    void setParent(N parent);

    N getLeft();

    void setLeft(N left);

    N getRight();

    void setRight(N right);

    K getKey();

    void setKey(K key);
}
