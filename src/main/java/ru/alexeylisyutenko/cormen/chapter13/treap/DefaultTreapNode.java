package ru.alexeylisyutenko.cormen.chapter13.treap;

public class DefaultTreapNode<K> implements TreapNode<K> {

    private TreapNode<K> parent;
    private TreapNode<K> left;
    private TreapNode<K> right;
    private K key;
    private int priority;

    @Override
    public TreapNode<K> getParent() {
        return parent;
    }

    public void setParent(TreapNode<K> parent) {
        this.parent = parent;
    }

    @Override
    public TreapNode<K> getLeft() {
        return left;
    }

    public void setLeft(TreapNode<K> left) {
        this.left = left;
    }

    @Override
    public TreapNode<K> getRight() {
        return right;
    }

    public void setRight(TreapNode<K> right) {
        this.right = right;
    }

    @Override
    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return key.toString();
    }
}
