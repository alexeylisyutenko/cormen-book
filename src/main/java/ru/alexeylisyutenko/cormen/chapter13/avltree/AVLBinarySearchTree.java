package ru.alexeylisyutenko.cormen.chapter13.avltree;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.utils.BinaryTreePrinter;

import java.util.Objects;
import java.util.function.Consumer;

public class AVLBinarySearchTree<K extends Comparable<K>> implements BinarySearchTree<K> {

    private final static AVLBinarySearchTreeNode NIL;

    static {
        NIL = new DefaultAVLBinarySearchTreeNode();
        NIL.setHeight(0);
    }

    private AVLBinarySearchTreeNode<K> root;

    public AVLBinarySearchTree() {
        this.root = NIL;
    }

    @SuppressWarnings("DuplicatedCode")
    private void rotateRight(AVLBinarySearchTreeNode<K> yNode) {
        if (yNode.getLeft() == NIL) {
            throw new IllegalStateException("Left child of a yNode must exist for right rotation");
        }
        AVLBinarySearchTreeNode<K> xNode = yNode.getLeft();

        // Put xNode's right to yNode's left.
        yNode.setLeft(xNode.getRight());
        if (xNode.getRight() != NIL) {
            xNode.getRight().setParent(yNode);
        }

        // Update xNode parent.
        xNode.setParent(yNode.getParent());

        // Update yNode's parent to point to xNode instead of yNode.
        AVLBinarySearchTreeNode<K> yParent = yNode.getParent();
        if (yParent == NIL) {
            root = xNode;
        } else {
            if (yParent.getLeft() == yNode) {
                yParent.setLeft(xNode);
            } else {
                yParent.setRight(xNode);
            }
        }

        // Put yNode on xNode's right.
        xNode.setRight(yNode);
        yNode.setParent(xNode);
    }

    @SuppressWarnings("DuplicatedCode")
    private void rotateLeft(AVLBinarySearchTreeNode<K> xNode) {
        if (xNode.getRight() == NIL) {
            throw new IllegalStateException("Right child of a xNode must exist for left rotation");
        }
        AVLBinarySearchTreeNode<K> yNode = xNode.getRight();

        // Put yNode's left to xNode's right.
        xNode.setRight(yNode.getLeft());
        if (yNode.getLeft() != NIL) {
            yNode.getLeft().setParent(xNode);
        }

        // Update yNode parent.
        yNode.setParent(xNode.getParent());

        // Update xNode's parent to point to yNode instead of xNode.
        if (xNode.getParent() == NIL) {
            root = yNode;
        } else {
            AVLBinarySearchTreeNode<K> xParent = xNode.getParent();
            if (xParent.getLeft() == xNode) {
                xParent.setLeft(yNode);
            } else {
                xParent.setRight(yNode);
            }
        }

        // Put xNode to yNode's left.
        yNode.setLeft(xNode);
        xNode.setParent(yNode);
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

    @Override
    public void inorderWalk(Consumer<K> consumer) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public void preorderWalk(Consumer<K> consumer) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public void postorderWalk(Consumer<K> consumer) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public int size() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public boolean contains(K key) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public K getMinimum() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public K getMaximum() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public K getSuccessorOf(K key) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public K getPredecessorOf(K key) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public void clear() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    void print() {
        BinaryTreePrinter.printNode(root, NIL);
    }
}
