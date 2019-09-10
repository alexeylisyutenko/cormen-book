package ru.alexeylisyutenko.cormen.chapter12.simplebinarytree;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;

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

    /**
     * Replaces one subtree as a child of its parent with another subtree.
     */
    private void transplant(SimpleBinarySearchTreeNode<K> originalNode, SimpleBinarySearchTreeNode<K> replacementNode) {
        SimpleBinarySearchTreeNode<K> parentNode = originalNode.getParent();
        if (parentNode == null) {
            root = replacementNode;
        } else if (parentNode.getLeft() == originalNode) {
            parentNode.setLeft(replacementNode);
        } else {
            parentNode.setRight(replacementNode);
        }
        if (replacementNode != null) {
            replacementNode.setParent(parentNode);
        }
    }

    @Override
    protected SimpleBinarySearchTreeNode<K> getNil() {
        return null;
    }
}
