package ru.alexeylisyutenko.cormen.chapter15.lcs;

import com.jakewharton.fliptables.FlipTable;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static ru.alexeylisyutenko.helper.Helpers.print2dArrayAsTable;

// TODO: Fix all!

/**
 * Exercise 15.4-5.
 */
public final class LongestMonotonicallyIncreasingSubsequence {
    private LongestMonotonicallyIncreasingSubsequence() {
    }

    public static void printSubset(int[] sequence, int index, String subset) {
        if (index == sequence.length) {
            System.out.println("{" + subset + "}");
        } else {
            // Current element is in subset.
            printSubset(sequence, index + 1, subset.isEmpty() ? subset + sequence[index] : subset + ", " + sequence[index]);

            // Current element is not in a subset.
            printSubset(sequence, index + 1, subset);
        }
    }

    public static void printSubsetWithLowerBound(int[] sequence, int index, String subset, int lowerBound) {
        if (index == sequence.length) {
            System.out.println("{" + subset + "}");
        } else {
            // Current element is in subset.
            if (sequence[index] >= lowerBound) {
                printSubsetWithLowerBound(sequence, index + 1, subset.isEmpty() ? subset + sequence[index] : subset + ", " + sequence[index], sequence[index]);
            }

            // Current element is not in a subset.
            printSubsetWithLowerBound(sequence, index + 1, subset, lowerBound);
        }
    }

    /**
     * This is exponential time algorithm for finding the length of the longest monotonically increasing subsequence
     *
     * @param sequence original sequence of numbers
     * @return the length of the longest monotonically increasing subsequence
     */
    public static int lengthOnlyRecursive(int[] sequence) {
        Objects.requireNonNull(sequence, "sequence cannot be null");
        return lengthOnlyAux(sequence, 0, Integer.MIN_VALUE);
    }

    public static int lengthOnlyAux(int[] sequence, int index, int lowerBound) {
        if (index == sequence.length) {
            return 0;
        }

        int l1 = Integer.MIN_VALUE;
        if (sequence[index] >= lowerBound) {
            l1 = lengthOnlyAux(sequence, index + 1, sequence[index]) + 1;
        }
        int l2 = lengthOnlyAux(sequence, index + 1, lowerBound);

        return Math.max(l1, l2);
    }

    public static int lengthOnlyDynamic(int[] sequence) {
        Objects.requireNonNull(sequence, "sequence cannot be null");

        int size = sequence.length;
        if (size == 0) {
            return 0;
        }

        // Add a special value to the front of the sequence.
        int[] modifiedSequence = new int[size + 1];
        modifiedSequence[0] = Integer.MIN_VALUE;
        System.arraycopy(sequence, 0, modifiedSequence, 1, size);

        //
        int[][] lengths = new int[size + 1][size];

        // Initialize the last raw.
        for (int j = 0; j < size; j++) {
            lengths[size][j] = modifiedSequence[size] < modifiedSequence[j] ? 0 : 1;
        }

        print2dArrayAsTable(lengths);

        for (int i = size - 1; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int l1 = Integer.MIN_VALUE;
                if (modifiedSequence[i] >= modifiedSequence[j]) {
                    l1 = lengths[i + 1][i] + 1;
                }
                int l2 = lengths[i + 1][j];
                lengths[i][j] = Math.max(l1, l2);
            }
        }

        print2dArrayAsTable(lengths);


        return lengths[1][0];
    }

}
