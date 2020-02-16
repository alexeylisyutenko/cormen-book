package ru.alexeylisyutenko.cormen.chapter14.intervaltree;

public final class Interval implements Comparable<Interval> {
    private final int low;
    private final int high;

    public Interval(int low, int high) {
        if (low > high) {
            throw new IllegalArgumentException("low must be less or equal than high");
        }
        this.low = low;
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public int getHigh() {
        return high;
    }

    @Override
    public String toString() {
        return "[" + low + ", " + high + "]";
    }

    @Override
    public int compareTo(Interval anotherInterval) {
        return Integer.compare(this.low, anotherInterval.low);
    }
}
