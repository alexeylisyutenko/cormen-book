package ru.alexeylisyutenko.cormen.chapter14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayInversionsCountAlgorithmTest {

    @Test
    void countArrayInversionsShouldWorkProperly() {
        assertEquals(5, ArrayInversionsCountAlgorithm.countArrayInversions(new int[]{2, 3, 8, 6, 1}));
        assertEquals(6, ArrayInversionsCountAlgorithm.countArrayInversions(new int[]{4, 3, 2, 1}));
        assertEquals(0, ArrayInversionsCountAlgorithm.countArrayInversions(new int[]{1, 2, 3, 4}));
        assertEquals(1, ArrayInversionsCountAlgorithm.countArrayInversions(new int[]{1, 3, 2, 4}));
    }
}