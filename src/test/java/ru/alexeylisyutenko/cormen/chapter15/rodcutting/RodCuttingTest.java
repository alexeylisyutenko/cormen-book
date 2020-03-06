package ru.alexeylisyutenko.cormen.chapter15.rodcutting;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.alexeylisyutenko.cormen.chapter15.rodcutting.RodCutting.splitToString;

class RodCuttingTest {

    public static final int[] DEFAULT_PRICES = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    @Test
    void demo() {
        int size = 5;
        System.out.println(RodCutting.cutRod(DEFAULT_PRICES, size));
        System.out.println(RodCutting.cutRoadWithCost(DEFAULT_PRICES, 3, size));
    }

    @Test
    void demo2() {
        int size = 5;
        int cost = 2;

        Pair<Integer, int[]> revenueAndSplit = RodCutting.extendedBottomUpCutRod(DEFAULT_PRICES, size);
        System.out.println("Without cost");
        System.out.println("Revenue: " + revenueAndSplit.getLeft());
        System.out.println(splitToString(revenueAndSplit.getRight(), size));
        System.out.println();

        Pair<Integer, int[]> revenueAndSplitWithCost = RodCutting.extendedBottomUpCutRodWithCost(DEFAULT_PRICES, cost, size);
        System.out.println("With cost: " + cost);
        System.out.println("Revenue: " + revenueAndSplitWithCost.getLeft());
        System.out.println(splitToString(revenueAndSplitWithCost.getRight(), size));
        System.out.println();
    }

    @Test
    void cutRod() {
        assertEquals(1, RodCutting.cutRod(DEFAULT_PRICES, 1));
        assertEquals(5, RodCutting.cutRod(DEFAULT_PRICES, 2));
        assertEquals(8, RodCutting.cutRod(DEFAULT_PRICES, 3));
        assertEquals(10, RodCutting.cutRod(DEFAULT_PRICES, 4));
        assertEquals(13, RodCutting.cutRod(DEFAULT_PRICES, 5));
        assertEquals(17, RodCutting.cutRod(DEFAULT_PRICES, 6));
        assertEquals(18, RodCutting.cutRod(DEFAULT_PRICES, 7));
        assertEquals(22, RodCutting.cutRod(DEFAULT_PRICES, 8));
        assertEquals(25, RodCutting.cutRod(DEFAULT_PRICES, 9));
        assertEquals(30, RodCutting.cutRod(DEFAULT_PRICES, 10));
    }

    @Test
    void memoizedCutRod() {
        assertEquals(1, RodCutting.memoizedCutRod(DEFAULT_PRICES, 1));
        assertEquals(5, RodCutting.memoizedCutRod(DEFAULT_PRICES, 2));
        assertEquals(8, RodCutting.memoizedCutRod(DEFAULT_PRICES, 3));
        assertEquals(10, RodCutting.memoizedCutRod(DEFAULT_PRICES, 4));
        assertEquals(13, RodCutting.memoizedCutRod(DEFAULT_PRICES, 5));
        assertEquals(17, RodCutting.memoizedCutRod(DEFAULT_PRICES, 6));
        assertEquals(18, RodCutting.memoizedCutRod(DEFAULT_PRICES, 7));
        assertEquals(22, RodCutting.memoizedCutRod(DEFAULT_PRICES, 8));
        assertEquals(25, RodCutting.memoizedCutRod(DEFAULT_PRICES, 9));
        assertEquals(30, RodCutting.memoizedCutRod(DEFAULT_PRICES, 10));
    }

    @Test
    void bottomUpCutRod() {
        assertEquals(1, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 1));
        assertEquals(5, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 2));
        assertEquals(8, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 3));
        assertEquals(10, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 4));
        assertEquals(13, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 5));
        assertEquals(17, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 6));
        assertEquals(18, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 7));
        assertEquals(22, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 8));
        assertEquals(25, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 9));
        assertEquals(30, RodCutting.bottomUpCutRod(DEFAULT_PRICES, 10));
    }

    @Test
    void cutRoadWithCost() {
        assertEquals(1, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 1));
        assertEquals(5, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 2));
        assertEquals(8, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 3));
        assertEquals(10, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 4));
        assertEquals(13, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 5));
        assertEquals(17, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 6));
        assertEquals(18, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 7));
        assertEquals(22, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 8));
        assertEquals(25, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 9));
        assertEquals(30, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 0, 10));

        assertEquals(11, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 2, 5));

        assertEquals(9, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 10, 4));
        assertEquals(10, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 10, 5));
        assertEquals(17, RodCutting.cutRoadWithCost(DEFAULT_PRICES, 10, 7));
    }

    @Test
    void extendedBottomUpCutRodWithCost() {
        Pair<Integer, int[]> revenueAndSplit;

        revenueAndSplit = RodCutting.extendedBottomUpCutRodWithCost(DEFAULT_PRICES, 0, 1);
        assertEquals(Integer.valueOf(1), revenueAndSplit.getLeft());
        assertArrayEquals(new int[]{1}, revenueAndSplit.getRight());

        revenueAndSplit = RodCutting.extendedBottomUpCutRodWithCost(DEFAULT_PRICES, 0, 5);
        assertEquals(Integer.valueOf(13), revenueAndSplit.getLeft());
        assertArrayEquals(new int[]{1, 2, 3, 2, 2}, revenueAndSplit.getRight());

        revenueAndSplit = RodCutting.extendedBottomUpCutRodWithCost(DEFAULT_PRICES, 2, 5);
        assertEquals(Integer.valueOf(11), revenueAndSplit.getLeft());
        assertArrayEquals(new int[]{1, 2, 3, 4, 2}, revenueAndSplit.getRight());

        revenueAndSplit = RodCutting.extendedBottomUpCutRodWithCost(DEFAULT_PRICES, 0, 9);
        assertEquals(Integer.valueOf(25), revenueAndSplit.getLeft());
        assertArrayEquals(new int[]{1, 2, 3, 2, 2, 6, 1, 2, 3}, revenueAndSplit.getRight());
    }

    @Test
    void extendedMemoizedCutRod() {
        Pair<Integer, int[]> revenueAndSplit;

        revenueAndSplit = RodCutting.extendedMemoizedCutRod(DEFAULT_PRICES, 1);
        assertEquals(Integer.valueOf(1), revenueAndSplit.getLeft());
        assertArrayEquals(new int[]{1}, revenueAndSplit.getRight());

        revenueAndSplit = RodCutting.extendedMemoizedCutRod(DEFAULT_PRICES, 5);
        assertEquals(Integer.valueOf(13), revenueAndSplit.getLeft());
        assertArrayEquals(new int[]{1, 2, 3, 2, 2}, revenueAndSplit.getRight());

        revenueAndSplit = RodCutting.extendedMemoizedCutRod(DEFAULT_PRICES, 9);
        assertEquals(Integer.valueOf(25), revenueAndSplit.getLeft());
        assertArrayEquals(new int[]{1, 2, 3, 2, 2, 6, 1, 2, 3}, revenueAndSplit.getRight());
    }


}