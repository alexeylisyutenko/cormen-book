package ru.alexeylisyutenko.cormen.chapter12.simplebinarytree;

public final class BinaryTreeNodesDemoFactory {

    public static SimpleBinarySearchTreeNode<Integer> createDemoBinarySearchTree() {
        // Third level.
        SimpleBinarySearchTreeNode<Integer> node4 = new DefaultSimpleBinarySearchTreeNode<>(null, null, 2);
        SimpleBinarySearchTreeNode<Integer> node5 = new DefaultSimpleBinarySearchTreeNode<>(null, null, 5);
        SimpleBinarySearchTreeNode<Integer> node6 = new DefaultSimpleBinarySearchTreeNode<>(null, null, 8);

        // Second level.
        SimpleBinarySearchTreeNode<Integer> node2 = new DefaultSimpleBinarySearchTreeNode<>(node4, node5, 5);
        SimpleBinarySearchTreeNode<Integer> node3 = new DefaultSimpleBinarySearchTreeNode<>(null, node6, 7);

        // First level.
        SimpleBinarySearchTreeNode<Integer> node1 = new DefaultSimpleBinarySearchTreeNode<>(node2, node3, 6);

        return node1;
    }

    public static SimpleBinarySearchTreeNode<Integer> createSingleNodeDemoBinarySearchTree() {
        return new DefaultSimpleBinarySearchTreeNode<>(null, null, 1);
    }

    public static SimpleBinarySearchTreeNode<Integer> createEmptyNodeDemoBinarySearchTree() {
        return null;
    }

    public static SimpleBinarySearchTreeNode<Integer> createLeftDegenerateDemoBinarySearchTree() {
        SimpleBinarySearchTreeNode<Integer> node6 = new DefaultSimpleBinarySearchTreeNode<>(null, null, 1);
        SimpleBinarySearchTreeNode<Integer> node5 = new DefaultSimpleBinarySearchTreeNode<>(node6, null, 2);
        SimpleBinarySearchTreeNode<Integer> node4 = new DefaultSimpleBinarySearchTreeNode<>(node5, null, 3);
        SimpleBinarySearchTreeNode<Integer> node3 = new DefaultSimpleBinarySearchTreeNode<>(node4, null, 4);
        SimpleBinarySearchTreeNode<Integer> node2 = new DefaultSimpleBinarySearchTreeNode<>(node3, null, 5);
        SimpleBinarySearchTreeNode<Integer> node1 = new DefaultSimpleBinarySearchTreeNode<>(node2, null, 6);
        return node1;
    }

    public static SimpleBinarySearchTreeNode<Integer> createRightDegenerateDemoBinarySearchTree() {
        SimpleBinarySearchTreeNode<Integer> node6 = new DefaultSimpleBinarySearchTreeNode<>(null, null, 6);
        SimpleBinarySearchTreeNode<Integer> node5 = new DefaultSimpleBinarySearchTreeNode<>(null, node6, 5);
        SimpleBinarySearchTreeNode<Integer> node4 = new DefaultSimpleBinarySearchTreeNode<>(null, node5, 4);
        SimpleBinarySearchTreeNode<Integer> node3 = new DefaultSimpleBinarySearchTreeNode<>(null, node4, 3);
        SimpleBinarySearchTreeNode<Integer> node2 = new DefaultSimpleBinarySearchTreeNode<>(null, node3, 2);
        SimpleBinarySearchTreeNode<Integer> node1 = new DefaultSimpleBinarySearchTreeNode<>(null, node2, 1);
        return node1;
    }

}
