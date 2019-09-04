package ru.alexeylisyutenko.cormen.chapter13;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter12.utils.BinaryTreePrinter;

import java.util.Objects;
import java.util.function.Consumer;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.RED;

public class RedBlackBinarySearchTree<K extends Comparable<K>> implements BinarySearchTree<K> {

    private final static RedBlackSearchTreeNode NIL;

    static {
        NIL = new DefaultRedBlackSearchTreeNode();
        NIL.setColor(BLACK);
    }

    private RedBlackSearchTreeNode<K> root;
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

    private void rotateLeft(RedBlackSearchTreeNode<K> xNode) {
        if (xNode.getRight() == NIL) {
            throw new IllegalStateException("Right child of a xNode must exist for left rotation");
        }
        RedBlackSearchTreeNode<K> yNode = xNode.getRight();

        // Put yNode's left to xNode's right.
        xNode.setRight(yNode.getLeft());
        if (yNode.getLeft() != NIL) {
            yNode.getLeft().setParent(xNode);
        }

        // Update yNode parent.
        yNode.setParent(xNode.getParent());

        // Update xNode's parent to point to yNode instead of xNode.
        if (xNode.getParent() == NIL) {
            root = yNode;
        } else {
            RedBlackSearchTreeNode<K> xParent = xNode.getParent();
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

    private void rotateRight(RedBlackSearchTreeNode<K> yNode) {
        if (yNode.getLeft() == NIL) {
            throw new IllegalStateException("Left child of a yNode must exist for right rotation");
        }
        RedBlackSearchTreeNode<K> xNode = yNode.getLeft();

        // Put xNode's right to yNode's left.
        yNode.setLeft(xNode.getRight());
        if (xNode.getRight() != NIL) {
            xNode.getRight().setParent(yNode);
        }

        // Update xNode parent.
        xNode.setParent(yNode.getParent());

        // Update yNode's parent to point to xNode instead of yNode.
        RedBlackSearchTreeNode<K> yParent = yNode.getParent();
        if (yParent == NIL) {
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

    private void inorderWalkRecursive(RedBlackSearchTreeNode<K> node, Consumer<K> consumer) {
        if (node != NIL) {
            inorderWalkRecursive(node.getLeft(), consumer);
            consumer.accept(node.getKey());
            inorderWalkRecursive(node.getRight(), consumer);
        }
    }

    @Override
    public void inorderWalk(Consumer<K> consumer) {
        inorderWalkRecursive(root, consumer);
    }

    private void preorderWalkRecursive(RedBlackSearchTreeNode<K> node, Consumer<K> consumer) {
        if (node != NIL) {
            consumer.accept(node.getKey());
            preorderWalkRecursive(node.getLeft(), consumer);
            preorderWalkRecursive(node.getRight(), consumer);
        }
    }

    @Override
    public void preorderWalk(Consumer<K> consumer) {
        preorderWalkRecursive(root, consumer);
    }

    private void postorderWalkRecursive(RedBlackSearchTreeNode<K> node, Consumer<K> consumer) {
        if (node != NIL) {
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

    private RedBlackSearchTreeNode<K> search(K key) {
        RedBlackSearchTreeNode<K> currentNode = root;
        while (currentNode != NIL && !currentNode.getKey().equals(key)) {
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
        return search(key) != NIL;
    }

    private RedBlackSearchTreeNode<K> findMinimumNode(RedBlackSearchTreeNode<K> baseNode) {
        RedBlackSearchTreeNode<K> currentNode = baseNode;
        while (currentNode.getLeft() != NIL) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    @Override
    public K getMinimum() {
        if (root == NIL) {
            return null;
        }
        RedBlackSearchTreeNode<K> minimumNode = findMinimumNode(root);
        return minimumNode != NIL ? minimumNode.getKey() : null;
    }

    private RedBlackSearchTreeNode<K> findMaximumNode(RedBlackSearchTreeNode<K> baseNode) {
        RedBlackSearchTreeNode<K> currentNode = baseNode;
        while (currentNode.getRight() != NIL) {
            currentNode = currentNode.getRight();
        }
        return currentNode;
    }

    @Override
    public K getMaximum() {
        if (root == NIL) {
            return null;
        }
        RedBlackSearchTreeNode<K> maximumNode = findMaximumNode(root);
        return maximumNode != NIL ? maximumNode.getKey() : null;
    }

    @Override
    public K getSuccessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        RedBlackSearchTreeNode<K> node = search(key);
        if (node == NIL) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getRight() != NIL) {
            return findMinimumNode(node.getRight()).getKey();
        } else {
            RedBlackSearchTreeNode<K> parentNode = node.getParent();
            while (parentNode != NIL && parentNode.getLeft() != node) {
                node = parentNode;
                parentNode = parentNode.getParent();
            }
            return parentNode != NIL ? parentNode.getKey() : null;
        }
    }

    @Override
    public K getPredecessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        RedBlackSearchTreeNode<K> node = search(key);
        if (node == NIL) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getLeft() != NIL) {
            return findMaximumNode(node.getLeft()).getKey();
        } else {
            RedBlackSearchTreeNode<K> parentNode = node.getParent();
            while (parentNode != NIL && parentNode.getRight() != node) {
                node = parentNode;
                parentNode = parentNode.getParent();
            }
            return parentNode != NIL ? parentNode.getKey() : null;
        }
    }

    @Override
    public void clear() {
        root = NIL;
        blackHeight = 0;
    }

    RedBlackSearchTreeNode<K> getRoot() {
        return root;
    }

    int getBlackHeight() {
        return blackHeight;
    }

    void print() {
        BinaryTreePrinter.printNode(root, NIL);
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
