package ru.alexeylisyutenko.cormen.chapter13;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.utils.BinaryTreePrinter;

import java.util.function.Consumer;

public class RedBlackBinarySearchTree<K extends Comparable<K>> implements BinarySearchTree<K> {

    private final RedBlackSearchTreeNode<K> nil;

    private RedBlackSearchTreeNode<K> root;

    public RedBlackBinarySearchTree() {
        this.nil = new SentinelRedBlackSearchTreeNode<>();
        this.root = nil;
    }

    private void rotateLeft(RedBlackSearchTreeNode<K> xNode) {
        if (xNode.getRight() == nil) {
            throw new IllegalStateException("Right child of a xNode must exist for left rotation");
        }
        RedBlackSearchTreeNode<K> yNode = xNode.getRight();

        // Put yNode's left to xNode's right.
        xNode.setRight(yNode.getLeft());
        if (yNode.getLeft() != nil) {
            yNode.getLeft().setParent(xNode);
        }

        // Update xNode's parent to point to yNode instead of xNode.
        if (xNode.getParent() == nil) {
            root = yNode;
        } else {
            RedBlackSearchTreeNode<K> xParent = xNode.getParent();
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

    private void rotateRight(RedBlackSearchTreeNode<K> yNode) {
        if (yNode.getLeft() == nil) {
            throw new IllegalStateException("Left child of a yNode must exist for right rotation");
        }
        RedBlackSearchTreeNode<K> xNode = yNode.getLeft();

        // Put xNode's right to yNode's left.
        yNode.setLeft(xNode.getRight());
        if (xNode.getRight() != nil) {
            xNode.getRight().setParent(yNode);
        }

        // Update yNode's parent to point to xNode instead of yNode.
        RedBlackSearchTreeNode<K> yParent = yNode.getParent();
        if (yParent == nil) {
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

    private void insertFixup(RedBlackSearchTreeNode<K> zNode) {

    }

    @Override
    public void insert(K key) {
        RedBlackSearchTreeNode<K> parentNode = nil;
        RedBlackSearchTreeNode<K> currentNode = root;

        while (currentNode != nil) {
            parentNode = currentNode;
            int comparisonResult = key.compareTo(currentNode.getKey());
            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        DefaultRedBlackSearchTreeNode<K> nodeToInsert = new DefaultRedBlackSearchTreeNode<>();
        nodeToInsert.setLeft(nil);
        nodeToInsert.setRight(nil);
        nodeToInsert.setKey(key);
        nodeToInsert.setColor(RedBlackTreeNodeColor.RED);

        if (parentNode == nil) {
            nodeToInsert.setParent(nil);
            root = nodeToInsert;
        } else {
            nodeToInsert.setParent(parentNode);
            int comparisonResult = key.compareTo(parentNode.getKey());
            if (comparisonResult < 0) {
                parentNode.setLeft(nodeToInsert);
            } else {
                parentNode.setRight(nodeToInsert);
            }
        }

        insertFixup(nodeToInsert);
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public void inorderWalk(Consumer<K> consumer) {

    }

    @Override
    public void preorderWalk(Consumer<K> consumer) {

    }

    @Override
    public void postorderWalk(Consumer<K> consumer) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public K getMinimum() {
        return null;
    }

    @Override
    public K getMaximum() {
        return null;
    }

    @Override
    public K getSuccessorOf(K key) {
        return null;
    }

    @Override
    public K getPredecessorOf(K key) {
        return null;
    }

    @Override
    public void clear() {
        root = nil;
    }

    RedBlackSearchTreeNode<K> getRoot() {
        return root;
    }

    void print() {
        BinaryTreePrinter.printNode(root, nil);
    }

    private static class SentinelRedBlackSearchTreeNode<K> implements RedBlackSearchTreeNode<K> {
        @Override
        public RedBlackTreeNodeColor getColor() {
            return RedBlackTreeNodeColor.BLACK;
        }

        @Override
        public void setColor(RedBlackTreeNodeColor color) {
            throw new UnsupportedOperationException("setColor() method cannot be called on sentinel node");
        }

        @Override
        public RedBlackSearchTreeNode<K> getParent() {
            return null;
        }

        @Override
        public void setParent(RedBlackSearchTreeNode<K> parent) {
            throw new UnsupportedOperationException("setParent() method cannot be called on sentinel node");
        }

        @Override
        public RedBlackSearchTreeNode<K> getLeft() {
            return null;
        }

        @Override
        public void setLeft(RedBlackSearchTreeNode<K> left) {
            throw new UnsupportedOperationException("setLeft() method cannot be called on sentinel node");
        }

        @Override
        public RedBlackSearchTreeNode<K> getRight() {
            return null;
        }

        @Override
        public void setRight(RedBlackSearchTreeNode<K> right) {
            throw new UnsupportedOperationException("setRight() method cannot be called on sentinel node");
        }

        @Override
        public K getKey() {
            return null;
        }

        @Override
        public void setKey(K key) {
            throw new UnsupportedOperationException("setKey() method cannot be called on sentinel node");
        }

        @Override
        public String toString() {
            return "nil";
        }
    }

}
