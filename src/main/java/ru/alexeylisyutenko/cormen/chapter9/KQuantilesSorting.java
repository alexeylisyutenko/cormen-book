package ru.alexeylisyutenko.cormen.chapter9;

import java.util.Arrays;

import static ru.alexeylisyutenko.helper.Helpers.ceilToInt;

public class KQuantilesSorting {

    public static int[] kQuantiles(int[] array, int k) {
        Arrays.sort(array);
        int[] quantiles = new int[k - 1];
        int n = array.length;
        for (int i = 1; i <= k - 1; i++) {
            int index = ceilToInt(i * (double) n / k);
            quantiles[i - 1] = array[index - 1];
        }
        return quantiles;
    }

}
