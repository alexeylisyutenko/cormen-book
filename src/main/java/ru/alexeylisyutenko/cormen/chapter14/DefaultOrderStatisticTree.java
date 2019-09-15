package ru.alexeylisyutenko.cormen.chapter14;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor;

import java.util.Objects;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.RED;

public class DefaultOrderStatisticTree<K extends Comparable<K>> extends AbstractBinarySearchTree<K, OrderStatisticTreeNode<K>> implements OrderStatisticTree<K> {
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

    protected void insertFixup(OrderStatisticTreeNode<K> zNode) {
        while (zNode.getParent().getColor() != BLACK) {
            OrderStatisticTreeNode<K> parentNode = zNode.getParent();
            OrderStatisticTreeNode<K> grandFatherNode = zNode.getParent().getParent();

            // Case 1.
            if (grandFatherNode.getLeft().getColor() == RED && grandFatherNode.getRight().getColor() == RED) {
                grandFatherNode.getLeft().setColor(BLACK);
                grandFatherNode.getRight().setColor(BLACK);
                grandFatherNode.setColor(RED);
                zNode = grandFatherNode;
            } else {
                if (grandFatherNode.getLeft() == parentNode) {
                    // zNode is in the left grandfather's subtree.
                    // Case 2.
                    if (parentNode.getRight() == zNode) {
                        rotateLeft(parentNode);
                        zNode = parentNode;
                        parentNode = zNode.getParent();
                        grandFatherNode = zNode.getParent().getParent();
                    }

                    // Case 3.
                    parentNode.setColor(BLACK);
                    grandFatherNode.setColor(RED);
                    rotateRight(grandFatherNode);
                } else {
                    // zNode is in the right grandfather's subtree.
                    // Case 2.
                    if (parentNode.getLeft() == zNode) {
                        rotateRight(parentNode);
                        zNode = parentNode;
                        parentNode = zNode.getParent();
                        grandFatherNode = zNode.getParent().getParent();
                    }

                    // Case 3.
                    parentNode.setColor(BLACK);
                    grandFatherNode.setColor(RED);
                    rotateLeft(grandFatherNode);
                }
            }
        }
        root.setColor(BLACK);
    }

    @Override
    public void insert(K key) {
        OrderStatisticTreeNode<K> parentNode = NIL;
        OrderStatisticTreeNode<K> currentNode = root;

        while (currentNode != NIL) {
            parentNode = currentNode;
            int comparisonResult = key.compareTo(currentNode.getKey());
            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        OrderStatisticTreeNode<K> nodeToInsert = new DefaultOrderStatisticTreeNode<>();
        nodeToInsert.setLeft(NIL);
        nodeToInsert.setRight(NIL);
        nodeToInsert.setKey(key);
        nodeToInsert.setColor(RED);

        if (parentNode == NIL) {
            nodeToInsert.setParent(NIL);
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

        updateNodeSizes(nodeToInsert);
        insertFixup(nodeToInsert);
    }

    void transplant(OrderStatisticTreeNode<K> uNode, OrderStatisticTreeNode<K> vNode) {
        OrderStatisticTreeNode<K> uNodeParent = uNode.getParent();
        if (uNodeParent == NIL) {
            root = vNode;
        } else if (uNodeParent.getLeft() == uNode) {
            uNodeParent.setLeft(vNode);
        } else {
            uNodeParent.setRight(vNode);
        }
        vNode.setParent(uNodeParent);
    }

    @Override
    public void delete(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        OrderStatisticTreeNode<K> zNode = search(key);
        if (zNode == NIL) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        OrderStatisticTreeNode<K> yNode = zNode;
        RedBlackTreeNodeColor yNodeOriginalColor = yNode.getColor();

        OrderStatisticTreeNode<K> xNode;

        if (zNode.getLeft() == NIL) {
            xNode = zNode.getRight();
            transplant(zNode, zNode.getRight());
        } else if (zNode.getRight() == NIL) {
            xNode = zNode.getLeft();
            transplant(zNode, zNode.getLeft());
        } else {
            yNode = findMinimumNode(zNode.getRight());
            yNodeOriginalColor = yNode.getColor();
            xNode = yNode.getRight();

            if (yNode.getParent() == zNode) {
                xNode.setParent(yNode);
            } else {
                transplant(yNode, yNode.getRight());
                yNode.setRight(zNode.getRight());
                zNode.getRight().setParent(yNode);
            }
            transplant(zNode, yNode);
            yNode.setLeft(zNode.getLeft());
            yNode.getLeft().setParent(yNode);
            yNode.setColor(zNode.getColor());
        }
        updateNodeSizes(xNode.getParent());
        if (yNodeOriginalColor == BLACK) {
            deleteFixup(xNode);
        }
    }

    protected void updateNodeSizes(OrderStatisticTreeNode<K> currentNode) {
        while (currentNode != NIL) {
            currentNode.setSize(currentNode.getLeft().getSize() + currentNode.getRight().getSize() + 1);
            currentNode = currentNode.getParent();
        }
    }

    private void deleteFixup(OrderStatisticTreeNode<K> xNode) {
        while (xNode != root && xNode.getColor() == BLACK) {
            if (xNode.getParent().getLeft() == xNode) {
                OrderStatisticTreeNode<K> wNode = xNode.getParent().getRight();

                if (wNode.getColor() == RED) {
                    // Case 1 (x's sibling w is red).
                    xNode.getParent().setColor(RED);
                    wNode.setColor(BLACK);
                    rotateLeft(xNode.getParent());
                    wNode = xNode.getParent().getRight();
                }
                // It is guaranteed here that x's sibling is black.
                if (wNode.getLeft().getColor() == BLACK && wNode.getRight().getColor() == BLACK) {
                    // Case 2 (x’s sibling w is black, and both of w’s children are black).
                    wNode.setColor(RED);
                    xNode = xNode.getParent();
                } else {
                    if (wNode.getRight().getColor() == BLACK) {
                        // Case 3 (x’s sibling w is black, w’s left child is red, and w’s right child is black).
                        wNode.getLeft().setColor(BLACK);
                        wNode.setColor(RED);
                        rotateRight(wNode);
                        wNode = xNode.getParent().getRight();
                    }
                    // Case 4 (x’s sibling w is black, and w’s right child is red).
                    wNode.setColor(xNode.getParent().getColor());
                    xNode.getParent().setColor(BLACK);
                    wNode.getRight().setColor(BLACK);
                    rotateLeft(xNode.getParent());
                    break;
                }
            } else {
                OrderStatisticTreeNode<K> wNode = xNode.getParent().getLeft();

                if (wNode.getColor() == RED) {
                    // Case 1 (x's sibling w is red).
                    xNode.getParent().setColor(RED);
                    wNode.setColor(BLACK);
                    rotateRight(xNode.getParent());
                    wNode = xNode.getParent().getLeft();
                }
                // It is guaranteed here that x's sibling is black.
                if (wNode.getLeft().getColor() == BLACK && wNode.getRight().getColor() == BLACK) {
                    // Case 2 (x’s sibling w is black, and both of w’s children are black).
                    wNode.setColor(RED);
                    xNode = xNode.getParent();
                } else {
                    if (wNode.getLeft().getColor() == BLACK) {
                        // Case 3 (x’s sibling w is black, w’s right child is red, and w’s left child is black).
                        wNode.getRight().setColor(BLACK);
                        wNode.setColor(RED);
                        rotateLeft(wNode);
                        wNode = xNode.getParent().getLeft();
                    }
                    // Case 4 (x’s sibling w is black, and w’s left child is red).
                    wNode.setColor(xNode.getParent().getColor());
                    xNode.getParent().setColor(BLACK);
                    wNode.getLeft().setColor(BLACK);
                    rotateRight(xNode.getParent());
                    break;
                }

            }
        }
        xNode.setColor(BLACK);
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

        // If ith successor is in the right subtree, we just search for ith order statistic there.
        if (node.getRight().getSize() <= successorIndex) {
            return selectOrderStatisticNode(node.getRight(), successorIndex);
        } else {
            successorIndex = successorIndex - node.getRight().getSize() - 1;
            OrderStatisticTreeNode<K> parent = node.getParent();
            while (parent != NIL && node == parent.getRight()) {
                node = parent;
                parent = node.getParent();
            }

        }

        return null;
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
}
