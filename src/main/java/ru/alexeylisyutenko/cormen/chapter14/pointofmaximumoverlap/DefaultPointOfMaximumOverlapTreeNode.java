package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import ru.alexeylisyutenko.cormen.chapter14.base.AbstractRedBlackBasedSearchTreeNode;
import ru.alexeylisyutenko.cormen.chapter14.mingaptree.MinGapTreeNode;

public class DefaultPointOfMaximumOverlapTreeNode extends AbstractRedBlackBasedSearchTreeNode<Integer, PointOfMaximumOverlapTreeNode>  implements PointOfMaximumOverlapTreeNode {
    private int value;
    private int sum;
    private int lowerChainSum;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getSum() {
        return sum;
    }

    @Override
    public void setSum(int sum) {
        this.sum = sum
    }

    @Override
    public int getLowerChainSum() {
        return lowerChainSum;
    }

    @Override
    public void setLowerChainSum(int lowerChainSum) {
        this.lowerChainSum = lowerChainSum;
    }

    @Override
    public String toString() {
        return String.format("%d:%d:%d ", key, value, sum);
    }
}
