package ru.alexeylisyutenko.cormen.chapter15.optimalbinarysearchtree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptimalBinarySearchTreesTest {

    @Test
    void optimalBinarySearchTree() {
        Probabilities probabilities = new Probabilities(
                new double[]{0.15, 0.10, 0.05, 0.10, 0.20},
                new double[]{0.05, 0.10, 0.05, 0.05, 0.05, 0.10}
        );

        OptimalBinarySearchTrees.ExpectedCostsAndRoots expectedCostsAndRoots = OptimalBinarySearchTrees.optimalBinarySearchTree(probabilities);

        double[][] expectedCosts = expectedCostsAndRoots.getExpectedCosts();
        assertEquals(0.05, expectedCosts[1][0], 0.001);
        assertEquals(0.45, expectedCosts[1][1], 0.001);
        assertEquals(0.90, expectedCosts[1][2], 0.001);
        assertEquals(1.25, expectedCosts[1][3], 0.001);
        assertEquals(1.75, expectedCosts[1][4], 0.001);
        assertEquals(2.75, expectedCosts[1][5], 0.001);
        assertEquals(0.10, expectedCosts[2][1], 0.001);
        assertEquals(0.40, expectedCosts[2][2], 0.001);
        assertEquals(0.70, expectedCosts[2][3], 0.001);
        assertEquals(1.20, expectedCosts[2][4], 0.001);
        assertEquals(2.00, expectedCosts[2][5], 0.001);
        assertEquals(0.05, expectedCosts[3][2], 0.001);
        assertEquals(0.25, expectedCosts[3][3], 0.001);
        assertEquals(0.60, expectedCosts[3][4], 0.001);
        assertEquals(1.30, expectedCosts[3][5], 0.001);
        assertEquals(0.05, expectedCosts[4][3], 0.001);
        assertEquals(0.30, expectedCosts[4][4], 0.001);
        assertEquals(0.90, expectedCosts[4][5], 0.001);
        assertEquals(0.05, expectedCosts[5][4], 0.001);
        assertEquals(0.50, expectedCosts[5][5], 0.001);
        assertEquals(0.10, expectedCosts[6][5], 0.001);

        int[][] roots = expectedCostsAndRoots.getRoots();
        assertEquals(1, roots[1][1]);
        assertEquals(1, roots[1][2]);
        assertEquals(2, roots[1][3]);
        assertEquals(2, roots[1][4]);
        assertEquals(2, roots[1][5]);
        assertEquals(2, roots[2][2]);
        assertEquals(2, roots[2][3]);
        assertEquals(2, roots[2][4]);
        assertEquals(4, roots[2][5]);
        assertEquals(3, roots[3][3]);
        assertEquals(4, roots[3][4]);
        assertEquals(5, roots[3][5]);
        assertEquals(4, roots[4][4]);
        assertEquals(5, roots[4][5]);
        assertEquals(5, roots[5][5]);

    }


}