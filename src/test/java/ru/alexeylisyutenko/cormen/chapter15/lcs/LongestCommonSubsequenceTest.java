package ru.alexeylisyutenko.cormen.chapter15.lcs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongestCommonSubsequenceTest {

    @Test
    void findLcsDemo() {
        System.out.println(LongestCommonSubsequence.findLcs("ABCBDAB", "BDCABA"));
    }

    @Test
    void lcsLength() {
        String sequence1 = "ABCBDAB";
        String sequence2 = "BDCABA";

        LongestCommonSubsequence.LcsLengths lcsLengths = LongestCommonSubsequence.lcsLength(sequence1, sequence2);

        assertArrayEquals(new int[] {0, 0, 0, 0, 0, 0, 0}, lcsLengths.getLengths()[0]);
        assertArrayEquals(new int[] {0, 0, 0, 0, 1, 1, 1}, lcsLengths.getLengths()[1]);
        assertArrayEquals(new int[] {0, 1, 1, 1, 1, 2, 2}, lcsLengths.getLengths()[2]);
        assertArrayEquals(new int[] {0, 1, 1, 2, 2, 2, 2}, lcsLengths.getLengths()[3]);
        assertArrayEquals(new int[] {0, 1, 1, 2, 2, 3, 3}, lcsLengths.getLengths()[4]);
        assertArrayEquals(new int[] {0, 1, 2, 2, 2, 3, 3}, lcsLengths.getLengths()[5]);
        assertArrayEquals(new int[] {0, 1, 2, 2, 3, 3, 4}, lcsLengths.getLengths()[6]);
        assertArrayEquals(new int[] {0, 1, 2, 2, 3, 4, 4}, lcsLengths.getLengths()[7]);

        assertArrayEquals(new byte[] {0, 0, 0, 0, 0, 0, 0}, lcsLengths.getChosenSolutions()[0]);
        assertArrayEquals(new byte[] {0, 2, 2, 2, 1, 3, 1}, lcsLengths.getChosenSolutions()[1]);
        assertArrayEquals(new byte[] {0, 1, 3, 3, 2, 1, 3}, lcsLengths.getChosenSolutions()[2]);
        assertArrayEquals(new byte[] {0, 2, 2, 1, 3, 2, 2}, lcsLengths.getChosenSolutions()[3]);
        assertArrayEquals(new byte[] {0, 1, 2, 2, 2, 1, 3}, lcsLengths.getChosenSolutions()[4]);
        assertArrayEquals(new byte[] {0, 2, 1, 2, 2, 2, 2}, lcsLengths.getChosenSolutions()[5]);
        assertArrayEquals(new byte[] {0, 2, 2, 2, 1, 2, 1}, lcsLengths.getChosenSolutions()[6]);
        assertArrayEquals(new byte[] {0, 1, 2, 2, 2, 1, 2}, lcsLengths.getChosenSolutions()[7]);
    }

    @Test
    void constructLcs() {
        String sequence1 = "ABCBDAB";
        String sequence2 = "BDCABA";
        LongestCommonSubsequence.LcsLengths lcsLengths = LongestCommonSubsequence.lcsLength(sequence1, sequence2);

        String lcs = LongestCommonSubsequence.constructLcs(lcsLengths, sequence1, sequence1.length(), sequence2.length());
        assertEquals("BCBA", lcs);
    }

    @Test
    void findLcs() {
        fail();
    }

}