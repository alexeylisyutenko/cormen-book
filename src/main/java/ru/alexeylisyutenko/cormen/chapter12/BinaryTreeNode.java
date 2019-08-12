package ru.alexeylisyutenko.cormen.chapter12;

public interface BinaryTreeNode<K> {

    BinaryTreeNode<K> getParent();

    void setParent(BinaryTreeNode<K> parent);

    BinaryTreeNode<K> getLeft();

    void setLeft(BinaryTreeNode<K> left);

    BinaryTreeNode<K> getRight();

    void setRight(BinaryTreeNode<K> right);

    K getKey();

    void setKey(K key);

}
