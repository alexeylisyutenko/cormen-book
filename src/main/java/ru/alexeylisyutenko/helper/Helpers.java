package ru.alexeylisyutenko.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

public final class Helpers {
    public static int[] randomIntArray(int size) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.ints(size, -1000, 1000).toArray();
    }

    public static int[] randomPositiveIntArray(int size, int randomNumberBound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.ints(size, 1, randomNumberBound).toArray();
    }

    public static void exchange(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static void exchange(Object[] array, int firstIndex, int secondIndex) {
        Object temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
