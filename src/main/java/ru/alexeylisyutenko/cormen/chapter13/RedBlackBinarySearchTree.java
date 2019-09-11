package ru.alexeylisyutenko.cormen.chapter13;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;

import java.util.Objects;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.RED;

public class RedBlackBinarySearchTree<K extends Comparable<K>> extends AbstractBinarySearchTree<K, RedBlackSearchTreeNode<K>> {

    private final static RedBlackSearchTreeNode NIL;

    static {
        NIL = new DefaultRedBlackSearchTreeNode();
        NIL.setColor(BLACK);
    }

    private int blackHeight;

    public RedBlackBinarySearchTree() {
        this.root = NIL;
        this.blackHeight = 0;
    }

    private RedBlackSearchTreeNode<K> createSentinelNode() {
        RedBlackSearchTreeNode<K> sentinel = new DefaultRedBlackSearchTreeNode<>();
        sentinel.setColor(BLACK);
        return sentinel;
    }

    @Override
    protected RedBlackSearchTreeNode<K> getNil() {
        return NIL;
    }

    void insertFixup(RedBlackSearchTreeNode<K> zNode) {
        while (zNode.getParent().getColor() != BLACK) {
            RedBlackSearchTreeNode<K> parentNode = zNode.getParent();
            RedBlackSearchTreeNode<K> grandFatherNode = zNode.getParent().getParent();

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
        if (root.getColor() == RED) {
            blackHeight++;
        }
        root.setColor(BLACK);
    }

    @Override
    public void insert(K key) {
        RedBlackSearchTreeNode<K> parentNode = NIL;
        RedBlackSearchTreeNode<K> currentNode = root;

        while (currentNode != NIL) {
            parentNode = currentNode;
            int comparisonResult = key.compareTo(currentNode.getKey());
            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        DefaultRedBlackSearchTreeNode<K> nodeToInsert = new DefaultRedBlackSearchTreeNode<>();
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

        insertFixup(nodeToInsert);
    }

    void transplant(RedBlackSearchTreeNode<K> uNode, RedBlackSearchTreeNode<K> vNode) {
        RedBlackSearchTreeNode<K> uNodeParent = uNode.getParent();
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

        RedBlackSearchTreeNode<K> zNode = search(key);
        if (zNode == null) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        RedBlackSearchTreeNode<K> yNode = zNode;
        RedBlackTreeNodeColor yNodeOriginalColor = yNode.getColor();

        RedBlackSearchTreeNode<K> xNode;

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
        if (yNodeOriginalColor == BLACK) {
            deleteFixup(xNode);
        }
    }

    private void deleteFixup(RedBlackSearchTreeNode<K> xNode) {
        while (xNode != root && xNode.getColor() == BLACK) {
            if (xNode.getParent().getLeft() == xNode) {
                RedBlackSearchTreeNode<K> wNode = xNode.getParent().getRight();

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
                RedBlackSearchTreeNode<K> wNode = xNode.getParent().getLeft();

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
        if (xNode == root && xNode.getColor() == BLACK) {
            blackHeight--;
        }
        xNode.setColor(BLACK);
    }

    @Override
    public void clear() {
        super.clear();
        blackHeight = 0;
    }

    int getBlackHeight() {
        return blackHeight;
    }

    /**
     * Cormen. Part of a problem 13-2.
     * Find a black node with maximum key and particular black height.
     */
    RedBlackSearchTreeNode<K> findMaxBlackNode(int blackHeight) {
        if (blackHeight < 0) {
            throw new IllegalArgumentException("blackHeight argument cannot be negative");
        }
        RedBlackSearchTreeNode<K> currentNode = root;
        int currentBlackHeight = this.blackHeight;
        while (currentNode != NIL) {
            if (currentNode.getColor() == BLACK && currentBlackHeight == blackHeight) {
                return currentNode;
            }
            currentNode = currentNode.getRight();
            if (currentNode.getColor() == BLACK) {
                currentBlackHeight--;
            }
        }
        throw new BinarySearchTreeException(String.format("There is no node in the tree with black height: %d", blackHeight));
    }

    /**
     * Cormen. Part of a problem 13-2.
     * Find a black node with minimum key and particular black height.
     */
    RedBlackSearchTreeNode<K> findMinBlackNode(int blackHeight) {
        if (blackHeight < 0) {
            throw new IllegalArgumentException("blackHeight argument cannot be negative");
        }
        RedBlackSearchTreeNode<K> currentNode = root;
        int currentBlackHeight = this.blackHeight;
        while (currentNode != NIL) {
            if (currentNode.getColor() == BLACK && currentBlackHeight == blackHeight) {
                return currentNode;
            }
            currentNode = currentNode.getLeft();
            if (currentNode.getColor() == BLACK) {
                currentBlackHeight--;
            }
        }
        throw new BinarySearchTreeException(String.format("There is no node in the tree with black height: %d", blackHeight));
    }

}
