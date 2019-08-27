package ru.alexeylisyutenko.cormen.chapter13;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;

public interface RedBlackSearchTreeNode<K> extends BinarySearchTreeNode<K> {

    RedBlackSearchTreeNode<K> getParent();

    void setParent(RedBlackSearchTreeNode<K> parent);

    RedBlackSearchTreeNode<K> getLeft();

    void setLeft(RedBlackSearchTreeNode<K> left);

    RedBlackSearchTreeNode<K> getRight();

    void setRight(RedBlackSearchTreeNode<K> right);

    K getKey();

    void setKey(K key);

    RedBlackTreeNodeColor getColor();

    void setColor(RedBlackTreeNodeColor color);

}
