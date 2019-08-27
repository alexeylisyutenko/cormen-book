package ru.alexeylisyutenko.cormen.chapter12.simplebinarytree;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;

public interface SimpleBinarySearchTreeNode<K> extends BinarySearchTreeNode<K> {

    SimpleBinarySearchTreeNode<K> getParent();

    void setParent(SimpleBinarySearchTreeNode<K> parent);

    SimpleBinarySearchTreeNode<K> getLeft();

    void setLeft(SimpleBinarySearchTreeNode<K> left);

    SimpleBinarySearchTreeNode<K> getRight();

    void setRight(SimpleBinarySearchTreeNode<K> right);

    K getKey();

    void setKey(K key);

}
