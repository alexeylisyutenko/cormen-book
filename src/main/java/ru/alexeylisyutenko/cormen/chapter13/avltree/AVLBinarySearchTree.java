package ru.alexeylisyutenko.cormen.chapter13.avltree;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTree;

import java.util.Objects;

public class AVLBinarySearchTree<K extends Comparable<K>> extends AbstractBinarySearchTree<K, AVLBinarySearchTreeNode<K>> {

    private final static AVLBinarySearchTreeNode NIL;

    static {
        NIL = new DefaultAVLBinarySearchTreeNode();
        NIL.setHeight(0);
    }

    public AVLBinarySearchTree() {
        this.root = NIL;
    }

    @Override
    protected AVLBinarySearchTreeNode<K> getNil() {
        return NIL;
    }

    @Override
    public void insert(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        AVLBinarySearchTreeNode<K> previous = NIL;
        AVLBinarySearchTreeNode<K> current = root;
        while (current != NIL) {
            previous = current;
            if (key.compareTo(current.getKey()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        AVLBinarySearchTreeNode<K> nodeToInsert = new DefaultAVLBinarySearchTreeNode<>();
        nodeToInsert.setLeft(NIL);
        nodeToInsert.setRight(NIL);
        nodeToInsert.setKey(key);
        nodeToInsert.setHeight(1);

        if (previous == NIL) {
            nodeToInsert.setParent(NIL);
            root = nodeToInsert;
        } else if (key.compareTo(previous.getKey()) < 0) {
            nodeToInsert.setParent(previous);
            previous.setLeft(nodeToInsert);
        } else {
            nodeToInsert.setParent(previous);
            previous.setRight(nodeToInsert);
        }

        // Update ancestors' heights if needed and find X node.
        // Node X is a node whose left subtree height and right subtree height have difference more or equal than 2.
        AVLBinarySearchTreeNode<K> xNode = NIL;
        AVLBinarySearchTreeNode<K> zNode = nodeToInsert;
        while (zNode.getParent() != NIL) {
            // Find current node's sibling.
            AVLBinarySearchTreeNode<K> sibling;
            if (zNode.getParent().getLeft() == zNode) {
                sibling = zNode.getParent().getRight();
            } else {
                sibling = zNode.getParent().getLeft();
            }

            if (Math.abs(zNode.getHeight() - sibling.getHeight()) >= 2) {
                // We found a node X which cases problems in subtree heights.
                // There is no need to update ancestor's heights further.
                xNode = zNode.getParent();
                break;
            } else if (sibling.getHeight() < zNode.getHeight()) {
                zNode.getParent().incrementHeight();
            } else {
                // There is no need to update heights. Everything is fine.
                break;
            }

            zNode = zNode.getParent();
        }

        // Fix heights if xNode is not NIL.
        if (xNode != NIL) {
            balance(xNode);
        }
    }

    private void balance(AVLBinarySearchTreeNode<K> xNode) {
        if (xNode.getLeft().getHeight() > xNode.getRight().getHeight()) {
            AVLBinarySearchTreeNode<K> leftNode = xNode.getLeft();

            // Case 1.
            if (leftNode.getLeft().getHeight() < leftNode.getRight().getHeight()) {
                int m = leftNode.getHeight();
                leftNode.getRight().setHeight(m);
                leftNode.getLeft().setHeight(m - 2);
                leftNode.setHeight(m - 1);

                rotateLeft(leftNode);

                // Update left node because xNode's left child was changed after rotation.
                leftNode = xNode.getLeft();
            }

            // Case 2.
            int m = leftNode.getHeight();
            xNode.setHeight(m - 1);
            rotateRight(xNode);
        } else {
            AVLBinarySearchTreeNode<K> rightNode = xNode.getRight();

            // Case 1.
            if (rightNode.getRight().getHeight() < rightNode.getLeft().getHeight()) {
                int m = rightNode.getHeight();
                rightNode.getLeft().setHeight(m);
                rightNode.getRight().setHeight(m - 2);
                rightNode.setHeight(m - 1);

                rotateRight(rightNode);

                // Update right node because xNode's right child was changed after rotation.
                rightNode = xNode.getRight();
            }

            // Case 2.
            int m = rightNode.getHeight();
            xNode.setHeight(m - 1);
            rotateLeft(xNode);
        }
    }

    @Override
    public void delete(K key) {
        throw new IllegalArgumentException("Not implemented yet");
    }
}
