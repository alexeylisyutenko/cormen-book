package ru.alexeylisyutenko.cormen.chapter12;

import java.util.Objects;
import java.util.function.Consumer;

public class DefaultBinarySearchTree<K extends Comparable<K>> implements BinaryTree<K> {

    private BinaryTreeNode<K> root;

    private BinaryTreeNode<K> search(K key) {
        BinaryTreeNode<K> node = root;
        while (node != null) {
            int comparisonResult = key.compareTo(node.getKey());
            if (comparisonResult == 0) {
                return node;
            }
            if (comparisonResult < 0) {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }
        }
        return null;
    }

    @Override
    public void insert(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        BinaryTreeNode<K> previous = null;
        BinaryTreeNode<K> current = root;
        while (current != null) {
            previous = current;
            if (key.compareTo(current.getKey()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (previous == null) {
            root = new DefaultBinaryTreeNode<>(null, null, key);
        } else if (key.compareTo(previous.getKey()) < 0) {
            previous.setLeft(new DefaultBinaryTreeNode<>(null, null, key));
        } else {
            previous.setRight(new DefaultBinaryTreeNode<>(null, null, key));
        }
    }

    @Override
    public void delete(K key) {

    }

    @Override
    public void inorderWalk(Consumer<K> consumer) {
        Objects.requireNonNull(consumer, "consumer cannot be null");
        BinarySearchTreeWalkAlgorithms.inorderTreeWalk(root, consumer);
    }

    @Override
    public void preorderWalk(Consumer<K> consumer) {
        Objects.requireNonNull(consumer, "consumer cannot be null");
        BinarySearchTreeWalkAlgorithms.preorderTreeWalk(root, consumer);
    }

    @Override
    public void postorderWalk(Consumer<K> consumer) {
        Objects.requireNonNull(consumer, "consumer cannot be null");
        BinarySearchTreeWalkAlgorithms.postorderTreeWalk(root, consumer);
    }

    @Override
    public int size() {
        var ref = new Object() {
            int counter = 0;
        };
        inorderWalk((key) -> ref.counter++);
        return ref.counter;
    }

    @Override
    public boolean contains(K key) {
        Objects.requireNonNull(key, "key cannot be null");
        return search(key) != null;
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

    public void print() {
        BinaryTreePrinter.printNode(root);
    }


}
