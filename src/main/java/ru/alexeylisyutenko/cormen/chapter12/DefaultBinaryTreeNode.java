package ru.alexeylisyutenko.cormen.chapter12;

public class DefaultBinaryTreeNode<K> implements BinaryTreeNode<K> {

    protected BinaryTreeNode<K> parent;
    protected BinaryTreeNode<K> left;
    protected BinaryTreeNode<K> right;
    protected K key;

    public DefaultBinaryTreeNode(BinaryTreeNode<K> left, BinaryTreeNode<K> right, K key) {
        this(null, left, right, key);
    }

    public DefaultBinaryTreeNode(BinaryTreeNode<K> parent, BinaryTreeNode<K> left, BinaryTreeNode<K> right, K key) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.key = key;
        updateParent(left);
        updateParent(right);
    }

    private void updateParent(BinaryTreeNode<K> child) {
        if (child != null) {
            child.setParent(this);
        }
    }

    @Override
    public BinaryTreeNode<K> getParent() {
        return parent;
    }

    @Override
    public void setParent(BinaryTreeNode<K> parent) {
        this.parent = parent;
    }

    @Override
    public BinaryTreeNode<K> getLeft() {
        return left;
    }

    @Override
    public void setLeft(BinaryTreeNode<K> left) {
        updateParent(left);
        this.left = left;
    }

    @Override
    public BinaryTreeNode<K> getRight() {
        return right;
    }

    @Override
    public void setRight(BinaryTreeNode<K> right) {
        updateParent(right);
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
    public String toString() {
        return key.toString();
    }

}
