package ru.alexeylisyutenko.cormen.chapter14;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedSearchTreeNode;

public class DefaultOrderStatisticTreeNode<K> extends AbstractRedBlackBasedSearchTreeNode<K, OrderStatisticTreeNode<K>> implements OrderStatisticTreeNode<K> {
    private int size;

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return key + ":" + size;
    }
}
