package ru.alexeylisyutenko.cormen.chapter15.rodcutting;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;

import static org.apache.commons.lang3.math.NumberUtils.max;

/**
 * Cormen. 15.1 Rod Cutting.
 */
public class RodCutting {

    /**
     * Recursive top-down implementation.
     *
     * @param prices price list
     * @param size   size of a rod
     * @return best possible revenue
     */
    public static int cutRod(int[] prices, int size) {
        if (size == 0) {
            return 0;
        }
        int maxRevenue = Integer.MIN_VALUE;
        for (int i = 1; i <= size; i++) {
            maxRevenue = max(maxRevenue, prices[i - 1] + cutRod(prices, size - i));
        }
        return maxRevenue;
    }

    /**
     * Memoized version of the algorithm.
     *
     * @param prices price list
     * @param size   size of a rod
     * @return best possible revenue
     */
    public static int memoizedCutRod(int[] prices, int size) {
        int[] revenueCache = new int[size + 1];
        Arrays.fill(revenueCache, Integer.MIN_VALUE);
        return memoizedCutRodAux(prices, size, revenueCache);
    }

    private static int memoizedCutRodAux(int[] prices, int size, int[] revenueCache) {
        if (revenueCache[size] >= 0) {
            return revenueCache[size];
        }
        int maxRevenue = Integer.MIN_VALUE;
        if (size == 0) {
            maxRevenue = 0;
        } else {
            for (int i = 1; i <= size; i++) {
                maxRevenue = max(maxRevenue, prices[i - 1] + memoizedCutRodAux(prices, size - i, revenueCache));
            }
        }
        revenueCache[size] = maxRevenue;
        return maxRevenue;
    }

    /**
     * Bottom-up version of the algorithm.
     *
     * @param prices price list
     * @param size   size of a rod
     * @return best possible revenue
     */
    public static int bottomUpCutRod(int[] prices, int size) {
        int[] revenues = new int[size + 1];
        revenues[0] = 0;
        for (int j = 1; j <= size; j++) {
            int maxRevenue = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                maxRevenue = max(maxRevenue, prices[i - 1] + revenues[j - i]);
            }
            revenues[j] = maxRevenue;
        }
        return revenues[size];
    }

    /**
     * Bottom-up version of the algorithm which returns the optimal split along with the best revenue.
     *
     * @param prices price list
     * @param size   size of a rod
     * @return best possible revenue and best split
     */
    public static Pair<Integer, int[]> extendedBottomUpCutRod(int[] prices, int size) {
        int[] revenues = new int[size + 1];
        int[] split = new int[size];
        revenues[0] = 0;
        for (int j = 1; j <= size; j++) {
            int maxRevenue = Integer.MIN_VALUE;
            for (int i = 1; i <= j; i++) {
                int revenue = prices[i - 1] + revenues[j - i];
                if (maxRevenue < revenue) {
                    maxRevenue = revenue;
                    split[j - 1] = i;
                }
            }
            revenues[j] = maxRevenue;
        }
        return Pair.of(revenues[size], split);
    }

    /**
     * Helper method that builds a string with a rod split representation inside.
     *
     * @param split split
     * @param size size of original rod
     * @return string with a rod split representation inside
     */
    public static String splitToString(int[] split, int size) {
        if (split.length != size) {
            throw new IllegalArgumentException("split.length must be equal to size");
        }

        StringBuilder sb = new StringBuilder();
        while (size > 0) {
            sb.append(split[size - 1]);
            size = size - split[size - 1];
            if (size > 0) {
                sb.append(" + ");
            }
        }
        return sb.toString();
    }

    /**
     * Recursive top-down implementation. Which also consider cost of a single cut.
     *
     * @param prices price list
     * @param cost   cost of a single cut
     * @param size   size of a rod
     * @return best possible revenue
     */
    public static int cutRoadWithCost(int[] prices, int cost, int size) {
        if (size == 0) {
            return 0;
        }
        int maxRevenue = Integer.MIN_VALUE;
        for (int i = 1; i <= size; i++) {
            int currentCost = i == size ? 0 : cost;
            int revenue = prices[i - 1] + cutRoadWithCost(prices, cost, size - i) - currentCost;
            if (maxRevenue < revenue) {
                maxRevenue = revenue;
            }
        }
        return maxRevenue;
    }

    public static Pair<Integer, int[]> extendedBottomUpCutRodWithCost(int[] prices, int size) {
        throw new IllegalStateException("Not implemented yet");
    }
}
