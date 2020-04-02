package ru.alexeylisyutenko.cormen.chapter15.lcs;

import lombok.Value;

import java.util.Objects;

/**
 * Cormen. 15.4 Longest common subsequence.
 */
public final class LongestCommonSubsequence {
    private LongestCommonSubsequence() {
    }

    /**
     * Computes the lengths of all subproblems for two sequences.
     *
     * @param sequence1 first sequence
     * @param sequence2 second sequence
     * @return lengths of subproblems which can be use to reconstruct the resulting longest common subsequence
     */
    public static LcsLengths lcsLength(String sequence1, String sequence2) {
        Objects.requireNonNull(sequence1, "sequence1 cannot be null");
        Objects.requireNonNull(sequence2, "sequence2 cannot be null");

        int m = sequence1.length();
        int n = sequence2.length();

        int[][] lengths = new int[m + 1][n + 1];
        byte[][] chosenSolutions = new byte[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            lengths[i][0] = 0;
        }
        for (int j = 0; j <= n; j++) {
            lengths[0][j] = 0;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sequence1.charAt(i - 1) == sequence2.charAt(j - 1)) {
                    lengths[i][j] = lengths[i - 1][j - 1] + 1;
                    chosenSolutions[i][j] = 1; // ↖
                } else if (lengths[i - 1][j] >= lengths[i][j - 1]) {
                    lengths[i][j] = lengths[i - 1][j];
                    chosenSolutions[i][j] = 2; // ⬆
                } else {
                    lengths[i][j] = lengths[i][j - 1];
                    chosenSolutions[i][j] = 3; // ⬅
                }
            }
        }

        return new LcsLengths(lengths, chosenSolutions);
    }

    /**
     *
     * @param lcsLengths
     * @param sequence1
     * @param i
     * @param j
     * @return
     */
    public static String constructLcs(LongestCommonSubsequence.LcsLengths lcsLengths, String sequence1, int i, int j) {
        StringBuilder stringBuilder = new StringBuilder();
        constructLcsAux(lcsLengths.getChosenSolutions(), sequence1, i, j, stringBuilder);
        return stringBuilder.toString();
    }

    private static void constructLcsAux(byte[][] chosenSolutions, String sequence1, int i, int j, StringBuilder stringBuilder) {
        if (i == 0 || j == 0) {
            return;
        }
        if (chosenSolutions[i][j] == 1) {
            constructLcsAux(chosenSolutions, sequence1, i - 1, j - 1, stringBuilder);
            stringBuilder.append(sequence1.charAt(i - 1));
        } else if (chosenSolutions[i][j] == 2) {
            constructLcsAux(chosenSolutions, sequence1, i - 1, j, stringBuilder);
        } else {
            constructLcsAux(chosenSolutions, sequence1, i, j - 1, stringBuilder);
        }
    }

    /**
     *
     * @param sequence1
     * @param sequence2
     * @return
     */
    public static String findLcs(String sequence1, String sequence2) {
        LcsLengths lcsLengths = lcsLength(sequence1, sequence2);
        return constructLcs(lcsLengths, sequence1, sequence1.length(), sequence2.length());
    }

    /**
     * Lengths of subproblems.
     */
    @Value
    public static class LcsLengths {
        /**
         * Lengths of subproblems.
         */
        int[][] lengths;

        /**
         * This is a helper field used to construct an optimal solution.
         */
        byte[][] chosenSolutions;
    }
}
