package ru.alexeylisyutenko.cormen.chapter10.tree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreePrintConstantExtraSpaceTest {

    TreeNode rootNode;

    @BeforeEach
    void constructDemoTree() {
        DefaultTreeNode node8 = new DefaultTreeNode(null, null, 8);
        DefaultTreeNode node9 = new DefaultTreeNode(null, null, 9);

        DefaultTreeNode node4 = new DefaultTreeNode(null, null, 4);
        DefaultTreeNode node5 = new DefaultTreeNode(null, null, 5);
        DefaultTreeNode node6 = new DefaultTreeNode(node8, null, 6);
        DefaultTreeNode node7 = new DefaultTreeNode(null, node9, 7);

        DefaultTreeNode node2 = new DefaultTreeNode(node4, node5, 2);
        DefaultTreeNode node3 = new DefaultTreeNode(node6, node7, 3);

        DefaultTreeNode node1 = new DefaultTreeNode(null, node2, node3, 1);

        rootNode = node1;
    }

    @Test
    void printTreeDemo() {
        TreePrintConstantExtraSpace.printTree(rootNode);

    }

}