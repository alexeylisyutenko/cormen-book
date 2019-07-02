package ru.alexeylisyutenko.cormen.chapter10.tree;

public class DefaultTreeNode implements TreeNode {
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;
    private int key;

    public DefaultTreeNode(TreeNode left, TreeNode right, int key) {
        this(null, left, right, key);
    }

    private void updateParent(TreeNode child) {
        if (child != null) {
            child.setParent(this);
        }
    }

    public DefaultTreeNode(TreeNode parent, TreeNode left, TreeNode right, int key) {
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.key = key;
        updateParent(left);
        updateParent(right);
    }

    @Override
    public TreeNode getParent() {
        return parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode getLeft() {
        return left;
    }

    @Override
    public void setLeft(TreeNode left) {
        updateParent(left);
        this.left = left;
    }

    @Override
    public TreeNode getRight() {
        return right;
    }

    @Override
    public void setRight(TreeNode right) {
        updateParent(right);
        this.right = right;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public void setKey(int key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "DefaultTreeNode{" +
                "left=" + left +
                ", right=" + right +
                ", key=" + key +
                '}';
    }
}
