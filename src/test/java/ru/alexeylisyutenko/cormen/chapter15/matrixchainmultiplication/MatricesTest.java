package ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication;

import org.apache.commons.math3.linear.RealMatrix;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication.Matrices.*;

class MatricesTest {

    @Test
    void matrixChainToDimensionTableDemo() {
        List<RealMatrix> matrixChain = createRealMatrixChain(10);
        System.out.println(matrixChainToDimensionTable(matrixChain));
        System.out.println(Arrays.toString(getMatrixChainDimensions(matrixChain)));
    }

}