package ru.alexeylisyutenko.cormen.chapter10.tree;

import lombok.Value;

@Value
public class DefaultTreeNode implements TreeNode {
    private final TreeNode parent;
    private final TreeNode left;
    private final TreeNode right;
    private final int key;
}
