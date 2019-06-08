package ru.alexeylisyutenko.cormen.chapter9;

import org.apache.commons.lang3.tuple.Pair;

public class MinMax {

    public static Pair<Integer, Integer> findMinMax(int[] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array must no be empty");
        }

        int min;
        int max;
        int startIndex;
        if (array.length % 2 == 0) {
            if (array[0] < array[1]) {
                min = array[0];
                max = array[1];
            } else {
                min = array[1];
                max = array[0];
            }
            startIndex = 2;
        } else {
            min = array[0];
            max = array[0];
            startIndex = 1;
        }

        for (int i = startIndex; i < array.length; i += 2) {
            if (array[i] < array[i + 1]) {
                if (min > array[i]) {
                    min = array[i];
                }
                if (max < array[i + 1]) {
                    max = array[i + 1];
                }
            } else {
                if (min > array[i + 1]) {
                    min = array[i + 1];
                }
                if (max < array[i]) {
                    max = array[i];
                }
            }
        }

        return Pair.of(min, max);
    }
}
