package ru.alexeylisyutenko.cormen.chapter15.rodcutting;

import static org.apache.commons.lang3.math.NumberUtils.max;

public class DefaultRodCutting implements RodCutting {
    @Override
    public int cutRod(int[] prices, int size) {
        if (size == 0) {
            return 0;
        }
        int maxRevenue = Integer.MIN_VALUE;
        for (int i = 1; i <= size; i++) {
            maxRevenue = max(maxRevenue, prices[i - 1] + cutRod(prices, size - i));
        }
        return maxRevenue;
    }
}
