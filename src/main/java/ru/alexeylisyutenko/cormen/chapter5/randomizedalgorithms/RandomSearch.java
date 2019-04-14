package ru.alexeylisyutenko.cormen.chapter5.randomizedalgorithms;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;

public class RandomSearch {

    public static Pair<Integer, Long> search(int[] array, int element) {
        long totalIterations = 0;
        int visitedIndexesCount = 0;
        boolean[] visitedFlags = new boolean[array.length];
        while (visitedIndexesCount < array.length) {
            totalIterations++;
            int index = RandomUtils.nextInt(0, array.length);
            if (array[index] == element) {
                return Pair.of(index, totalIterations);
            } else {
                if (!visitedFlags[index]) {
                    visitedFlags[index] = true;
                    visitedIndexesCount++;
                }
            }
        }
        return Pair.of(-1, totalIterations);
    }

}
