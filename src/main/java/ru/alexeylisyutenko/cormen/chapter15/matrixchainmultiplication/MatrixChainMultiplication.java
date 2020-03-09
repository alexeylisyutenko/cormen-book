package ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication;

import lombok.Value;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.List;

import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.Matrices.getMatrixChainDimensions;

/**
 * Cormen. 15.2 Matrix-chain multiplication.
 */
public final class MatrixChainMultiplication {
    private MatrixChainMultiplication() {
    }

    /**
     * Find an optimal parenthesization of a matrix chain.
     *
     * @param matrixChain matrix chain
     * @return optimal parenthesization of a matrix chain
     */
    public static Parenthesization matrixChainOrder(List<RealMatrix> matrixChain) {
        int[] chainDimensions = getMatrixChainDimensions(matrixChain);
        return matrixChainOrder(chainDimensions);
    }

    /**
     * Find an optimal parenthesization of a matrix chain.
     *
     * @param chainDimensions dimensions of a matrix chain
     * @return optimal parenthesization of a matrix chain
     */
    public static Parenthesization matrixChainOrder(int[] chainDimensions) {
        int size = chainDimensions.length - 1;

        int[][] parenthesis = new int[size][size];
        int[][] costs = new int[size][size];
        for (int i = 1; i <= size; i++) {
            costs[i - 1][i - 1] = 0;
        }

        for (int len = 2; len <= size; len++) {
            for (int i = 1; i <= size - len + 1; i++) {
                int j = i + len - 1;
                costs[i - 1][j - 1] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = costs[i - 1][k - 1] + costs[k][j - 1] + chainDimensions[i - 1] * chainDimensions[k] * chainDimensions[j];
                    if (q < costs[i - 1][j - 1]) {
                        costs[i - 1][j - 1] = q;
                        parenthesis[i - 1][j - 1] = k;
                    }
                }

            }
        }
        return new Parenthesization(costs[0][size - 1], parenthesis);
    }

    /**
     * Forms a string with an optimal parenthesization.
     *
     * @param parenthesization result of the {@link MatrixChainMultiplication#matrixChainOrder} method
     * @return string with an optimal parenthesization
     */
    public static String printOptimalParenthesis(Parenthesization parenthesization) {
        StringBuilder sb = new StringBuilder();
        int[][] parenthesis = parenthesization.getParenthesis();
        printOptimalParenthesisAux(sb, parenthesis, 1, parenthesis.length);
        return sb.toString();
    }

    private static void printOptimalParenthesisAux(StringBuilder sb, int[][] parenthesis, int i, int j) {
        if (i == j) {
            sb.append("A").append(i);
        } else {
            sb.append("(");
            printOptimalParenthesisAux(sb, parenthesis, i, parenthesis[i - 1][j - 1]);
            printOptimalParenthesisAux(sb, parenthesis, parenthesis[i - 1][j - 1] + 1, j);
            sb.append(")");
        }
    }

    @Value
    public static class Parenthesization {
        int cost;
        int[][] parenthesis;
    }
}
