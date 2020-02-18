package ru.alexeylisyutenko.cormen.chapter14.exercise14_3_7;

import ru.alexeylisyutenko.cormen.chapter14.intervaltree.Interval;

public final class Rectangle {
    private final int xLow;
    private final int xHigh;
    private final int yLow;
    private final int yHigh;

    public Rectangle(int xLow, int xHigh, int yLow, int yHigh) {
        validateArguments(xLow, xHigh, yLow, yHigh);
        this.xLow = xLow;
        this.xHigh = xHigh;
        this.yLow = yLow;
        this.yHigh = yHigh;
    }

    private void validateArguments(int xLow, int xHigh, int yLow, int yHigh) {
        if (xLow > xHigh) {
            throw new IllegalArgumentException("xLow must be less or equal than xHigh");
        }
        if (yLow > yHigh) {
            throw new IllegalArgumentException("yLow must be less or equal than yHigh");
        }
    }

    public int getxLow() {
        return xLow;
    }

    public int getxHigh() {
        return xHigh;
    }

    public int getyLow() {
        return yLow;
    }

    public int getyHigh() {
        return yHigh;
    }

    public Interval getVerticalInterval() {
        return new Interval(yLow, yHigh);
    }
}
