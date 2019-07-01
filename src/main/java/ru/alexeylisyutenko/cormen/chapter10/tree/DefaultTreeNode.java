package ru.alexeylisyutenko.cormen.chapter10.tree;

import lombok.Builder;
import lombok.Data;

@Data
public class DefaultTreeNode implements TreeNode {
    private TreeNode parent;
    private TreeNode left;
    private TreeNode right;
    private int key;
}
