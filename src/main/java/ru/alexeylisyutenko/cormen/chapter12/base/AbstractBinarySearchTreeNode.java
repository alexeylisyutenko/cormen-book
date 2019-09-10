package ru.alexeylisyutenko.cormen.chapter12.base;

public abstract class AbstractBinarySearchTreeNode<K, T extends BinarySearchTreeNode<K, T>> implements BinarySearchTreeNode<K, T> {
    protected T parent;
    protected T left;
    protected T right;
    protected K key;

    @Override
    public T getParent() {
        return parent;
    }

    @Override
    public void setParent(T parent) {
        this.parent = parent;
    }

    @Override
    public T getLeft() {
        return left;
    }

    @Override
    public void setLeft(T left) {
        this.left = left;
    }

    @Override
    public T getRight() {
        return right;
    }

    @Override
    public void setRight(T right) {
        this.right = right;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
