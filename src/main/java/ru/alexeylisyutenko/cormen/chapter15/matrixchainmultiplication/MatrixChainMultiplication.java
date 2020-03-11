package ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication;

import lombok.Value;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.List;
import java.util.Objects;

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
        Objects.requireNonNull(chainDimensions, "chainDimensions cannot be null");
        if (chainDimensions.length <= 2) {
            throw new IllegalArgumentException("chainDimensions.length must be greater than 2");
        }

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

    /**
     * Multiply matrices in a chain.
     *
     * @param matrixChain matrix chain
     * @return matrix which is a product of all matrices in a chain
     */
    public static RealMatrix matrixChainMultiply(List<RealMatrix> matrixChain) {
        Parenthesization parenthesization = matrixChainOrder(matrixChain);
        return matrixChainMultiplyAux(matrixChain, parenthesization, 1, matrixChain.size());
    }

    private static RealMatrix matrixChainMultiplyAux(List<RealMatrix> matrixChain, Parenthesization parenthesization, int i, int j) {
        if (i == j) {
            return matrixChain.get(i - 1);
        } else {
            int[][] parenthesis = parenthesization.getParenthesis();
            RealMatrix left = matrixChainMultiplyAux(matrixChain, parenthesization, i, parenthesis[i - 1][j - 1]);
            RealMatrix right = matrixChainMultiplyAux(matrixChain, parenthesization, parenthesis[i - 1][j - 1] + 1, j);
            return matrixMultiply(left, right);
        }
    }

    /**
     * Multiply two matrices.
     *
     * @param left left hand side matrix
     * @param right right hand side matrix
     * @return a product matrix (left * right)
     */
    public static RealMatrix matrixMultiply(RealMatrix left, RealMatrix right) {
        Objects.requireNonNull(left, "left cannot be null");
        Objects.requireNonNull(right, "right cannot be null");
        if (left.getColumnDimension() != right.getRowDimension()) {
            throw new IllegalArgumentException("Incompatible matrices");
        }

        RealMatrix product = MatrixUtils.createRealMatrix(left.getRowDimension(), right.getColumnDimension());
        for (int row = 0; row < left.getRowDimension(); row++) {
            for (int column = 0; column < right.getColumnDimension(); column++) {
                double entry = 0.0;
                for (int k = 0; k < left.getColumnDimension(); k++) {
                    entry += left.getEntry(row, k) * right.getEntry(k, column);
                }
                product.setEntry(row, column, entry);
            }
        }
        return product;
    }

    @Value
    public static class Parenthesization {
        int cost;
        int[][] parenthesis;
    }
}
