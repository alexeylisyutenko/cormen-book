package ru.alexeylisyutenko.cormen.chapter12.simplebinarytree;

public class DefaultSimpleBinarySearchTreeNode<K> implements SimpleBinarySearchTreeNode<K> {

    protected SimpleBinarySearchTreeNode<K> parent;
    protected SimpleBinarySearchTreeNode<K> left;
    protected SimpleBinarySearchTreeNode<K> right;
    protected K key;

    public DefaultSimpleBinarySearchTreeNode(SimpleBinarySearchTreeNode<K> left, SimpleBinarySearchTreeNode<K> right, K key) {
        this(null, left, right, key);
    }

    public DefaultSimpleBinarySearchTreeNode(SimpleBinarySearchTreeNode<K> parent, SimpleBinarySearchTreeNode<K> left, SimpleBinarySearchTreeNode<K> right, K key) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.key = key;
        updateParent(left);
        updateParent(right);
    }

    private void updateParent(SimpleBinarySearchTreeNode<K> child) {
        if (child != null) {
            child.setParent(this);
        }
    }

    @Override
    public SimpleBinarySearchTreeNode<K> getParent() {
        return parent;
    }

    @Override
    public void setParent(SimpleBinarySearchTreeNode<K> parent) {
        this.parent = parent;
    }

    @Override
    public SimpleBinarySearchTreeNode<K> getLeft() {
        return left;
    }

    @Override
    public void setLeft(SimpleBinarySearchTreeNode<K> left) {
        updateParent(left);
        this.left = left;
    }

    @Override
    public SimpleBinarySearchTreeNode<K> getRight() {
        return right;
    }

    @Override
    public void setRight(SimpleBinarySearchTreeNode<K> right) {
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
