package ru.alexeylisyutenko.cormen.chapter12.base;

import java.util.Deque;
import java.util.LinkedList;
import java.util.function.Consumer;

public final class BinarySearchTreeWalkAlgorithms {

    public static <K> void inorderTreeWalk(BinarySearchTreeNode<K> node, Consumer<K> consumer) {
        if (node != null) {
            inorderTreeWalk(node.getLeft(), consumer);
            consumer.accept(node.getKey());
            inorderTreeWalk(node.getRight(), consumer);
        }
    }

    public static <K> void preorderTreeWalk(BinarySearchTreeNode<K> node, Consumer<K> consumer) {
        if (node != null) {
            consumer.accept(node.getKey());
            preorderTreeWalk(node.getLeft(), consumer);
            preorderTreeWalk(node.getRight(), consumer);
        }
    }

    public static <K> void postorderTreeWalk(BinarySearchTreeNode<K> node, Consumer<K> consumer) {
        if (node != null) {
            postorderTreeWalk(node.getLeft(), consumer);
            postorderTreeWalk(node.getRight(), consumer);
            consumer.accept(node.getKey());
        }
    }

    public static <K> void stackBasedInorderTreeWalk(BinarySearchTreeNode<K> node, Consumer<K> consumer) {
        Deque<BinarySearchTreeNode<K>> stack = new LinkedList<>();
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.getLeft();
            } else {
                node = stack.pop();
                consumer.accept(node.getKey());
                node = node.getRight();
            }
        }
    }

    public static <K> void iterationBasedInorderTreeWalk(BinarySearchTreeNode<K> node, Consumer<K> consumer) {
        BinarySearchTreeNode<K> previous = null;
        BinarySearchTreeNode<K> current = node;
        while (current != null) {

            BinarySearchTreeNode<K> next;
            if (previous == current.getParent()) {
                // We're coming from parent.
                if (current.getLeft() == null && current.getRight() == null) {
                    consumer.accept(current.getKey());

                    // If there are no children then we go back to the parent.
                    next = current.getParent();
                } else if (current.getLeft() == null) {
                    consumer.accept(current.getKey());

                    // If there is only right child then we go to the right.
                    next = current.getRight();
                } else {
                    // Otherwise we go to the left.
                    next = current.getLeft();
                }

            } else if (previous == current.getLeft()) {
                consumer.accept(current.getKey());

                // We're coming from the left child.
                if (current.getRight() == null) {
                    // If there is no right child then we go to the parent.
                    next = current.getParent();
                } else {
                    // Otherwise we go to the right child.
                    next = current.getRight();
                }
            } else if (previous == current.getRight()) {

                // We're coming from the right child.
                next = current.getParent();

            } else {
                throw new IllegalStateException("Incorrect branch");
            }

            previous = current;
            current = next;
        }
    }

}
