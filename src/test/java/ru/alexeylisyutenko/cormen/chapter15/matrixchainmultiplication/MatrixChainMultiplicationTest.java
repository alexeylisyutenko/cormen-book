package ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.Matrices.createRealMatrixChain;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.Matrices.matrixChainToDimensionTable;
import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.MatrixChainMultiplication.printOptimalParenthesis;

class MatrixChainMultiplicationTest {

    @Test
    void demo() {
        List<RealMatrix> matrixChain = createRealMatrixChain(20);
        System.out.println(matrixChainToDimensionTable(matrixChain));

        MatrixChainMultiplication.Parenthesization parenthesization = MatrixChainMultiplication.matrixChainOrder(matrixChain);
        System.out.println(parenthesization);
        System.out.println(printOptimalParenthesis(parenthesization));
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

}