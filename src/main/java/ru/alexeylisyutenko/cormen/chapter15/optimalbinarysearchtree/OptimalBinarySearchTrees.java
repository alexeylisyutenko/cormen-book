package ru.alexeylisyutenko.cormen.chapter15.optimalbinarysearchtree;

import lombok.Value;

/**
 * Cormen. 15.5 Optimal binary search trees.
 */
public final class OptimalBinarySearchTrees {
    private OptimalBinarySearchTrees() {
    }

    /**
     * Find an optimal binary search tree for keys with given probabilities.
     *
     * @param probabilities probabilities for each key in BTS that a search will be for that key
     * @return expected costs and roots for each subtree
     */
    public static ExpectedCostsAndRoots optimalBinarySearchTree(Probabilities probabilities) {
        int size = probabilities.getHit().length;

        double[][] expectedCosts = new double[size + 2][size + 1];
        double[][] probabilitySums = new double[size + 2][size + 1];
        int[][] roots = new int[size + 1][size + 1];

        for (int i = 1; i <= size + 1; i++) {
            expectedCosts[i][i - 1] = probabilities.getMissProbability(i - 1);
            probabilitySums[i][i - 1] = probabilities.getMissProbability(i - 1);
        }

        return null;
    }

    @Value
    private static class ExpectedCostsAndRoots {
        double[][] expectedCosts;
        int[][] roots;
    }
}
