package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter14.intervaltree.Interval;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;
import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.RED;

public class DefaultPointOfMaximumOverlapTree extends AbstractRedBlackBasedBinarySearchTree<PointOfMaximumOverlapTreeKey, PointOfMaximumOverlapTreeNode> implements PointOfMaximumOverlapTree {
    private final static PointOfMaximumOverlapTreeNode NIL;

    static {
        NIL = new DefaultPointOfMaximumOverlapTreeNode();
        NIL.setColor(BLACK);
        NIL.setSum(0);
        NIL.setMaximumSum(0);
    }

    @Override
    public void intervalInsert(Interval interval) {
        insertNode(createNodeToInsert(interval.getLow(), +1));
        insertNode(createNodeToInsert(interval.getHigh(), -1));
    }

    private PointOfMaximumOverlapTreeNode createNodeToInsert(int key, int value) {
        PointOfMaximumOverlapTreeNode lowNodeToInsert = new DefaultPointOfMaximumOverlapTreeNode();
        lowNodeToInsert.setLeft(getNil());
        lowNodeToInsert.setRight(getNil());
        lowNodeToInsert.setKey(new PointOfMaximumOverlapTreeKey(key, value));
        lowNodeToInsert.setColor(RED);
        lowNodeToInsert.setValue(value);
        lowNodeToInsert.setSum(0);
        lowNodeToInsert.setMaximumSum(0);
        lowNodeToInsert.setPointOfMaximumSum(0);
        return lowNodeToInsert;
    }

    @Override
    public void intervalDelete(Interval interval) {
        delete(new PointOfMaximumOverlapTreeKey(interval.getHigh(), -1));
        delete(new PointOfMaximumOverlapTreeKey(interval.getLow(), +1));
    }

    @Override
    public int findPom() {
        if (root == NIL) {
            throw new BinarySearchTreeException("Tree is empty");
        }
        return root.getPointOfMaximumSum();
    }

    @Override
    protected PointOfMaximumOverlapTreeNode getNil() {
        return NIL;
    }

    @Override
    protected void updateSingleNodeAttributes(PointOfMaximumOverlapTreeNode node) {
        // Sum.
        node.setSum(node.getLeft().getSum() + node.getValue() + node.getRight().getSum());

        // Maximum sum and point of maximum sum.
        int left = node.getLeft().getMaximumSum();
        int leftCurrent = node.getLeft().getSum() + node.getValue();
        int leftCurrentRight = node.getLeft().getSum() + node.getValue() + node.getRight().getMaximumSum();

        if (left >= leftCurrent && left >= leftCurrentRight) {
            node.setMaximumSum(left);
            node.setPointOfMaximumSum(node.getLeft().getPointOfMaximumSum());
        } else if (leftCurrent >= left && leftCurrent >= leftCurrentRight) {
            node.setMaximumSum(leftCurrent);
            node.setPointOfMaximumSum(node.getKey().getEndpoint());
        } else {
            node.setMaximumSum(leftCurrentRight);
            node.setPointOfMaximumSum(node.getRight().getPointOfMaximumSum());
        }
    }

}
