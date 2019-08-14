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
        Objects.requireNonNull(key, "key cannot be null");

        BinaryTreeNode<K> node = search(key);
        if (node == null) {
            throw new BinaryTreeException("There is no such key in the tree: " + key);
        }

        if (node.getLeft() == null) {
            transplant(node, node.getRight());
        } else if (node.getRight() == null) {
            transplant(node, node.getLeft());
        } else {
            BinaryTreeNode<K> successorNode = findMinimumNode(node.getRight());
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
    private void transplant(BinaryTreeNode<K> originalNode, BinaryTreeNode<K> replacementNode) {
        BinaryTreeNode<K> parentNode = originalNode.getParent();
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

    private BinaryTreeNode<K> findMinimumNode(BinaryTreeNode<K> baseNode) {
        BinaryTreeNode<K> node = baseNode;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public K getMinimum() {
        if (root == null) {
            return null;
        }
        BinaryTreeNode<K> minimumNode = findMinimumNode(root);
        return minimumNode != null ? minimumNode.getKey() : null;
    }

    private BinaryTreeNode<K> findMaximumNode(BinaryTreeNode<K> baseNode) {
        BinaryTreeNode<K> node = baseNode;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    @Override
    public K getMaximum() {
        if (root == null) {
            return null;
        }
        BinaryTreeNode<K> maximumNode = findMaximumNode(root);
        return maximumNode != null ? maximumNode.getKey() : null;
    }

    @Override
    public K getSuccessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        BinaryTreeNode<K> node = search(key);
        if (node == null) {
            throw new BinaryTreeException("There is no such key in the tree: " + key);
        }

        if (node.getRight() != null) {
            BinaryTreeNode<K> minimumNode = findMinimumNode(node.getRight());
            return minimumNode.getKey();
        } else {
            BinaryTreeNode<K> parentNode = node.getParent();
            while (parentNode != null && parentNode.getLeft() != node) {
                node = parentNode;
                parentNode = node.getParent();
            }
            return parentNode != null ? parentNode.getKey() : null;
        }
    }

    @Override
    public K getPredecessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        BinaryTreeNode<K> node = search(key);
        if (node == null) {
            throw new BinaryTreeException("There is no such key in the tree: " + key);
        }

        if (node.getLeft() != null) {
            BinaryTreeNode<K> maximumNode = findMaximumNode(node.getLeft());
            return maximumNode.getKey();
        } else {
            BinaryTreeNode<K> parentNode = node.getParent();
            while (parentNode != null && parentNode.getRight() != node) {
                node = parentNode;
                parentNode = node.getParent();
            }
            return parentNode != null ? parentNode.getKey() : null;
        }
    }

    @Override
    public void clear() {
        root = null;
    }

    public void print() {
        BinaryTreePrinter.printNode(root);
    }

}
