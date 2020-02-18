package ru.alexeylisyutenko.cormen.chapter12.simplebinarytree;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter13.RedBlackSearchTreeNode;

import java.util.Objects;

public class SimpleBinarySearchTree<K extends Comparable<K>> extends AbstractBinarySearchTree<K, SimpleBinarySearchTreeNode<K>> implements BinarySearchTree<K> {
    @Override
    public void insert(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        SimpleBinarySearchTreeNode<K> previous = null;
        SimpleBinarySearchTreeNode<K> current = root;
        while (current != null) {
            previous = current;
            if (key.compareTo(current.getKey()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (previous == null) {
            root = new DefaultSimpleBinarySearchTreeNode<>(null, null, key);
        } else if (key.compareTo(previous.getKey()) < 0) {
            previous.setLeft(new DefaultSimpleBinarySearchTreeNode<>(null, null, key));
        } else {
            previous.setRight(new DefaultSimpleBinarySearchTreeNode<>(null, null, key));
        }
    }

    @Override
    public void delete(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        SimpleBinarySearchTreeNode<K> node = search(key);
        if (node == null) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getLeft() == null) {
            transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            transplant(node, node.getLeft());
        } else {
            SimpleBinarySearchTreeNode<K> successorNode = findMinimumNode(node.getRight());
            if (node.getRight() != successorNode) {
                transplant(successorNode, successorNode.getRight());
                successorNode.setRight(node.getRight());
            }
            transplant(node, successorNode);
            successorNode.setLeft(node.getLeft());
        }
    }

    public void transplant(SimpleBinarySearchTreeNode<K> uNode, SimpleBinarySearchTreeNode<K> vNode) {
        SimpleBinarySearchTreeNode<K> uNodeParent = uNode.getParent();
        if (uNodeParent == getNil()) {
            root = vNode;
        } else if (uNodeParent.getLeft() == uNode) {
            uNodeParent.setLeft(vNode);
        } else {
            uNodeParent.setRight(vNode);
        }
        if (vNode != getNil()) {
            vNode.setParent(uNodeParent);
        }
    }


    @Override
    protected SimpleBinarySearchTreeNode<K> getNil() {
        return null;
    }
}
