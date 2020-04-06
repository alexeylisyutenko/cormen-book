package ru.alexeylisyutenko.cormen.chapter15.lcs;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LongestCommonSubsequenceTest {

    @Test
    void findLcsDemo() {
        System.out.println(LongestCommonSubsequence.findLcs("ABCBDAB", "BDCABA"));
        System.out.println(LongestCommonSubsequence.findLcs("10010101", "010110110"));
    }

    @Test
    void lcsLength() {
        String sequence1 = "ABCBDAB";
        String sequence2 = "BDCABA";

        LongestCommonSubsequence.LcsLengthsAndSolutions lcsLengthsAndSolutions = LongestCommonSubsequence.lcsLength(sequence1, sequence2);

        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0}, lcsLengthsAndSolutions.getLengths()[0]);
        assertArrayEquals(new int[]{0, 0, 0, 0, 1, 1, 1}, lcsLengthsAndSolutions.getLengths()[1]);
        assertArrayEquals(new int[]{0, 1, 1, 1, 1, 2, 2}, lcsLengthsAndSolutions.getLengths()[2]);
        assertArrayEquals(new int[]{0, 1, 1, 2, 2, 2, 2}, lcsLengthsAndSolutions.getLengths()[3]);
        assertArrayEquals(new int[]{0, 1, 1, 2, 2, 3, 3}, lcsLengthsAndSolutions.getLengths()[4]);
        assertArrayEquals(new int[]{0, 1, 2, 2, 2, 3, 3}, lcsLengthsAndSolutions.getLengths()[5]);
        assertArrayEquals(new int[]{0, 1, 2, 2, 3, 3, 4}, lcsLengthsAndSolutions.getLengths()[6]);
        assertArrayEquals(new int[]{0, 1, 2, 2, 3, 4, 4}, lcsLengthsAndSolutions.getLengths()[7]);

        assertArrayEquals(new int[]{0, 0, 0, 0, 0, 0, 0}, lcsLengthsAndSolutions.getChosenSolutions()[0]);
        assertArrayEquals(new int[]{0, 2, 2, 2, 1, 3, 1}, lcsLengthsAndSolutions.getChosenSolutions()[1]);
        assertArrayEquals(new int[]{0, 1, 3, 3, 2, 1, 3}, lcsLengthsAndSolutions.getChosenSolutions()[2]);
        assertArrayEquals(new int[]{0, 2, 2, 1, 3, 2, 2}, lcsLengthsAndSolutions.getChosenSolutions()[3]);
        assertArrayEquals(new int[]{0, 1, 2, 2, 2, 1, 3}, lcsLengthsAndSolutions.getChosenSolutions()[4]);
        assertArrayEquals(new int[]{0, 2, 1, 2, 2, 2, 2}, lcsLengthsAndSolutions.getChosenSolutions()[5]);
        assertArrayEquals(new int[]{0, 2, 2, 2, 1, 2, 1}, lcsLengthsAndSolutions.getChosenSolutions()[6]);
        assertArrayEquals(new int[]{0, 1, 2, 2, 2, 1, 2}, lcsLengthsAndSolutions.getChosenSolutions()[7]);
    }

    @Test
    void lcsLengthsMemoized () {
        String sequence1 = "ABCBDAB";
        String sequence2 = "BDCABA";

        LongestCommonSubsequence.LcsLengthsAndSolutions lcsLengthsAndSolutions = LongestCommonSubsequence.lcsLengthsMemoized(sequence1, sequence2);
        System.out.println(lcsLengthsAndSolutions.toLengthsTable());
        System.out.println(lcsLengthsAndSolutions.toChosenSolutionsTable());

        String lcs = LongestCommonSubsequence.constructLcs(lcsLengthsAndSolutions, sequence1, sequence1.length(), sequence2.length());
        assertEquals("BCBA", lcs);
    }

    @Test
    void constructLcs() {
        String sequence1 = "ABCBDAB";
        String sequence2 = "BDCABA";
        LongestCommonSubsequence.LcsLengthsAndSolutions lcsLengthsAndSolutions = LongestCommonSubsequence.lcsLength(sequence1, sequence2);

        String lcs = LongestCommonSubsequence.constructLcs(lcsLengthsAndSolutions, sequence1, sequence1.length(), sequence2.length());
        assertEquals("BCBA", lcs);
    }

    @Test
    void constructLcsShort() {
        String sequence1 = "ABCBDAB";
        String sequence2 = "BDCABA";
        int[][] lengths = LongestCommonSubsequence.lcsLength(sequence1, sequence2).getLengths();

        String lcs = LongestCommonSubsequence.constructLcsShort(lengths, sequence1, sequence2);
        assertEquals("BCBA", lcs);
    }

    @Test
    void findLcs() {
        assertEquals("BCBA", LongestCommonSubsequence.findLcs("ABCBDAB", "BDCABA"));
        assertEquals("", LongestCommonSubsequence.findLcs("ABCBDAB", ""));
        assertEquals("", LongestCommonSubsequence.findLcs("", "BDCABA"));
        assertEquals("", LongestCommonSubsequence.findLcs("", ""));
    }

    @Test
    void onlyLcsLengthWithOptimizedSpaceDemo() {
        System.out.println(LongestCommonSubsequence.onlyLcsLengthWithOptimizedSpace("ABCBDAB", "BDCABA"));
        System.out.println(LongestCommonSubsequence.onlyLcsLengthWithOptimizedSpace("BDCABA", "ABCBDAB"));
    }

    @Test
    void onlyLcsLengthWithOptimizedSpace() {
        assertEquals(4, LongestCommonSubsequence.onlyLcsLengthWithOptimizedSpace("ABCBDAB", "BDCABA"));
        assertEquals(4, LongestCommonSubsequence.onlyLcsLengthWithOptimizedSpace("BDCABA", "ABCBDAB"));
        assertEquals(0, LongestCommonSubsequence.onlyLcsLengthWithOptimizedSpace("ABCBDAB", ""));
        assertEquals(0, LongestCommonSubsequence.onlyLcsLengthWithOptimizedSpace("", "BDCABA"));
        assertEquals(0, LongestCommonSubsequence.onlyLcsLengthWithOptimizedSpace("", ""));
    }

    @RepeatedTest(1000)
    void onlyLcsLengthWithOptimizedSpaceRandomized() {
        String sequence1 = RandomStringUtils.randomAlphabetic(0, 100);
        String sequence2 = RandomStringUtils.randomAlphabetic(0, 100);
        assertEquals(LongestCommonSubsequence.findLcs(sequence1, sequence2).length(), LongestCommonSubsequence.onlyLcsLengthWithOptimizedSpace(sequence1, sequence2));
    }
}