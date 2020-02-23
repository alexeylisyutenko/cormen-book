package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;
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

    private PointOfMaximumOverlapTreeNode createNodeToInsert(int key, int value) {
        PointOfMaximumOverlapTreeNode lowNodeToInsert = new DefaultPointOfMaximumOverlapTreeNode();
        lowNodeToInsert.setLeft(getNil());
        lowNodeToInsert.setRight(getNil());
        lowNodeToInsert.setKey(key);
        lowNodeToInsert.setColor(RED);
        lowNodeToInsert.setValue(value);
        lowNodeToInsert.setSum(0);
        lowNodeToInsert.setPointOfMaximumOverlap(0);
        lowNodeToInsert.setMaximumOverlappingIntervals(0);
        lowNodeToInsert.setPositiveValueSum(0);
        lowNodeToInsert.setNegativeValueSum(0);
        return lowNodeToInsert;
    }

    @Override
    public void intervalDelete(Interval interval) {
        delete(interval.getHigh());
        delete(interval.getLow());
    }

    @Override
    public int findPom() {
        if (root == NIL) {
            throw new BinarySearchTreeException("Tree is empty");
        }
        return root.getPointOfMaximumOverlap();
    }

    @Override
    protected PointOfMaximumOverlapTreeNode getNil() {
        return NIL;
    }

    // TODO: Refactor
    @Override
    protected void updateSingleNodeAttributes(PointOfMaximumOverlapTreeNode node) {
        // Sum.
        updateNodeSum(node);

        // Positive value sum.
        int positiveValueSum = node.getValue() == +1 ? 1 : 0;
        if (node.getLeft() != NIL) {
            positiveValueSum += node.getLeft().getPositiveValueSum();
        }
        if (node.getRight() != NIL) {
            positiveValueSum += node.getRight().getPositiveValueSum();
        }
        node.setPositiveValueSum(positiveValueSum);

        // Negative value sum.
        int negativeValueSum = node.getValue() == -1 ? 1 : 0;
        if (node.getLeft() != NIL) {
            negativeValueSum += node.getLeft().getNegativeValueSum();
        }
        if (node.getRight() != NIL) {
            negativeValueSum += node.getRight().getNegativeValueSum();
        }
        node.setNegativeValueSum(negativeValueSum);

        // Left negative chain.
        int leftNegativeChain = 0;
        if (node.getLeft() != NIL) {
            leftNegativeChain = node.getLeft().getLeftNegativeChain();
        }
        if (node.getLeft() == NIL && node.getValue() == -1) {
            leftNegativeChain++;
        } else if (node.getLeft() != NIL && node.getLeft().getPositiveValueSum() == 0 && node.getValue() == -1) {
            leftNegativeChain++;
        }
        if ((node.getLeft() == NIL || node.getLeft() != NIL && node.getLeft().getPositiveValueSum() == 0)
                && node.getValue() == -1
                && node.getRight() != NIL) {

            leftNegativeChain += node.getRight().getLeftNegativeChain();
        }
        node.setLeftNegativeChain(leftNegativeChain);

        // Initial.
        if (node.getValue() == +1) {
            node.setPointOfMaximumOverlap(node.getKey());
            node.setMaximumOverlappingIntervals(1);
        } else {
            node.setPointOfMaximumOverlap(Integer.MIN_VALUE);
            node.setMaximumOverlappingIntervals(Integer.MIN_VALUE);
        }

        // Left.
        if (node.getLeft() != NIL) {
            if (node.getValue() == +1) {
                if (node.getLeft().getSum() > 0) {
                    int currentNodeOverlappingIntervals = node.getLeft().getSum() + 1;
                    if (currentNodeOverlappingIntervals > node.getLeft().getMaximumOverlappingIntervals()) {
                        node.setPointOfMaximumOverlap(node.getKey());
                        node.setMaximumOverlappingIntervals(currentNodeOverlappingIntervals);
                    }
                } else {
                    if (node.getLeft().getMaximumOverlappingIntervals() > node.getMaximumOverlappingIntervals()) {
                        node.setMaximumOverlappingIntervals(node.getLeft().getMaximumOverlappingIntervals());
                        node.setPointOfMaximumOverlap(node.getLeft().getPointOfMaximumOverlap());
                    }
                }
            } else {
                // What to do here? Nothing. Just copy from the left.
                if (node.getLeft().getMaximumOverlappingIntervals() > node.getMaximumOverlappingIntervals()) {
                    node.setMaximumOverlappingIntervals(node.getLeft().getMaximumOverlappingIntervals());
                    node.setPointOfMaximumOverlap(node.getLeft().getPointOfMaximumOverlap());
                }
            }
        }

        // Right.
        if (node.getRight() != NIL) {
            int currentNodeLeftToRightSum = (node.getLeft() != NIL ? node.getLeft().getSum() : 0) + node.getValue();

            if (currentNodeLeftToRightSum > 0) {
                if (currentNodeLeftToRightSum > node.getRight().getLeftNegativeChain()) {
                    currentNodeLeftToRightSum = currentNodeLeftToRightSum - node.getRight().getLeftNegativeChain();
                    if (node.getRight().getMaximumOverlappingIntervals() + currentNodeLeftToRightSum  >= node.getMaximumOverlappingIntervals()) {
                        node.setPointOfMaximumOverlap(node.getRight().getPointOfMaximumOverlap());
                        node.setMaximumOverlappingIntervals(node.getRight().getMaximumOverlappingIntervals() + currentNodeLeftToRightSum);
                    }
                }

            } else {
                // Right subtree can improve the result only in one way.
                if (node.getRight().getMaximumOverlappingIntervals() >= node.getMaximumOverlappingIntervals()) {
                    node.setPointOfMaximumOverlap(node.getRight().getPointOfMaximumOverlap());
                    node.setMaximumOverlappingIntervals(node.getRight().getMaximumOverlappingIntervals());
                }
            }
        }

    }

    private void updateNodeSum(PointOfMaximumOverlapTreeNode node) {
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
