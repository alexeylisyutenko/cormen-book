package ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.Matrices.createRealMatrixChain;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.Matrices.matrixChainToDimensionTable;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.MatrixChainMultiplication.printOptimalParenthesis;

class MatrixChainMultiplicationTest {

    @Disabled
    @Test
    void demo() {
        List<RealMatrix> matrixChain = createRealMatrixChain(20);
        System.out.println(matrixChainToDimensionTable(matrixChain));

        MatrixChainMultiplication.Parenthesization parenthesization = MatrixChainMultiplication.matrixChainOrder(matrixChain);
        System.out.println(parenthesization);
        System.out.println(printOptimalParenthesis(parenthesization));
    }

    @Disabled
    @Test
    void demo2() {
        List<RealMatrix> matrixChain = List.of(
                MatrixUtils.createRealMatrix(5, 10),
                MatrixUtils.createRealMatrix(10, 3),
                MatrixUtils.createRealMatrix(3, 12),
                MatrixUtils.createRealMatrix(12, 5),
                MatrixUtils.createRealMatrix(5, 50),
                MatrixUtils.createRealMatrix(50, 6)
        );
        System.out.println(matrixChainToDimensionTable(matrixChain));

        MatrixChainMultiplication.Parenthesization parenthesization = MatrixChainMultiplication.matrixChainOrder(matrixChain);
        System.out.println(parenthesization);
        System.out.println(printOptimalParenthesis(parenthesization));
    }

    @Disabled
    @Test
    void matrixChainMultiplyDemo() {
        List<RealMatrix> matrixChain = createRealMatrixChain(5);
        System.out.println(matrixChainToDimensionTable(matrixChain));

        MatrixChainMultiplication.Parenthesization parenthesization = MatrixChainMultiplication.matrixChainOrder(matrixChain);
        System.out.println(parenthesization);
        System.out.println(printOptimalParenthesis(parenthesization));

        RealMatrix product = MatrixChainMultiplication.matrixChainMultiply(matrixChain);
        System.out.println(product);
    }

    @Test
    void matrixChainOrder() {
        List<RealMatrix> matrixChain = List.of(
                MatrixUtils.createRealMatrix(30, 35),
                MatrixUtils.createRealMatrix(35, 15),
                MatrixUtils.createRealMatrix(15, 5),
                MatrixUtils.createRealMatrix(5, 10),
                MatrixUtils.createRealMatrix(10, 20),
                MatrixUtils.createRealMatrix(20, 25)
        );

        MatrixChainMultiplication.Parenthesization parenthesization = MatrixChainMultiplication.matrixChainOrder(matrixChain);
        assertEquals(15125, parenthesization.getCost());
        assertEquals("((A1(A2A3))((A4A5)A6))", printOptimalParenthesis(parenthesization));
    }

    @Test
    void matrixMultiply() {
        RealMatrix left = MatrixUtils.createRealMatrix(new double[][]{{-3, 3, 4}, {-4, 5, 1}, {-2, 6, -1}, {0, 2, 7}});
        RealMatrix right = MatrixUtils.createRealMatrix(new double[][]{{8, 9, 3, 4}, {10, 5, 1, 11}, {12, 6, 13, 14}});

        RealMatrix product = MatrixChainMultiplication.matrixMultiply(left, right);
        assertEquals(left.multiply(right), product);
    }

    @RepeatedTest(1000)
    void matrixChainMultiplyRandomized() {
        List<RealMatrix> matrixChain = createRealMatrixChain(5);
        RealMatrix product = MatrixChainMultiplication.matrixChainMultiply(matrixChain);
        RealMatrix expectedProduct = matrixChain.stream().reduce(RealMatrix::multiply).orElseThrow();
        assertEquals(expectedProduct, product);
    }

}