package ru.alexeylisyutenko.cormen.chapter11.probesequence;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.hashfunction.DivisionMethodIntegerHashFunction;

import static org.junit.jupiter.api.Assertions.*;

class LinearProbeSequenceFunctionTest {

    @Test
    void calculateHashShouldWorkProperly() {
        LinearProbeSequenceFunction<Integer> probeSequenceFunction = new LinearProbeSequenceFunction<>(new DivisionMethodIntegerHashFunction(5), 5);

        assertEquals(1, probeSequenceFunction.calculateHash(1, 0));
        assertEquals(2, probeSequenceFunction.calculateHash(1, 1));
        assertEquals(3, probeSequenceFunction.calculateHash(1, 2));
        assertEquals(4, probeSequenceFunction.calculateHash(1, 3));
        assertEquals(0, probeSequenceFunction.calculateHash(1, 4));
    }

}