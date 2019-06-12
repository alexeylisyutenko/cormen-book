package ru.alexeylisyutenko.cormen.chapter9;

public class KQuantiles {

    public static int[] quantiles(int[] array, int k) {
        if (k < 1 || k > array.length) {
            throw new IllegalArgumentException("Incorrect k value");
        }
        int[] quantiles = new int[k - 1];
        findQuantiles(array, 0, array.length - 1, k, quantiles);
        return quantiles;
    }

    private static void findQuantiles(int[] array, int lo, int hi, int k, int[] quantiles) {
        if (k == 1) {
            return;
        }

        int i = k / 2;


        int indexToSelect =
        OrderStatisticSelection.select(array, )

    }


}
