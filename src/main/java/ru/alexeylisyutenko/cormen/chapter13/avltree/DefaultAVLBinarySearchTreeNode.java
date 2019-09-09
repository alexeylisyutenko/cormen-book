package ru.alexeylisyutenko.cormen.chapter13.avltree;

public class DefaultAVLBinarySearchTreeNode<K> implements AVLBinarySearchTreeNode<K> {

    private AVLBinarySearchTreeNode<K> parent;
    private AVLBinarySearchTreeNode<K> left;
    private AVLBinarySearchTreeNode<K> right;
    private K key;
    private int height;

    @Override
    public AVLBinarySearchTreeNode<K> getParent() {
        return parent;
    }

    @Override
    public void setParent(AVLBinarySearchTreeNode<K> parent) {
        this.parent = parent;
    }

    @Override
    public AVLBinarySearchTreeNode<K> getLeft() {
        return left;
    }

    @Override
    public void setLeft(AVLBinarySearchTreeNode<K> left) {
        this.left = left;
    }

    @Override
    public AVLBinarySearchTreeNode<K> getRight() {
        return right;
    }

    @Override
    public void setRight(AVLBinarySearchTreeNode<K> right) {
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
