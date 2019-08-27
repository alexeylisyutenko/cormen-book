package ru.alexeylisyutenko.cormen.chapter12.simplebinarytree;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeWalkAlgorithms;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter12.utils.BinaryTreePrinter;

import java.util.Objects;
import java.util.function.Consumer;

public class SimpleBinarySearchTree<K extends Comparable<K>> implements BinarySearchTree<K> {

    private SimpleBinarySearchTreeNode<K> root;

    private SimpleBinarySearchTreeNode<K> search(K key) {
        SimpleBinarySearchTreeNode<K> node = root;
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

    private SimpleBinarySearchTreeNode<K> findMinimumNode(SimpleBinarySearchTreeNode<K> baseNode) {
        SimpleBinarySearchTreeNode<K> node = baseNode;
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
        SimpleBinarySearchTreeNode<K> minimumNode = findMinimumNode(root);
        return minimumNode != null ? minimumNode.getKey() : null;
    }

    private SimpleBinarySearchTreeNode<K> findMaximumNode(SimpleBinarySearchTreeNode<K> baseNode) {
        SimpleBinarySearchTreeNode<K> node = baseNode;
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
        SimpleBinarySearchTreeNode<K> maximumNode = findMaximumNode(root);
        return maximumNode != null ? maximumNode.getKey() : null;
    }

    @Override
    public K getSuccessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        SimpleBinarySearchTreeNode<K> node = search(key);
        if (node == null) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getRight() != null) {
            SimpleBinarySearchTreeNode<K> minimumNode = findMinimumNode(node.getRight());
            return minimumNode.getKey();
        } else {
            SimpleBinarySearchTreeNode<K> parentNode = node.getParent();
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

        SimpleBinarySearchTreeNode<K> node = search(key);
        if (node == null) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getLeft() != null) {
            SimpleBinarySearchTreeNode<K> maximumNode = findMaximumNode(node.getLeft());
            return maximumNode.getKey();
        } else {
            SimpleBinarySearchTreeNode<K> parentNode = node.getParent();
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
