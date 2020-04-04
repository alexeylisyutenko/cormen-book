package ru.alexeylisyutenko.cormen.chapter15.lcs;

import com.jakewharton.fliptables.FlipTable;
import lombok.Value;

import java.util.Arrays;
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
    public static LcsLengthsAndSolutions lcsLength(String sequence1, String sequence2) {
        Objects.requireNonNull(sequence1, "sequence1 cannot be null");
        Objects.requireNonNull(sequence2, "sequence2 cannot be null");

        int m = sequence1.length();
        int n = sequence2.length();

        int[][] lengths = new int[m + 1][n + 1];
        int[][] chosenSolutions = new int[m + 1][n + 1];

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

        return new LcsLengthsAndSolutions(lengths, chosenSolutions);
    }

    /**
     * Constructs the longest common subsequence by data from {@link LcsLengthsAndSolutions} object.
     *
     * @param lcsLengthsAndSolutions lengths of subproblems.
     * @param sequence1              first sequence
     * @param m                      number of elements in the first sequence
     * @param n                      number of elements in the second sequence
     * @return the longest common subsequence
     */
    public static String constructLcs(LcsLengthsAndSolutions lcsLengthsAndSolutions, String sequence1, int m, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        constructLcsAux(lcsLengthsAndSolutions.getChosenSolutions(), sequence1, m, n, stringBuilder);
        return stringBuilder.toString();
    }

    private static void constructLcsAux(int[][] chosenSolutions, String sequence1, int i, int j, StringBuilder stringBuilder) {
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
     * Exercise 15.4-2.
     */
    public static String constructLcsShort(int[][] lengths, String sequence1, String sequence2) {
        StringBuilder stringBuilder = new StringBuilder();
        constructLcsShortAux(lengths, sequence1, sequence2, sequence1.length(), sequence2.length(), stringBuilder);
        return stringBuilder.toString();
    }

    private static void constructLcsShortAux(int[][] lengths, String sequence1, String sequence2, int i, int j, StringBuilder sb) {
        if (i == 0 || j == 0) {
            return;
        }
        if (sequence1.charAt(i - 1) == sequence2.charAt(j - 1)) {
            constructLcsShortAux(lengths, sequence1, sequence2, i - 1, j - 1, sb);
            sb.append(sequence1.charAt(i - 1));
        } else if (lengths[i - 1][j] >= lengths[i][j - 1]) {
            constructLcsShortAux(lengths, sequence1, sequence2, i - 1, j, sb);
        } else {
            constructLcsShortAux(lengths, sequence1, sequence2, i, j - 1, sb);
        }
    }


    /**
     * Exercise 15.4-3.
     */
    public static LcsLengthsAndSolutions lcsLengthsMemoized(String sequence1, String sequence2) {
        Objects.requireNonNull(sequence1, "sequence1 cannot be null");
        Objects.requireNonNull(sequence2, "sequence2 cannot be null");

        int m = sequence1.length();
        int n = sequence2.length();

        int[][] lengths = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                lengths[i][j] = -1;
            }
        }
        int[][] chosenSolutions = new int[m + 1][n + 1];
        lcsLengthsMemoizedAux(sequence1, sequence2, m, n, lengths, chosenSolutions);
        return new LcsLengthsAndSolutions(lengths, chosenSolutions);
    }

    private static int lcsLengthsMemoizedAux(String sequence1, String sequence2, int i, int j, int[][] lengths, int[][] chosenSolutions) {
        if (lengths[i][j] >= 0) {
            return lengths[i][j];
        }

        int length;

        if (i == 0 || j == 0) {
            length = 0;
        } else if (sequence1.charAt(i - 1) == sequence2.charAt(j - 1)) {
            length = lcsLengthsMemoizedAux(sequence1, sequence2, i - 1, j - 1, lengths, chosenSolutions) + 1;
            chosenSolutions[i][j] = 1;
        } else {
            int l1 = lcsLengthsMemoizedAux(sequence1, sequence2, i - 1, j, lengths, chosenSolutions);
            int l2 = lcsLengthsMemoizedAux(sequence1, sequence2, i, j - 1, lengths, chosenSolutions);

            if (l1 >= l2) {
                length = l1;
                chosenSolutions[i][j] = 2;
            } else {
                length = l2;
                chosenSolutions[i][j] = 3;
            }
        }

        lengths[i][j] = length;
        return length;
    }

    // Print all LCS? How to do that? Can we use information in

    /**
     * Finds the longest common subsequence of two sequences.
     *
     * @param sequence1 first sequence
     * @param sequence2 second sequence
     * @return the longest common subsequence
     */
    public static String findLcs(String sequence1, String sequence2) {
        LcsLengthsAndSolutions lcsLengthsAndSolutions = lcsLength(sequence1, sequence2);
        return constructLcs(lcsLengthsAndSolutions, sequence1, sequence1.length(), sequence2.length());
    }

    /**
     * Lengths of subproblems along with a helper field used to construct an optimal solution.
     */
    @Value
    public static class LcsLengthsAndSolutions {
        /**
         * Lengths of subproblems.
         */
        int[][] lengths;

        /**
         * This is a helper field used to construct an optimal solution.
         */
        int[][] chosenSolutions;

        public String toLengthsTable() {
            return constructTable(lengths);
        }

        public String toChosenSolutionsTable() {
            return constructTable(chosenSolutions);
        }

        private String constructTable(int[][] source) {
            int rows = source.length;
            int columns = (rows == 0) ? 0 : source[0].length;
            String[] headers = constructEmptyHeader(columns);
            String[][] data = Arrays.stream(source)
                    .map(ints -> Arrays.stream(ints).mapToObj(String::valueOf).toArray(String[]::new))
                    .toArray(String[][]::new);
            return FlipTable.of(headers, data);
        }

        private String[] constructEmptyHeader(int columns) {
            String[] headers = new String[columns];
            for (int i = 0; i < columns; i++) {
                headers[i] = "";
            }
            return headers;
        }

    }
}
