package ru.alexeylisyutenko.cormen.chapter12.base;

import ru.alexeylisyutenko.cormen.chapter12.utils.BinaryTreePrinter;

import java.util.Objects;
import java.util.function.Consumer;

public abstract class AbstractBinarySearchTree<K extends Comparable<K>, N extends BinarySearchTreeNode<K, N>> implements BinarySearchTree<K> {
    protected N root;

    public AbstractBinarySearchTree() {
        this.root = getNil();
    }

    protected abstract N getNil();

    protected void rotateLeft(N xNode) {
        if (xNode.getRight() == getNil()) {
            throw new IllegalStateException("Right child of a xNode must exist for left rotation");
        }
        N yNode = xNode.getRight();

        // Put yNode's left to xNode's right.
        xNode.setRight(yNode.getLeft());
        if (yNode.getLeft() != getNil()) {
            yNode.getLeft().setParent(xNode);
        }

        // Update yNode parent.
        yNode.setParent(xNode.getParent());

        // Update xNode's parent to point to yNode instead of xNode.
        if (xNode.getParent() == getNil()) {
            root = yNode;
        } else {
            N xParent = xNode.getParent();
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

    protected void rotateRight(N yNode) {
        if (yNode.getLeft() == getNil()) {
            throw new IllegalStateException("Left child of a yNode must exist for right rotation");
        }
        N xNode = yNode.getLeft();

        // Put xNode's right to yNode's left.
        yNode.setLeft(xNode.getRight());
        if (xNode.getRight() != getNil()) {
            xNode.getRight().setParent(yNode);
        }

        // Update xNode parent.
        xNode.setParent(yNode.getParent());

        // Update yNode's parent to point to xNode instead of yNode.
        N yParent = yNode.getParent();
        if (yParent == getNil()) {
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

    protected void inorderWalkRecursive(N node, Consumer<K> consumer) {
        if (node != getNil()) {
            inorderWalkRecursive(node.getLeft(), consumer);
            consumer.accept(node.getKey());
            inorderWalkRecursive(node.getRight(), consumer);
        }
    }

    @Override
    public void inorderWalk(Consumer<K> consumer) {
        inorderWalkRecursive(root, consumer);
    }

    protected void preorderWalkRecursive(N node, Consumer<K> consumer) {
        if (node != getNil()) {
            consumer.accept(node.getKey());
            preorderWalkRecursive(node.getLeft(), consumer);
            preorderWalkRecursive(node.getRight(), consumer);
        }
    }

    @Override
    public void preorderWalk(Consumer<K> consumer) {
        preorderWalkRecursive(root, consumer);
    }

    protected void postorderWalkRecursive(N node, Consumer<K> consumer) {
        if (node != getNil()) {
            postorderWalkRecursive(node.getLeft(), consumer);
            postorderWalkRecursive(node.getRight(), consumer);
            consumer.accept(node.getKey());
        }
    }

    @Override
    public void postorderWalk(Consumer<K> consumer) {
        postorderWalkRecursive(root, consumer);
    }

    @Override
    public int size() {
        var ref = new Object() {
            int counter = 0;
        };
        inorderWalk((key) -> ref.counter++);
        return ref.counter;
    }

    protected N search(K key) {
        N currentNode = root;
        while (currentNode != getNil() && !currentNode.getKey().equals(key)) {
            int comparisonResult = key.compareTo(currentNode.getKey());
            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        return currentNode;
    }


    @Override
    public boolean contains(K key) {
        return search(key) != getNil();
    }

    protected N findMinimumNode(N baseNode) {
        N currentNode = baseNode;
        while (currentNode.getLeft() != getNil()) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    @Override
    public K getMinimum() {
        if (root == getNil()) {
            return null;
        }
        N minimumNode = findMinimumNode(root);
        return minimumNode != getNil() ? minimumNode.getKey() : null;
    }

    protected N findMaximumNode(N baseNode) {
        N currentNode = baseNode;
        while (currentNode.getRight() != getNil()) {
            currentNode = currentNode.getRight();
        }
        return currentNode;
    }

    @Override
    public K getMaximum() {
        if (root == getNil()) {
            return null;
        }
        N maximumNode = findMaximumNode(root);
        return maximumNode != getNil() ? maximumNode.getKey() : null;
    }

    @Override
    public K getSuccessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        N node = search(key);
        if (node == getNil()) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getRight() != getNil()) {
            return findMinimumNode(node.getRight()).getKey();
        } else {
            N parentNode = node.getParent();
            while (parentNode != getNil() && parentNode.getLeft() != node) {
                node = parentNode;
                parentNode = parentNode.getParent();
            }
            return parentNode != getNil() ? parentNode.getKey() : null;
        }
    }

    @Override
    public K getPredecessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        N node = search(key);
        if (node == getNil()) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getLeft() != getNil()) {
            return findMaximumNode(node.getLeft()).getKey();
        } else {
            N parentNode = node.getParent();
            while (parentNode != getNil() && parentNode.getRight() != node) {
                node = parentNode;
                parentNode = parentNode.getParent();
            }
            return parentNode != getNil() ? parentNode.getKey() : null;
        }
    }

    @Override
    public void clear() {
        root = getNil();
    }

    public void print() {
        BinaryTreePrinter.printNode(root, getNil());
    }

    public N getRoot() {
        return root;
    }

}
