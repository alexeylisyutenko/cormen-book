package ru.alexeylisyutenko.cormen.chapter13;

public interface RedBlackTreeNode<K> {

    RedBlackTreeNode<K> getParent();

    void setParent(RedBlackTreeNode<K> parent);

    RedBlackTreeNode<K> getLeft();

    void setLeft(RedBlackTreeNode<K> left);

    RedBlackTreeNode<K> getRight();

    void setRight(RedBlackTreeNode<K> right);

    K getKey();

    void setKey(K key);

    RedBlackTreeNodeColor getColor();

    void setColor(RedBlackTreeNodeColor color);

}
