package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter14.intervaltree.Interval;

import static ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor.BLACK;

public class DefaultPointOfMaximumOverlapTree extends AbstractRedBlackBasedBinarySearchTree<Integer, PointOfMaximumOverlapTreeNode> implements PointOfMaximumOverlapTree {
    private final static PointOfMaximumOverlapTreeNode NIL;

    static {
        NIL = new DefaultPointOfMaximumOverlapTreeNode();
        NIL.setColor(BLACK);
    }

    @Override
    public void intervalInsert(Interval interval) {
        insert(interval.getLow());
        insert(interval.getHigh());
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
    protected PointOfMaximumOverlapTreeNode createNodeToInsert(Integer key) {
        return null;
    }

    @Override
    protected PointOfMaximumOverlapTreeNode getNil() {
        return NIL;
    }
}
