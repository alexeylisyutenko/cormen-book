package ru.alexeylisyutenko.cormen.chapter14.base;

import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor;

import java.util.Objects;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.RED;

public abstract class AbstractRedBlackBasedBinarySearchTree<K extends Comparable<K>, N extends RedBlackBasedSearchTreeNode<K, N>> extends AbstractBinarySearchTree<K, N> {

    @Override
    public void insert(K key) {
        N parentNode = getNil();
        N currentNode = root;

        while (currentNode != getNil()) {
            parentNode = currentNode;
            int comparisonResult = key.compareTo(currentNode.getKey());
            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        N nodeToInsert = createNodeToInsert(key);
        nodeToInsert.setLeft(getNil());
        nodeToInsert.setRight(getNil());
        nodeToInsert.setKey(key);
        nodeToInsert.setColor(RED);

        if (parentNode == getNil()) {
            nodeToInsert.setParent(getNil());
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

        beforeInsertFixup(nodeToInsert);

        insertFixup(nodeToInsert);
    }

    protected abstract N createNodeToInsert(K key);

    protected void beforeInsertFixup(N nodeToInsert) {
    }

    protected void insertFixup(N zNode) {
        while (zNode.getParent().getColor() != BLACK) {
            N parentNode = zNode.getParent();
            N grandFatherNode = zNode.getParent().getParent();

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
    public void delete(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        N zNode = search(key);
        if (zNode == getNil()) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        N yNode = zNode;
        RedBlackTreeNodeColor yNodeOriginalColor = yNode.getColor();

        N xNode;

        if (zNode.getLeft() == getNil()) {
            xNode = zNode.getRight();
            transplant(zNode, zNode.getRight());
        } else if (zNode.getRight() == getNil()) {
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

        beforeDeleteFixup(xNode.getParent());

        if (yNodeOriginalColor == BLACK) {
            deleteFixup(xNode);
        }
    }

    protected void beforeDeleteFixup(N xNode) {
    }

    protected void deleteFixup(N xNode) {
        while (xNode != root && xNode.getColor() == BLACK) {
            if (xNode.getParent().getLeft() == xNode) {
                N wNode = xNode.getParent().getRight();

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
                N wNode = xNode.getParent().getLeft();

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

}
