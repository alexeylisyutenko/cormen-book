package ru.alexeylisyutenko.cormen.chapter13.treap;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTreeNode;

public class DefaultTreapNode<K> extends AbstractBinarySearchTreeNode<K, TreapNode<K>> implements TreapNode<K> {
    private int priority;

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }
}
