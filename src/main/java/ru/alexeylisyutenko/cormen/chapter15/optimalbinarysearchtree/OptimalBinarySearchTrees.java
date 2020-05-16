package ru.alexeylisyutenko.cormen.chapter15.optimalbinarysearchtree;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.simplebinarytree.DefaultSimpleBinarySearchTreeNode;
import ru.alexeylisyutenko.cormen.chapter12.simplebinarytree.SimpleBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.simplebinarytree.SimpleBinarySearchTreeNode;

import java.util.List;
import java.util.Objects;

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

        for (int len = 1; len <= size; len++) {
            for (int i = 1; i <= size - len + 1; i++) {
                int j = i + len - 1;
                expectedCosts[i][j] = Integer.MAX_VALUE;
                probabilitySums[i][j] = probabilitySums[i][j - 1] + probabilities.getHitProbability(j) + probabilities.getMissProbability(j);
                for (int r = i; r <= j; r++) {
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

    /**
     * Construct optimal binary search tree for given keys and roots table.
     *
     * @param <K>   key type
     * @param keys  keys of a tree
     * @param roots roots table describing optimal solution
     * @return optimal binary search tree for a given key
     */
    public static <K extends Comparable<K>> BinarySearchTree<K> constructOptimalBinarySearchTree(List<K> keys, int[][] roots) {
        Objects.requireNonNull(keys, "keys cannot be null");
        Objects.requireNonNull(roots, "roots cannot be null");
        if (keys.isEmpty()) {
            throw new IllegalArgumentException("There must be at least one key");
        }
        if (keys.size() != roots.length - 1) {
            throw new IllegalArgumentException("keys list and roots table have incompatible sizes");
        }

        SimpleBinarySearchTreeNode<K> root = constructOptimalBinarySearchTreeAux(keys, roots, 1, keys.size());
        return new OptimalBinarySearchTree<>(root);
    }

    private static <K extends Comparable<K>> SimpleBinarySearchTreeNode<K> constructOptimalBinarySearchTreeAux(List<K> keys, int[][] roots, int i, int j) {
        if (i > j) {
            return null;
        }
        int subtreeRootIndex = roots[i][j];
        SimpleBinarySearchTreeNode<K> left = constructOptimalBinarySearchTreeAux(keys, roots, i, subtreeRootIndex - 1);
        SimpleBinarySearchTreeNode<K> right = constructOptimalBinarySearchTreeAux(keys, roots, subtreeRootIndex + 1, j);
        return new DefaultSimpleBinarySearchTreeNode<>(left, right, keys.get(subtreeRootIndex - 1));
    }

    /**
     * Prints optimal binary search tree in a textual form. Exercise 15.5-1.
     *
     * @param roots roots table
     */
    public static void printOptimalBinarySearchTree(int[][] roots) {
        Objects.requireNonNull(roots, "roots cannot be null");

        int size = roots.length - 1;
        if (size == 0) {
            return;
        }

        int root = roots[1][size];
        System.out.println(String.format("k%d is the root", root));
        printOptimalBinarySearchTreeAux(roots, 1, root - 1, String.format("the left child of k%d", root));
        printOptimalBinarySearchTreeAux(roots, root + 1, size, String.format("the right child of k%d", root));
    }

    private static void printOptimalBinarySearchTreeAux(int[][] roots, int i, int j, String parentInfo) {
        if (i > j) {
            System.out.println(String.format("d%d is %s", j, parentInfo));
        } else {
            int root = roots[i][j];
            System.out.println(String.format("k%d is %s", root, parentInfo));
            printOptimalBinarySearchTreeAux(roots, i, root - 1, String.format("the left child of k%d", root));
            printOptimalBinarySearchTreeAux(roots, root + 1, j, String.format("the right child of k%d", root));
        }
    }

    private static class OptimalBinarySearchTree<K extends Comparable<K>> extends SimpleBinarySearchTree<K> {
        public OptimalBinarySearchTree(SimpleBinarySearchTreeNode<K> root) {
            this.root = root;
        }

        @Override
        public void insert(K key) {
            throw new IllegalStateException("Insertion is not supported");
        }

        @Override
        public void delete(K key) {
            throw new IllegalStateException("Deletion is not supported");
        }
    }
}
