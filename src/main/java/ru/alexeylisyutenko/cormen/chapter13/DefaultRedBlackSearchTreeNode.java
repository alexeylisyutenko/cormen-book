package ru.alexeylisyutenko.cormen.chapter13;

public class DefaultRedBlackSearchTreeNode<K> implements RedBlackSearchTreeNode<K> {

    private RedBlackSearchTreeNode<K> parent;
    private RedBlackSearchTreeNode<K> left;
    private RedBlackSearchTreeNode<K> right;
    private K key;
    private RedBlackTreeNodeColor color;

    public DefaultRedBlackSearchTreeNode() {
    }

    public DefaultRedBlackSearchTreeNode(RedBlackSearchTreeNode<K> parent, RedBlackSearchTreeNode<K> left, RedBlackSearchTreeNode<K> right, K key, RedBlackTreeNodeColor color) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.key = key;
        this.color = color;
    }

    @Override
    public RedBlackSearchTreeNode<K> getParent() {
        return parent;
    }

    @Override
    public void setParent(RedBlackSearchTreeNode<K> parent) {
        this.parent = parent;
    }

    @Override
    public RedBlackSearchTreeNode<K> getLeft() {
        return left;
    }

    @Override
    public void setLeft(RedBlackSearchTreeNode<K> left) {
        this.left = left;
    }

    @Override
    public RedBlackSearchTreeNode<K> getRight() {
        return right;
    }

    @Override
    public void setRight(RedBlackSearchTreeNode<K> right) {
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
        return key + (color == RedBlackTreeNodeColor.RED ? "r" : "b");
    }

}
