package ru.alexeylisyutenko.cormen.chapter14;

import java.util.Objects;

/**
 * Cormen. Exercise 14.1-7.
 */
public final class ArrayInversionsCountAlgorithm {
    /**
     * Counts number of inversions in an array.
     *
     * @param array source array
     * @return number of inversions in the array
     */
    public static int countArrayInversions(int[] array) {
        Objects.requireNonNull(array, "array argument cannot be null");

        OrderStatisticTree<Integer> orderStatisticTree = new DefaultOrderStatisticTree<>();
        int inversions = 0;
        for (int element : array) {
            orderStatisticTree.insert(element);
            inversions += orderStatisticTree.countGreaterKeys(element);
        }
        return inversions;
    }
}
