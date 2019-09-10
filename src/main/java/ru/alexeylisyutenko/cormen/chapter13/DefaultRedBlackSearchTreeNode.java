package ru.alexeylisyutenko.cormen.chapter13;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTreeNode;

public class DefaultRedBlackSearchTreeNode<K> extends AbstractBinarySearchTreeNode<K, RedBlackSearchTreeNode<K>> implements RedBlackSearchTreeNode<K> {
    private RedBlackTreeNodeColor color;

    public DefaultRedBlackSearchTreeNode() {
    }

    @Override
    public RedBlackTreeNodeColor getColor() {
        return color;
    }

    @Override
    public void setColor(RedBlackTreeNodeColor color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return key + (color == RedBlackTreeNodeColor.RED ? "r" : "b");
    }

}
