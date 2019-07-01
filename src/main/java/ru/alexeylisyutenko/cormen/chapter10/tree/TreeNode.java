package ru.alexeylisyutenko.cormen.chapter10.tree;

public interface TreeNode {

    TreeNode getParent();

    TreeNode getLeft();

    TreeNode getRight();

    int getKey();

}
