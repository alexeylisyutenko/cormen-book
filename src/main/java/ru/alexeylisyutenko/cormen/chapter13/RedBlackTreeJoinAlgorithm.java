package ru.alexeylisyutenko.cormen.chapter13;

public class RedBlackTreeJoinAlgorithm {
    /**
     * This algorithm solves problem 13-2 form Cormen.
     * Every key in tree 2 must be less or equal than all keys in tree 1.
     */
    public static <K extends Comparable<K>> RedBlackBinarySearchTree<K> join(RedBlackBinarySearchTree<K> tree1, RedBlackBinarySearchTree<K> tree2, K middleKey) {
        if (tree1.getBlackHeight() >= tree2.getBlackHeight()) {
            RedBlackSearchTreeNode<K> maxBlackNode = tree1.findMaxBlackNode(tree2.getBlackHeight());

            RedBlackSearchTreeNode<K> subtreeRoot = new DefaultRedBlackSearchTreeNode<>();
            subtreeRoot.setKey(middleKey);
            subtreeRoot.setLeft(maxBlackNode);
            subtreeRoot.setRight(tree2.getRoot());
            subtreeRoot.setColor(RedBlackTreeNodeColor.RED);

            tree1.transplant(maxBlackNode, subtreeRoot);
            tree1.insertFixup(subtreeRoot);

            tree2.clear();

            return tree1;
        } else {
            RedBlackSearchTreeNode<K> minBlackNode = tree2.findMinBlackNode(tree1.getBlackHeight());

            RedBlackSearchTreeNode<K> subtreeRoot = new DefaultRedBlackSearchTreeNode<>();
            subtreeRoot.setKey(middleKey);
            subtreeRoot.setLeft(tree1.getRoot());
            subtreeRoot.setRight(minBlackNode);
            subtreeRoot.setColor(RedBlackTreeNodeColor.RED);

            tree2.transplant(minBlackNode, subtreeRoot);
            tree2.insertFixup(subtreeRoot);

            tree1.clear();

            return tree2;
        }
    }
}
