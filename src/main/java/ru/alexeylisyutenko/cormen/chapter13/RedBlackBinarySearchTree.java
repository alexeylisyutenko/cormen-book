package ru.alexeylisyutenko.cormen.chapter13;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter12.utils.BinaryTreePrinter;

import java.util.Objects;
import java.util.function.Consumer;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.RED;

public class RedBlackBinarySearchTree<K extends Comparable<K>> implements BinarySearchTree<K> {

    private final RedBlackSearchTreeNode<K> nil;

    private RedBlackSearchTreeNode<K> root;

    public RedBlackBinarySearchTree() {
        this.nil = createSentinelNode();
        this.root = nil;
    }

    private RedBlackSearchTreeNode<K> createSentinelNode() {
        RedBlackSearchTreeNode<K> sentinel = new DefaultRedBlackSearchTreeNode<>();
        sentinel.setColor(BLACK);
        return sentinel;
    }

    private void rotateLeft(RedBlackSearchTreeNode<K> xNode) {
        if (xNode.getRight() == nil) {
            throw new IllegalStateException("Right child of a xNode must exist for left rotation");
        }
        RedBlackSearchTreeNode<K> yNode = xNode.getRight();

        // Put yNode's left to xNode's right.
        xNode.setRight(yNode.getLeft());
        if (yNode.getLeft() != nil) {
            yNode.getLeft().setParent(xNode);
        }

        // Update yNode parent.
        yNode.setParent(xNode.getParent());

        // Update xNode's parent to point to yNode instead of xNode.
        if (xNode.getParent() == nil) {
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
        if (yNode.getLeft() == nil) {
            throw new IllegalStateException("Left child of a yNode must exist for right rotation");
        }
        RedBlackSearchTreeNode<K> xNode = yNode.getLeft();

        // Put xNode's right to yNode's left.
        yNode.setLeft(xNode.getRight());
        if (xNode.getRight() != nil) {
            xNode.getRight().setParent(yNode);
        }

        // Update xNode parent.
        xNode.setParent(yNode.getParent());

        // Update yNode's parent to point to xNode instead of yNode.
        RedBlackSearchTreeNode<K> yParent = yNode.getParent();
        if (yParent == nil) {
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

    private void insertFixup(RedBlackSearchTreeNode<K> zNode) {
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
        root.setColor(BLACK);
    }

    @Override
    public void insert(K key) {
        RedBlackSearchTreeNode<K> parentNode = nil;
        RedBlackSearchTreeNode<K> currentNode = root;

        while (currentNode != nil) {
            parentNode = currentNode;
            int comparisonResult = key.compareTo(currentNode.getKey());
            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        DefaultRedBlackSearchTreeNode<K> nodeToInsert = new DefaultRedBlackSearchTreeNode<>();
        nodeToInsert.setLeft(nil);
        nodeToInsert.setRight(nil);
        nodeToInsert.setKey(key);
        nodeToInsert.setColor(RED);

        if (parentNode == nil) {
            nodeToInsert.setParent(nil);
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

    private void transplant(RedBlackSearchTreeNode<K> uNode, RedBlackSearchTreeNode<K> vNode) {
        RedBlackSearchTreeNode<K> uNodeParent = uNode.getParent();
        if (uNodeParent == nil) {
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

        if (zNode.getLeft() == nil) {
            xNode = zNode.getRight();
            transplant(zNode, zNode.getRight());
        } else if (zNode.getRight() == nil) {
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
                    xNode = root;
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
                    xNode = root;
                }

            }
        }
        xNode.setColor(BLACK);
    }

    private void inorderWalkRecursive(RedBlackSearchTreeNode<K> node, Consumer<K> consumer) {
        if (node != nil) {
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
        if (node != nil) {
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
        if (node != nil) {
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
        while (currentNode != nil && !currentNode.getKey().equals(key)) {
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
        return search(key) != nil;
    }

    private RedBlackSearchTreeNode<K> findMinimumNode(RedBlackSearchTreeNode<K> baseNode) {
        RedBlackSearchTreeNode<K> currentNode = baseNode;
        while (currentNode.getLeft() != nil) {
            currentNode = currentNode.getLeft();
        }
        return currentNode;
    }

    @Override
    public K getMinimum() {
        if (root == nil) {
            return null;
        }
        RedBlackSearchTreeNode<K> minimumNode = findMinimumNode(root);
        return minimumNode != nil ? minimumNode.getKey() : null;
    }

    private RedBlackSearchTreeNode<K> findMaximumNode(RedBlackSearchTreeNode<K> baseNode) {
        RedBlackSearchTreeNode<K> currentNode = baseNode;
        while (currentNode.getRight() != nil) {
            currentNode = currentNode.getRight();
        }
        return currentNode;
    }

    @Override
    public K getMaximum() {
        if (root == nil) {
            return null;
        }
        RedBlackSearchTreeNode<K> maximumNode = findMaximumNode(root);
        return maximumNode != nil ? maximumNode.getKey() : null;
    }

    @Override
    public K getSuccessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        RedBlackSearchTreeNode<K> node = search(key);
        if (node == nil) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getRight() != nil) {
            return findMinimumNode(node.getRight()).getKey();
        } else {
            RedBlackSearchTreeNode<K> parentNode = node.getParent();
            while (parentNode != nil && parentNode.getLeft() != node) {
                node = parentNode;
                parentNode = parentNode.getParent();
            }
            return parentNode != nil ? parentNode.getKey() : null;
        }
    }

    @Override
    public K getPredecessorOf(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        RedBlackSearchTreeNode<K> node = search(key);
        if (node == nil) {
            throw new BinarySearchTreeException("There is no such key in the tree: " + key);
        }

        if (node.getLeft() != nil) {
            return findMaximumNode(node.getLeft()).getKey();
        } else {
            RedBlackSearchTreeNode<K> parentNode = node.getParent();
            while (parentNode != nil && parentNode.getRight() != node) {
                node = parentNode;
                parentNode = parentNode.getParent();
            }
            return parentNode != nil ? parentNode.getKey() : null;
        }
    }

    @Override
    public void clear() {
        root = nil;
    }

    RedBlackSearchTreeNode<K> getRoot() {
        return root;
    }

    void print() {
        BinaryTreePrinter.printNode(root, nil);
    }

}
