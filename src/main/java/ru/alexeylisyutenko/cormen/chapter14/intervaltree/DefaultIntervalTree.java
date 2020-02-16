package ru.alexeylisyutenko.cormen.chapter14.intervaltree;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedBinarySearchTree;

import java.util.Optional;

import static org.apache.commons.lang3.math.NumberUtils.max;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;

public class DefaultIntervalTree extends AbstractRedBlackBasedBinarySearchTree<Interval, IntervalTreeNode> implements IntervalTree {
    private final static IntervalTreeNode NIL;

    static {
        NIL = new DefaultIntervalTreeNode();
        NIL.setColor(BLACK);
    }

    @Override
    protected void rotateLeft(IntervalTreeNode xNode) {
        IntervalTreeNode yNode = xNode.getRight();
        super.rotateLeft(xNode);
        yNode.setMax(xNode.getMax());
        updateSingleNodeAttributes(xNode);
    }

    @Override
    protected void rotateRight(IntervalTreeNode yNode) {
        IntervalTreeNode xNode = yNode.getLeft();
        super.rotateRight(yNode);
        xNode.setMax(yNode.getMax());
        updateSingleNodeAttributes(yNode);
    }

    @Override
    protected IntervalTreeNode createNodeToInsert(Interval key) {
        DefaultIntervalTreeNode nodeToInsert = new DefaultIntervalTreeNode();
        nodeToInsert.setMax(key.getHigh());
        return nodeToInsert;
    }

    @Override
    protected IntervalTreeNode getNil() {
        return NIL;
    }

    @Override
    protected void beforeInsertFixup(IntervalTreeNode nodeToInsert) {
        updateNodesAttributesOnPathToRoot(nodeToInsert);
    }

    @Override
    protected void beforeDeleteFixup(IntervalTreeNode xNode) {
        updateNodesAttributesOnPathToRoot(xNode);
    }

    protected void updateNodesAttributesOnPathToRoot(IntervalTreeNode currentNode) {
        while (currentNode != NIL) {
            updateSingleNodeAttributes(currentNode);
            currentNode = currentNode.getParent();
        }
    }

    private void updateSingleNodeAttributes(IntervalTreeNode node) {
        int maximum = max(node.getLeft() == NIL ? Integer.MIN_VALUE : node.getLeft().getMax(),
                node.getRight() == NIL ? Integer.MIN_VALUE : node.getRight().getMax(),
                node.getKey().getHigh());
        node.setMax(maximum);
    }

    @Override
    public void intervalInsert(Interval interval) {
        insert(interval);
    }

    @Override
    public void intervalDelete(Interval interval) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    public Optional<Interval> intervalSearch(Interval interval) {
        throw new IllegalStateException("Not implemented yet");
    }
}
