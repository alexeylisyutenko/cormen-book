package ru.alexeylisyutenko.cormen.chapter8;

@SuppressWarnings("ALL")
public final class RangeCounter {

    private final int k;
    private final int[] counts;

    public RangeCounter(int[] array, int k) {
        validateArguments(array, k);
        this.k = k;
        this.counts = buildCounts(array, k);
    }

    private int[] buildCounts(int[] array, int k) {
        int[] counts = new int[k + 1];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > k) {
                throw new IllegalArgumentException(String.format("Incorrect input array value array[%d] == %d", i, array[i]));
            }
            counts[array[i]]++;
        }
        for (int i = 1; i < counts.length; i++) {
            counts[i] += counts[i - 1];
        }
        return counts;
    }

    private void validateArguments(int[] array, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Incorrect k value: " + k);
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] < 0 || array[i] > k) {
                throw new IllegalArgumentException(String.format("Incorrect input array value array[%d] == %d", i, array[i]));
            }
        }
    }

    public int getCountInRange(int fromInclusive, int toInclusive) {
        if (fromInclusive < 0 || fromInclusive > k) {
            throw new IllegalArgumentException("Incorrect fromInclusive argument");
        }
        if (toInclusive < 0 || toInclusive > k) {
            throw new IllegalArgumentException("Incorrect toInclusive argument");
        }
        if (fromInclusive > toInclusive) {
            throw new IllegalArgumentException("fromInclusive must be less or equal to toInclusive");
        }
        return counts[toInclusive] - (fromInclusive - 1 < 0 ? 0 : counts[fromInclusive - 1]);
    }

}
