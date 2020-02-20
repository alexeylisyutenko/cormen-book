package ru.alexeylisyutenko.cormen.chapter14.mingaptree;

import org.apache.commons.lang3.math.NumberUtils;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedBinarySearchTree;

import static org.apache.commons.lang3.math.NumberUtils.min;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;

public class DefaultMinGapTree extends AbstractRedBlackBasedBinarySearchTree<Integer, MinGapTreeNode> implements MinGapTree {
    private final static MinGapTreeNode NIL;

    static {
        NIL = new DefaultMinGapTreeNode();
        NIL.setColor(BLACK);
        NIL.setMinimum(Integer.MAX_VALUE);
        NIL.setMaximum(Integer.MIN_VALUE);
        NIL.setMinimumGap(Integer.MAX_VALUE);
    }

    @Override
    protected MinGapTreeNode getNil() {
        return NIL;
    }

    @Override
    public int minGap() {
        if (root == NIL) {
            throw new BinarySearchTreeException("Tree is empty");
        }
        if (root.getLeft() == NIL && root.getRight() == NIL) {
            throw new BinarySearchTreeException("There is only one element in the tree. Minimum gap is not defined.");
        }
        return root.getMinimumGap();
    }

    @Override
    protected MinGapTreeNode createNodeToInsert(Integer key) {
        DefaultMinGapTreeNode nodeToInsert = new DefaultMinGapTreeNode();
        nodeToInsert.setMinimum(0);
        nodeToInsert.setMaximum(0);
        nodeToInsert.setMinimumGap(Integer.MAX_VALUE);
        return nodeToInsert;
    }

    @Override
    protected void beforeInsertFixup(MinGapTreeNode nodeToInsert) {
        updateNodesAttributesOnPathToRoot(nodeToInsert);
    }

    @Override
    protected void beforeDeleteFixup(MinGapTreeNode xNode) {
        updateNodesAttributesOnPathToRoot(xNode);
    }

    protected void updateNodesAttributesOnPathToRoot(MinGapTreeNode currentNode) {
        while (currentNode != NIL) {
            updateSingleNodeAttributes(currentNode);
            currentNode = currentNode.getParent();
        }
    }

    private void updateSingleNodeAttributes(MinGapTreeNode node) {
        // Minimum attribute.
        int minimum = min(node.getLeft().getMinimum(), node.getRight().getMinimum(), node.getKey());
        node.setMinimum(minimum);

        // Maximum attribute.
        int maximum = NumberUtils.max(node.getLeft().getMaximum(), node.getRight().getMaximum(), node.getKey());
        node.setMaximum(maximum);

        // Minimum gap attribute.
        int minimumGap = Integer.MAX_VALUE;
        if (node.getLeft() != NIL) {
            minimumGap = min(
                    minimumGap,
                    node.getLeft().getMinimumGap(),
                    node.getKey() - node.getLeft().getKey(),
                    node.getKey() - node.getLeft().getMaximum()
            );
        }
        if (node.getRight() != NIL) {
            minimumGap = min(
                    minimumGap,
                    node.getRight().getMinimumGap(),
                    node.getRight().getKey() - node.getKey(),
                    node.getRight().getMinimum() - node.getKey()
            );
        }
        node.setMinimumGap(minimumGap);
    }

    @Override
    protected void rotateLeft(MinGapTreeNode xNode) {
        MinGapTreeNode yNode = xNode.getRight();
        super.rotateLeft(xNode);

        yNode.setMinimum(xNode.getMinimum());
        yNode.setMaximum(xNode.getMaximum());
        yNode.setMinimumGap(xNode.getMinimumGap());

        updateSingleNodeAttributes(xNode);
    }

    @Override
    protected void rotateRight(MinGapTreeNode yNode) {
        MinGapTreeNode xNode = yNode.getLeft();
        super.rotateRight(yNode);

        xNode.setMinimum(yNode.getMinimum());
        xNode.setMaximum(yNode.getMaximum());
        xNode.setMinimumGap(yNode.getMinimumGap());

        updateSingleNodeAttributes(yNode);
    }
}