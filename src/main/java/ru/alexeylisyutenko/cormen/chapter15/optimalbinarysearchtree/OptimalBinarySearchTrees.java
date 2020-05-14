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

        for (int len = 1; len <= size ; len++) {
            for (int i = 1; i <= size - len + 1; i++) {
                int j = i + len - 1;
                expectedCosts[i][j] = Integer.MAX_VALUE;
                probabilitySums[i][j] = probabilitySums[i][j - 1] + probabilities.getHitProbability(j) + probabilities.getMissProbability(j);
                for (int r = i; r <= j ; r++) {
                    double currentExpectedCost = expectedCosts[i][r - 1] + expectedCosts[r + 1][j] + probabilitySums[i][j];
                    if (currentExpectedCost < expectedCosts[i][j]) {
                        expectedCosts[i][j] = currentExpectedCost;
                        roots[i][j] = r;
                    }
                }
            }
        }

        return new ExpectedCostsAndRoots(expectedCosts, roots);
    }

    @Value
    public static class ExpectedCostsAndRoots {
        double[][] expectedCosts;
        int[][] roots;
    }
}
