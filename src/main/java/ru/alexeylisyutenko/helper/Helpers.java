package ru.alexeylisyutenko.helper;

import com.jakewharton.fliptables.FlipTable;
import org.apache.commons.lang3.RandomUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public final class Helpers {

    public static double[] randomDoubleArray(int size) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.doubles(size).toArray();
    }

    public static double[] randomDoubleArray(int size, int places) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.doubles(size).map(value -> roundFloor(value, places)).toArray();
    }

    public static int[] randomIntArray(int size) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.ints(size, -1000, 1000).toArray();
    }

    public static int[] randomPositiveIntArray(int size, int randomNumberBound) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        return random.ints(size, 1, randomNumberBound).toArray();
    }

    public static int[] randomIntArrayOfFixedLength(int size, int digits) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int lowerBound = (int) Math.pow(10, digits - 1);
        int upperBound = (int) Math.pow(10, digits) - 1;
        return random.ints(size, lowerBound, upperBound).toArray();
    }

    public static int[] sequentialIntArray(int size) {
        return IntStream.rangeClosed(1, size).toArray();
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

    public static double roundHalfUp(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double roundFloor(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.FLOOR);
        return bd.doubleValue();
    }

    public static int floorToInt(double val) {
        if (Double.isInfinite(val)) {
            throw new IllegalArgumentException("val must be finite");
        }
        return (int) Math.floor(val);
    }

    public static int ceilToInt(double val) {
        if (Double.isInfinite(val)) {
            throw new IllegalArgumentException("val must be finite");
        }
        return (int) Math.ceil(val);
    }

    public static List<Integer> generateRandomDistinctIntegers(int count) {
        HashSet<Integer> integerHashSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomInt;
            do {
                randomInt = RandomUtils.nextBoolean() ? RandomUtils.nextInt() : -RandomUtils.nextInt();
            } while (integerHashSet.contains(randomInt));
            integerHashSet.add(randomInt);
        }
        return new ArrayList<>(integerHashSet);
    }

    public static List<Integer> generateRandomDistinctIntegers(int count, int startInclusive, int endExclusive) {
        HashSet<Integer> integerHashSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomInt;
            do {
                randomInt = RandomUtils.nextInt(startInclusive, endExclusive);
            } while (integerHashSet.contains(randomInt));
            integerHashSet.add(randomInt);
        }
        return new ArrayList<>(integerHashSet);
    }

    public static void print2dArrayAsTable(int[][] source) {
        int rows = source.length;
        int columns = (rows == 0) ? 0 : source[0].length;

        String[] headers = new String[columns];
        for (int i = 0; i < columns; i++) {
            headers[i] = "-";
        }

        String[][] data = Arrays.stream(source)
                .map(ints -> Arrays.stream(ints).mapToObj(String::valueOf).toArray(String[]::new))
                .toArray(String[][]::new);
        System.out.println(FlipTable.of(headers, data));
    }

}
