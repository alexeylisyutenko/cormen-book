package ru.alexeylisyutenko.cormen.chapter13;

import ru.alexeylisyutenko.cormen.chapter12.BinaryTree;

import java.util.function.Consumer;

public class RedBlackBinaryTree<K extends Comparable<K>> implements BinaryTree<K> {

    private final RedBlackTreeNode<K> nil;

    private RedBlackTreeNode<K> root;

    public RedBlackBinaryTree() {
        this.nil = new SentinelRedBlackTreeNode<>();
        this.root = nil;
    }

    private void rotateLeft(RedBlackTreeNode<K> xNode) {
        if (xNode.getRight() == nil) {
            throw new IllegalStateException("Right child of a xNode must exist for left rotation");
        }
        RedBlackTreeNode<K> yNode = xNode.getRight();

        // Put yNode's left to xNode's right.
        xNode.setRight(yNode.getLeft());
        if (yNode.getLeft() != nil) {
            yNode.getLeft().setParent(xNode);
        }

        // Update xNode's parent to point to yNode instead of xNode.
        if (xNode.getParent() == nil) {
            root = yNode;
        } else {
            RedBlackTreeNode<K> xParent = xNode.getParent();
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

    private void rotateRight(RedBlackTreeNode<K> yNode) {
        if (yNode.getLeft() == nil) {
            throw new IllegalStateException("Left child of a yNode must exist for right rotation");
        }
        RedBlackTreeNode<K> xNode = yNode.getLeft();

        // Put xNode's right to yNode's left.
        yNode.setLeft(xNode.getRight());
        if (xNode.getRight() != nil) {
            xNode.getRight().setParent(yNode);
        }

        // Update yNode's parent to point to xNode instead of yNode.
        RedBlackTreeNode<K> yParent = yNode.getParent();
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

    private void insertFixup(RedBlackTreeNode<K> zNode) {

    }

    @Override
    public void insert(K key) {

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

    private static class SentinelRedBlackTreeNode<K> implements RedBlackTreeNode<K> {
        @Override
        public RedBlackTreeNodeColor getColor() {
            return RedBlackTreeNodeColor.BLACK;
        }

        @Override
        public void setColor(RedBlackTreeNodeColor color) {
            throw new UnsupportedOperationException("setColor() method cannot be called on sentinel node");
        }

        @Override
        public RedBlackTreeNode<K> getParent() {
            return null;
        }

        @Override
        public void setParent(RedBlackTreeNode<K> parent) {
            throw new UnsupportedOperationException("setParent() method cannot be called on sentinel node");
        }

        @Override
        public RedBlackTreeNode<K> getLeft() {
            return null;
        }

        @Override
        public void setLeft(RedBlackTreeNode<K> left) {
            throw new UnsupportedOperationException("setLeft() method cannot be called on sentinel node");
        }

        @Override
        public RedBlackTreeNode<K> getRight() {
            return null;
        }

        @Override
        public void setRight(RedBlackTreeNode<K> right) {
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
    }

}
