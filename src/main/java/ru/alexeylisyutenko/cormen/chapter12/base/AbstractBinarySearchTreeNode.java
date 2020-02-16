package ru.alexeylisyutenko.cormen.chapter12.base;

public abstract class AbstractBinarySearchTreeNode<K, N extends BinarySearchTreeNode<K, N>> implements BinarySearchTreeNode<K, N> {
    protected N parent;
    protected N left;
    protected N right;
    protected K key;

    @Override
    public N getParent() {
        return parent;
    }

    @Override
    public void setParent(N parent) {
        this.parent = parent;
    }

    @Override
    public N getLeft() {
        return left;
    }

    @Override
    public void setLeft(N left) {
        this.left = left;
    }

    @Override
    public N getRight() {
        return right;
    }

    @Override
    public void setRight(N right) {
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
