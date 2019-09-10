package ru.alexeylisyutenko.cormen.chapter13.avltree;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTreeNode;

public class DefaultAVLBinarySearchTreeNode<K> extends AbstractBinarySearchTreeNode<K, AVLBinarySearchTreeNode<K>> implements AVLBinarySearchTreeNode<K> {
    private int height;

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void incrementHeight() {
        height++;
    }

    @Override
    public void decrementHeight() {
        height--;
    }

    @Override
    public String toString() {
        return key + ":" + height;
    }
}
