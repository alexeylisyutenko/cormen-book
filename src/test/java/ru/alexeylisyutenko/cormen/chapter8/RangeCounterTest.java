package ru.alexeylisyutenko.cormen.chapter8;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RangeCounterTest {

    @Test
    void getRangeCountDemo() {
        int[] array = {2, 5, 3, 0, 2, 3, 0, 3};
        RangeCounter rangeCounter = new RangeCounter(array, 5);
        System.out.println(rangeCounter.getCountInRange(1, 1));
    }

}