package ru.alexeylisyutenko.cormen.chapter12;

public final class BinaryTreeNodesDemoFactory {

    public static BinaryTreeNode<Integer> createDemoBinarySearchTree() {
        // Third level.
        BinaryTreeNode<Integer> node4 = new DefaultBinaryTreeNode<>(null, null, 2);
        BinaryTreeNode<Integer> node5 = new DefaultBinaryTreeNode<>(null, null, 5);
        BinaryTreeNode<Integer> node6 = new DefaultBinaryTreeNode<>(null, null, 8);

        // Second level.
        BinaryTreeNode<Integer> node2 = new DefaultBinaryTreeNode<>(node4, node5, 5);
        BinaryTreeNode<Integer> node3 = new DefaultBinaryTreeNode<>(null, node6, 7);

        // First level.
        BinaryTreeNode<Integer> node1 = new DefaultBinaryTreeNode<>(node2, node3, 6);

        return node1;
    }

    public static BinaryTreeNode<Integer> createSingleNodeDemoBinarySearchTree() {
        return new DefaultBinaryTreeNode<>(null, null, 1);
    }

    public static BinaryTreeNode<Integer> createEmptyNodeDemoBinarySearchTree() {
        return null;
    }

    public static BinaryTreeNode<Integer> createLeftDegenerateDemoBinarySearchTree() {
        BinaryTreeNode<Integer> node6 = new DefaultBinaryTreeNode<>(null, null, 1);
        BinaryTreeNode<Integer> node5 = new DefaultBinaryTreeNode<>(node6, null, 2);
        BinaryTreeNode<Integer> node4 = new DefaultBinaryTreeNode<>(node5, null, 3);
        BinaryTreeNode<Integer> node3 = new DefaultBinaryTreeNode<>(node4, null, 4);
        BinaryTreeNode<Integer> node2 = new DefaultBinaryTreeNode<>(node3, null, 5);
        BinaryTreeNode<Integer> node1 = new DefaultBinaryTreeNode<>(node2, null, 6);
        return node1;
    }

    public static BinaryTreeNode<Integer> createRightDegenerateDemoBinarySearchTree() {
        BinaryTreeNode<Integer> node6 = new DefaultBinaryTreeNode<>(null, null, 6);
        BinaryTreeNode<Integer> node5 = new DefaultBinaryTreeNode<>(null, node6, 5);
        BinaryTreeNode<Integer> node4 = new DefaultBinaryTreeNode<>(null, node5, 4);
        BinaryTreeNode<Integer> node3 = new DefaultBinaryTreeNode<>(null, node4, 3);
        BinaryTreeNode<Integer> node2 = new DefaultBinaryTreeNode<>(null, node3, 2);
        BinaryTreeNode<Integer> node1 = new DefaultBinaryTreeNode<>(null, node2, 1);
        return node1;
    }

}
