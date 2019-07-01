package ru.alexeylisyutenko.cormen.chapter10.tree;

public interface TreeNode {

    TreeNode getParent();

    void setParent(TreeNode parent);

    TreeNode getLeft();

    void setLeft(TreeNode left);

    TreeNode getRight();

    void setRight(TreeNode right);

    int getKey();

    void setKey(int key);

}
