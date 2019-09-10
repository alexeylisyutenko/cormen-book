package ru.alexeylisyutenko.cormen.chapter12.base;

public abstract class AbstractBinarySearchTree<K extends Comparable<K>, T extends BinarySearchTreeNode<K, T>> implements BinarySearchTree<K> {
    protected T root;

    protected abstract T getNil();


}
