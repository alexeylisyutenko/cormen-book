package ru.alexeylisyutenko.cormen.chapter13;

public class DefaultRedBlackTreeNode<K> implements RedBlackTreeNode<K> {

    private RedBlackTreeNode<K> parent;
    private RedBlackTreeNode<K> left;
    private RedBlackTreeNode<K> right;
    private K key;
    private RedBlackTreeNodeColor color;

    public DefaultRedBlackTreeNode(RedBlackTreeNode<K> parent, RedBlackTreeNode<K> left, RedBlackTreeNode<K> right, K key, RedBlackTreeNodeColor color) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.key = key;
        this.color = color;
    }

    @Override
    public RedBlackTreeNode<K> getParent() {
        return parent;
    }

    @Override
    public void setParent(RedBlackTreeNode<K> parent) {
        this.parent = parent;
    }

    @Override
    public RedBlackTreeNode<K> getLeft() {
        return left;
    }

    @Override
    public void setLeft(RedBlackTreeNode<K> left) {
        this.left = left;
    }

    @Override
    public RedBlackTreeNode<K> getRight() {
        return right;
    }

    @Override
    public void setRight(RedBlackTreeNode<K> right) {
        this.right = right;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public void setKey(K key) {
        this.key = key;
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
        return key.toString() + (color == RedBlackTreeNodeColor.RED ? "r" : "b");
    }

}
