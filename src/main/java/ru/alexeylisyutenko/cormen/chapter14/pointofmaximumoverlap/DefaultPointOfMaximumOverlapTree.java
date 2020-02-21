package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter14.intervaltree.Interval;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.RED;

public class DefaultPointOfMaximumOverlapTree extends AbstractRedBlackBasedBinarySearchTree<Integer, PointOfMaximumOverlapTreeNode> implements PointOfMaximumOverlapTree {
    private final static PointOfMaximumOverlapTreeNode NIL;

    static {
        NIL = new DefaultPointOfMaximumOverlapTreeNode();
        NIL.setColor(BLACK);
    }

    @Override
    public void intervalInsert(Interval interval) {
        insertNode(createNodeToInsert(interval.getLow(), +1));
        insertNode(createNodeToInsert(interval.getHigh(), -1));
    }

    private PointOfMaximumOverlapTreeNode createNodeToInsert(int intervalLow, int value) {
        PointOfMaximumOverlapTreeNode lowNodeToInsert = new DefaultPointOfMaximumOverlapTreeNode();
        lowNodeToInsert.setLeft(getNil());
        lowNodeToInsert.setRight(getNil());
        lowNodeToInsert.setKey(intervalLow);
        lowNodeToInsert.setColor(RED);
        lowNodeToInsert.setValue(value);
        lowNodeToInsert.setSum(0);
        lowNodeToInsert.setLowerChainSum(0);
        return lowNodeToInsert;
    }

    @Override
    public void intervalDelete(Interval interval) {
        delete(interval.getHigh());
        delete(interval.getLow());
    }

    @Override
    public int findPom() {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    protected PointOfMaximumOverlapTreeNode getNil() {
        return NIL;
    }

    @Override
    protected void updateSingleNodeAttributes(PointOfMaximumOverlapTreeNode node) {
        // Update sum attribute.
        int sum = node.getValue();
        if (node.getLeft() != NIL) {
            sum += node.getLeft().getSum();
        }
        if (node.getRight() != NIL) {
            sum += node.getRight().getSum();
        }
        node.setSum(sum);
    }
}
