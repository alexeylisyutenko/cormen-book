package ru.alexeylisyutenko.cormen.chapter8.columnsort;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public final class DefaultMesh implements Mesh {

    private final int[] data;
    private final int r;
    private final int s;

    private MeshState state;

    public DefaultMesh(int[] data, int r, int s) {
        validateArguments(data, r, s);
        this.data = data;
        this.r = r;
        this.s = s;
        this.state = MeshState.NORMAL;
    }

    public DefaultMesh(int r, int s) {
        this(new int[r * s], r, s);
    }

    private void validateArguments(int[] data, int r, int s) {
        if (r < 0) {
            throw new IllegalArgumentException("r must be positive");
        }
        if (s < 0) {
            throw new IllegalArgumentException("s must be positive");
        }
        if (r % 2 != 0) {
            throw new IllegalArgumentException("r must be even");
        }
        if (r % s != 0) {
            throw new IllegalArgumentException("s must be a divisor of r");
        }
        if (r < 2 * s * s) {
            throw new IllegalArgumentException("r must be greater or equal to 2*(s^2)");
        }
        if (data.length != r * s) {
            throw new IllegalArgumentException("data length must be equal to r*s");
        }
    }

    @Override
    public MeshState getState() {
        return state;
    }

    @Override
    public void setState(MeshState state) {
        this.state = state;
    }

    @Override
    public int getCellValue(int row, int column) {
        switch (state) {
            case NORMAL:
                return getNormalCellValue(row, column);
            case TRANSPOSED:
                return getTransposedCellValue(row, column);
            case SHIFTED:
                return getShiftedCellValue(row, column);
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
    }

    @Override
    public void setCellValue(int row, int column, int value) {
        switch (state) {
            case NORMAL:
                setNormalCellValue(row, column, value);
                break;
            case TRANSPOSED:
                setTransposedCellValue(row, column, value);
                break;
            case SHIFTED:
                setShiftedCellValue(row, column, value);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }
    }

    private int getNormalCellValue(int row, int column) {
        int index = calculateNormalIndex(row, column);
        return data[index];
    }

    private void setNormalCellValue(int row, int column, int value) {
        int index = calculateNormalIndex(row, column);
        data[index] = value;
    }

    private int getTransposedCellValue(int row, int column) {
        int index = calculateTransposedIndex(row, column);
        return data[index];
    }

    private void setTransposedCellValue(int row, int column, int value) {
        int index = calculateTransposedIndex(row, column);
        data[index] = value;
    }

    private int getShiftedCellValue(int row, int column) {
        int index = calculateShiftedIndex(row, column);
        if (index < 0) {
            return Integer.MIN_VALUE;
        }
        if (index >= data.length) {
            return Integer.MAX_VALUE;
        }
        return data[index];
    }

    private void setShiftedCellValue(int row, int column, int value) {
        int index = calculateShiftedIndex(row, column);
        if (index >= 0 && index < data.length) {
            data[index] = value;
        }
    }

    private int calculateNormalIndex(int row, int column) {
        validateNormalIndex(row, column);
        return column * r + row;
    }

    private void validateNormalIndex(int row, int column) {
        if (row < 0 || row >= r) {
            throw new IllegalArgumentException(String.format("Incorrect row index: %d", row));
        }
        if (column < 0 || column >= s) {
            throw new IllegalArgumentException(String.format("Incorrect column index: %d", column));
        }
    }

    private int calculateTransposedIndex(int row, int column) {
        validateTransposedIndex(row, column);
        return row * s + column;
    }

    private void validateTransposedIndex(int row, int column) {
        validateNormalIndex(row, column);
    }

    private int calculateShiftedIndex(int row, int column) {
        validateShiftedIndex(row, column);
        return (column - 1) * r + r / 2 + row;
    }

    private void validateShiftedIndex(int row, int column) {
        if (row < 0 || row >= r) {
            throw new IllegalArgumentException(String.format("Incorrect row index: %d", row));
        }
        if (column < 0 || column >= s + 1) {
            throw new IllegalArgumentException(String.format("Incorrect column index: %d", column));
        }
    }

    private int getDecimalDigitsNumber(int value) {
        if (value == 0) {
            return 1;
        }
        return (int) Math.log10(value) + 1;
    }

    private int findMaxDecimalDigitsNumber() {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < data.length; i++) {
            int currentDigitsNumber = getDecimalDigitsNumber(data[i]);
            if (max < currentDigitsNumber) {
                max = currentDigitsNumber;
            }
        }
        return max;
    }

    public void printAsMatrix() {
        int maxDecimalDigitsNumber = findMaxDecimalDigitsNumber();
        int columns = state == MeshState.SHIFTED ? s + 1 : s;
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < columns; j++) {
                int value = getCellValue(i, j);
                String centredValue;
                if (value == Integer.MIN_VALUE || value == Integer.MAX_VALUE) {
                    centredValue = StringUtils.center("", maxDecimalDigitsNumber + 4);
                } else {
                    centredValue = StringUtils.center(value + "", maxDecimalDigitsNumber + 4);
                }
                System.out.print(centredValue);
            }
            System.out.println();
        }
    }

    @Override
    public int[] getData() {
        return data;
    }

    @Override
    public int getRows() {
        return r;
    }

    @Override
    public int getColumns() {
        return state == MeshState.SHIFTED ? s + 1 : s;
    }

    @Override
    public String toString() {
        return "DefaultMesh{" +
                "data=" + Arrays.toString(data) +
                ", r=" + r +
                ", s=" + s +
                '}';
    }

}
