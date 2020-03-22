package ru.alexeylisyutenko.cormen.chapter15.matrixchainmultiplication;


import com.jakewharton.fliptables.FlipTable;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Helper methods for working with real matrices and matrix chains.
 */
public final class Matrices {
    private Matrices() {
    }

    /**
     * Create a matrix chain for successive multiplications.
     *
     * @param chainSize chain size
     * @return real matrix chain
     */
    public static List<RealMatrix> createRealMatrixChain(int chainSize) {
        return createRealMatrixChain(chainSize, 100, 100);
    }

    /**
     * Create a matrix chain for successive multiplications.
     *
     * @param chainSize chain size
     * @param maxRows maximum number of rows in each matrix
     * @param maxColumns maximum number of columns in each matrix
     * @return real matrix chain
     */
    public static List<RealMatrix> createRealMatrixChain(int chainSize, int maxRows, int maxColumns) {
        if (chainSize < 0) {
            throw new IllegalArgumentException("chainSize must be greater or equal than 0");
        }

        ArrayList<RealMatrix> matrices = new ArrayList<>(chainSize);
        for (int i = 0; i < chainSize; i++) {
            int rows;
            if (matrices.size() == 0) {
                rows = RandomUtils.nextInt(1, maxRows);
            } else {
                rows = matrices.get(matrices.size() - 1).getColumnDimension();
            }
            int columns = RandomUtils.nextInt(1, maxColumns);

            RealMatrix matrix = createRandomDataMatrix(rows, columns);
            matrices.add(matrix);
        }
        return matrices;
    }

    /**
     * Create real matrix of the specified size with random data.
     *
     * @param rows number of rows of the matrix
     * @param columns number of columns of the matrix
     * @return  RealMatrix with specified dimensions and random data
     */
    public static RealMatrix createRandomDataMatrix(int rows, int columns) {
        RealMatrix matrix = MatrixUtils.createRealMatrix(rows, columns);
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                matrix.setEntry(row, column, RandomUtils.nextInt(0, 11) - 5);
            }
        }
        return matrix;
    }

    /**
     * Make a string containing a textual representation of the table with dimensions of the matrixChain in the chain.
     *
     * @param matrixChain matrix chain
     * @return textual representation of the table with dimensions of the matrixChain in the chain
     */
    public static String matrixChainToDimensionTable(List<RealMatrix> matrixChain) {
        String[] header = IntStream.range(1, matrixChain.size() + 1)
                .mapToObj(value -> "A" + value)
                .toArray(String[]::new);
        String[] data = matrixChain.stream()
                .map(matrix -> matrix.getRowDimension() + "x" + matrix.getColumnDimension())
                .toArray(String[]::new);
        return FlipTable.of(header, new String[][]{data});
    }

    /**
     * Returns a dimension array which contains the dimensions of the matrices in the matrix chain.
     *
     * @param matrixChain matrix chain
     * @return dimension array
     */
    public static int[] getMatrixChainDimensions(List<RealMatrix> matrixChain) {
        Objects.requireNonNull(matrixChain,"matrixChain cannot be null");
        if (matrixChain.isEmpty()) {
            throw new IllegalArgumentException("matrixChain must not be empty");
        }

        int[] dimensions = new int[matrixChain.size() + 1];
        dimensions[0] = matrixChain.get(0).getRowDimension();
        for (int i = 1; i <= matrixChain.size() ; i++) {
            dimensions[i] = matrixChain.get(i - 1).getColumnDimension();
        }
        return dimensions;
    }

}
