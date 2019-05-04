package ru.alexeylisyutenko.helper;

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
}
