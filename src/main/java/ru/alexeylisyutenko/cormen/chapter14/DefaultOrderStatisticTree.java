package ru.alexeylisyutenko.cormen.chapter14;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedBinarySearchTree;

import java.util.Objects;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;

public class DefaultOrderStatisticTree<K extends Comparable<K>> extends AbstractRedBlackBasedBinarySearchTree<K, OrderStatisticTreeNode<K>> implements OrderStatisticTree<K> {
    /**
     * Sentinel node which is used instead of null for convenience.
     */
    private final static OrderStatisticTreeNode NIL;

    static {
        NIL = new DefaultOrderStatisticTreeNode();
        NIL.setColor(BLACK);
        NIL.setSize(0);
    }

    @Override
    protected OrderStatisticTreeNode<K> getNil() {
        return NIL;
    }

    @Override
    protected void rotateLeft(OrderStatisticTreeNode<K> xNode) {
        OrderStatisticTreeNode<K> yNode = xNode.getRight();
        super.rotateLeft(xNode);

        yNode.setSize(xNode.getSize());
        xNode.setSize(xNode.getLeft().getSize() + xNode.getRight().getSize() + 1);
    }

    @Override
    protected void rotateRight(OrderStatisticTreeNode<K> yNode) {
        OrderStatisticTreeNode<K> xNode = yNode.getLeft();
        super.rotateRight(yNode);

        xNode.setSize(yNode.getSize());
        yNode.setSize(yNode.getLeft().getSize() + yNode.getRight().getSize() + 1);
    }

    @Override
    protected OrderStatisticTreeNode<K> createNodeToInsert(K key) {
        return new DefaultOrderStatisticTreeNode<>();
    }

    @Override
    protected void beforeInsertFixup(OrderStatisticTreeNode<K> nodeToInsert) {
        updateNodeSizes(nodeToInsert);
    }

    @Override
    protected void beforeDeleteFixup(OrderStatisticTreeNode<K> xNode) {
        updateNodeSizes(xNode);
    }

    protected void updateNodeSizes(OrderStatisticTreeNode<K> currentNode) {
        while (currentNode != NIL) {
            currentNode.setSize(currentNode.getLeft().getSize() + currentNode.getRight().getSize() + 1);
            currentNode = currentNode.getParent();
        }
    }

    protected OrderStatisticTreeNode<K> selectOrderStatisticNode(OrderStatisticTreeNode<K> currentNode, int rank) {
        while (true) {
            if (currentNode == NIL) {
                return NIL;
            }

            int currentNodeRank = currentNode.getLeft().getSize() + 1;
            if (currentNodeRank == rank) {
                return currentNode;
            } else if (rank < currentNodeRank) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
                rank = rank - currentNodeRank;
            }
        }
    }

    @Override
    public K selectOrderStatistic(int rank) {
        if (rank < 1) {
            throw new IllegalArgumentException("rank cannot be less than 1");
        }

        if (rank > size()) {
            throw new BinarySearchTreeException(String.format("There is no node with rank '%d' in the tree", rank));
        }
        return selectOrderStatisticNode(root, rank).getKey();
    }

    @Override
    public int findRank(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        OrderStatisticTreeNode<K> currentNode = search(key);
        if (currentNode == NIL) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        int rank = currentNode.getLeft().getSize() + 1;
        while (currentNode != root) {
            if (currentNode.getParent().getRight() == currentNode) {
                rank += currentNode.getParent().getLeft().getSize() + 1;
            }
            currentNode = currentNode.getParent();
        }
        return rank;
    }

    protected OrderStatisticTreeNode<K> findIthSuccessorNode(OrderStatisticTreeNode<K> node, int successorIndex) {
        if (successorIndex == 0) {
            return node;
        }

        if (successorIndex <= node.getRight().getSize()) {
            // If ith successor is in the right subtree, we just search for ith order statistic there.
            return selectOrderStatisticNode(node.getRight(), successorIndex);
        } else {
            // Save new successor index.
            int newSuccessorIndex = successorIndex - node.getRight().getSize() - 1;

            // We go up the tree until we find a node whose left child is also current node's ancestor;
            OrderStatisticTreeNode<K> parent = node.getParent();
            while (parent != NIL && node == parent.getRight()) {
                node = parent;
                parent = node.getParent();
            }
            if (parent == NIL) {
                return NIL;
            } else {
                return findIthSuccessorNode(parent, newSuccessorIndex);
            }
        }
    }

    @Override
    public K getIthSuccessorOf(K key, int successorIndex) {
        Objects.requireNonNull(key, "key cannot be null");

        OrderStatisticTreeNode<K> node = search(key);
        if (node == NIL) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        OrderStatisticTreeNode<K> successorNode = findIthSuccessorNode(node, successorIndex);
        if (successorNode == NIL) {
            throw new BinarySearchTreeException(String.format("There is no such successor node. Key: '%s', successorIndex: '%d'.", key.toString(), successorIndex));
        }
        return successorNode.getKey();
    }

    @Override
    public int size() {
        return root.getSize();
    }

    @Override
    public int countGreaterKeys(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        OrderStatisticTreeNode<K> node = search(key);
        if (node == NIL) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        int greaterKeysCount = node.getRight().getSize();
        while (node.getParent() != NIL) {
            if (node.getParent().getLeft() == node) {
                greaterKeysCount += node.getParent().getRight().getSize() + 1;
            }
            node = node.getParent();
        }
        return greaterKeysCount;
    }
}
