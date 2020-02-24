package ru.alexeylisyutenko.cormen.chapter14.intervaltree;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedBinarySearchTree;

import java.util.Objects;
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
    protected void updateSingleNodeAttributes(IntervalTreeNode node) {
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
        Objects.requireNonNull(interval, "interval cannot be null");

        IntervalTreeNode nodeToDelete = intervalSearchExactly(interval)
                .orElseThrow(() -> new BinarySearchTreeException("There is no such interval in the tree"));
        deleteNode(nodeToDelete);
    }

    @Override
    public Optional<Interval> intervalSearchOverlapping(Interval interval) {
        Objects.requireNonNull(interval, "interval cannot be null");

        IntervalTreeNode currentNode = root;
        while (currentNode != NIL && !intervalsOverlap(currentNode.getKey(), interval)) {
            if (currentNode.getLeft() != NIL && currentNode.getLeft().getMax() >= interval.getLow()) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        if (currentNode == NIL) {
            return Optional.empty();
        } else {
            return Optional.of(currentNode.getKey());
        }
    }

    private boolean intervalsOverlap(Interval interval1, Interval interval2) {
        return interval1.getLow() <= interval2.getHigh() && interval2.getLow() <= interval1.getHigh();
    }

    private boolean intervalsEqual(Interval interval1, Interval interval2) {
        return interval1.getLow() == interval2.getLow() && interval1.getHigh() == interval2.getHigh();
    }

    @Override
    public boolean intervalContains(Interval interval) {
        Objects.requireNonNull(interval, "interval cannot be null");
        return intervalSearchExactly(interval).isPresent();
    }

    private Optional<IntervalTreeNode> intervalSearchExactly(Interval interval) {
        IntervalTreeNode currentNode = root;
        while (currentNode != NIL && !intervalsEqual(currentNode.getKey(), interval)) {
            if (interval.getHigh() > currentNode.getMax()) {
                currentNode = NIL;
            } else if (interval.getLow() < currentNode.getKey().getLow()) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }
        if (currentNode == NIL) {
            return Optional.empty();
        } else {
            return Optional.of(currentNode);
        }
    }
}
