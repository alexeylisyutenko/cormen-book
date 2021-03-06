package ru.alexeylisyutenko.cormen.chapter15.lcs;

import lombok.Value;

import java.util.*;

/**
 * Exercise 15.4-5.
 */
public final class LongestMonotonicallyIncreasingSubsequence {
    private LongestMonotonicallyIncreasingSubsequence() {
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

    /**
     * This method finds the length of the longestMonotonically increasing subsequence.
     *
     * @param sequence sequence of integers
     * @return the length of the longestMonotonically increasing subsequence
     */
    public static int lengthOnlyDynamic(int[] sequence) {
        Objects.requireNonNull(sequence, "sequence cannot be null");
        int[][] lengths = findLMISLengths(sequence).getLengths();
        return lengths[0][0];
    }

    /**
     * Returns the longest monotonically increasing subsequence. O(n^2).
     *
     * @param sequence sequence of integers
     * @return the longestMonotonically increasing subsequence
     */
    public static int[] findLongestMonotonicallyIncreasingSubsequence(int[] sequence) {
        Objects.requireNonNull(sequence, "sequence cannot be null");
        LMISLengthsAndDirections lengthsAndDirections = findLMISLengths(sequence);
        return constructOptimalSolution(sequence, lengthsAndDirections);
    }

    public static int[] constructOptimalSolution(int[] sequence, LMISLengthsAndDirections lmisLengthsAndDirections) {
        int optimalSolutionSize = lmisLengthsAndDirections.getLengths()[0][0];
        int[][] directions = lmisLengthsAndDirections.getDirections();

        int[] optimalSolution = new int[optimalSolutionSize];
        int optimalSolutionIndex = 0;

        int i = 0;
        int j = 0;
        while (i < sequence.length) {
            if (directions[i][j] == 1) {
                optimalSolution[optimalSolutionIndex++] = sequence[i];
                j = i + 1;
            }
            i++;
        }

        return optimalSolution;
    }

    public static LMISLengthsAndDirections findLMISLengths(int[] sequence) {
        int size = sequence.length;

        // Add a special value to the front of the sequence.
        int[] modifiedSequence = new int[size + 1];
        modifiedSequence[0] = Integer.MIN_VALUE;
        System.arraycopy(sequence, 0, modifiedSequence, 1, size);

        // Use bottom-up approach to find the solution.
        int[][] lengths = new int[size + 1][size + 1];
        int[][] directions = new int[size + 1][size + 1];
        for (int i = size; i >= 1; i--) {
            for (int j = i - 1; j >= 0; j--) {
                int l1 = Integer.MIN_VALUE;
                if (modifiedSequence[i] >= modifiedSequence[j]) {
                    l1 = lengths[i][i] + 1;
                }
                int l2 = lengths[i][j];

                if (l1 >= l2) {
                    lengths[i - 1][j] = l1;
                    directions[i - 1][j] = 1;
                } else {
                    lengths[i - 1][j] = l2;
                    directions[i - 1][j] = 2;
                }
            }
        }

        return new LMISLengthsAndDirections(lengths, directions);
    }

    /**
     * Returns the longest monotonically increasing subsequence. O(n log n).
     *
     * @param sequence sequence of integers
     * @return the longestMonotonically increasing subsequence
     */
    public static int[] findLMISFast(int[] sequence) {
        Objects.requireNonNull(sequence, "sequence cannot be null");
        int size = sequence.length;
        if (size == 0) {
            return new int[0];
        }

        int[] lastElements = new int[size];
        for (int i = 0; i < size; i++) {
            lastElements[i] = Integer.MAX_VALUE;
        }

        ArrayList<ArrayList<Integer>> candidates = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            candidates.add(new ArrayList<>(size));
        }

        int longestCandidateLength = 1;
        for (int i = 0; i < size; i++) {
            if (sequence[i] < lastElements[0]) {
                lastElements[0] = sequence[i];
                candidates.get(0).clear();
                candidates.get(0).add(sequence[i]);
            } else {
                int j = binSearch(lastElements, sequence[i]);
                lastElements[j] = sequence[i];
                candidates.get(j).clear();
                candidates.get(j).addAll(candidates.get(j - 1));
                candidates.get(j).add(sequence[i]);
                if (j + 1> longestCandidateLength) {
                    longestCandidateLength++;
                }
            }
        }

        ArrayList<Integer> lmis = candidates.get(longestCandidateLength - 1);
        return lmis.stream().mapToInt(value -> value).toArray();
    }

    private static int binSearch(int[] sequence, int key) {
        int searchResult = Arrays.binarySearch(sequence, key);
        if (searchResult < 0) {
            return -(searchResult + 1);
        } else {
            while (searchResult < sequence.length && sequence[searchResult] == key) {
                searchResult++;
            }
            return searchResult;
        }
    }

    @Value
    public static class LMISLengthsAndDirections {
        int[][] lengths;
        int[][] directions;
    }

}
