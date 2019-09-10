package ru.alexeylisyutenko.cormen.chapter13;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;

public interface RedBlackSearchTreeNode<K> extends BinarySearchTreeNode<K, RedBlackSearchTreeNode<K>> {
    RedBlackTreeNodeColor getColor();

    void setColor(RedBlackTreeNodeColor color);
}
