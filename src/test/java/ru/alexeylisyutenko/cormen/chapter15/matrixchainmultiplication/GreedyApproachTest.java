package ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.Matrices.*;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.MatrixChainMultiplication.matrixChainOrder;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.MatrixChainMultiplication.printOptimalParenthesis;

/**
 * Cormen. Exercise 15.3-4.
 */
public class GreedyApproachTest {

    @Test
    void demo() {
        List<RealMatrix> matrixChain = List.of(
                MatrixUtils.createRealMatrix(1, 2),
                MatrixUtils.createRealMatrix(2, 4),
                MatrixUtils.createRealMatrix(4, 8)
        );
        System.out.println(matrixChainToDimensionTable(matrixChain));

        MatrixChainMultiplication.Parenthesization parenthesization = matrixChainOrderGreedy(matrixChain);
        System.out.println(parenthesization);
        System.out.println(printOptimalParenthesis(parenthesization));


        MatrixChainMultiplication.Parenthesization optimalParenthesization = matrixChainOrder(matrixChain);
        System.out.println(optimalParenthesization);
        System.out.println(printOptimalParenthesis(optimalParenthesization));
    }

    @RepeatedTest(1000)
    void findCounterExample() {
        List<RealMatrix> matrixChain = createRealMatrixChain(3, 10, 10);

        String greedyParenthesization = printOptimalParenthesis(matrixChainOrderGreedy(matrixChain));
        String optimalParenthesization = printOptimalParenthesis(matrixChainOrder(matrixChain));

        assertEquals(optimalParenthesization, greedyParenthesization, () -> {
            StringBuilder sb = new StringBuilder();
            sb.append("\r\n");
            sb.append(matrixChainToDimensionTable(matrixChain)).append("\r\n");
            sb.append("Greedy: \t").append(greedyParenthesization).append("\r\n");
            sb.append("Optimal: \t").append(optimalParenthesization).append("\r\n");
            return sb.toString();
        });
    }

    private MatrixChainMultiplication.Parenthesization matrixChainOrderGreedy(List<RealMatrix> matrixChain) {
        Objects.requireNonNull(matrixChain);
        if (matrixChain.size() <= 1) {
            throw new IllegalArgumentException("matrix chain size must be greater than 1");
        }

        int size = matrixChain.size();
        int[] chainDimensions = getMatrixChainDimensions(matrixChain);
        int[][] parenthesis = new int[size][size];

        int cost = matrixChainOrderGreedyAux(chainDimensions, parenthesis, 1, size);
        return new MatrixChainMultiplication.Parenthesization(cost, parenthesis);
    }

    private int matrixChainOrderGreedyAux(int[] chainDimensions, int[][] parenthesis, int left, int right) {
        if (left == right) {
            return 0;
        }

        int k = 0;
        int splitCost = Integer.MAX_VALUE;
        for (int i = left; i <= right - 1; i++) {
            int cost = chainDimensions[left - 1] * chainDimensions[i] * chainDimensions[right];
            if (cost < splitCost) {
                splitCost = cost;
                k = i;
            }
        }

        parenthesis[left - 1][right - 1] = k;

        return matrixChainOrderGreedyAux(chainDimensions, parenthesis, left, k) +
                matrixChainOrderGreedyAux(chainDimensions, parenthesis, k + 1, right) +
                chainDimensions[left - 1] * chainDimensions[k] * chainDimensions[right];
    }

}
