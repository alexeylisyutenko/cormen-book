package ru.alexeylisyutenko.cormen.chapter11.probesequence;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.probesequencefactory.IntegerDoubleHashingProbeSequenceFunctionFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoubleHashingProbeSequenceFunctionTest {

    @Test
    void calculateHashShouldWorkProperly() {
        IntegerDoubleHashingProbeSequenceFunctionFactory sequenceFunctionFactory = new IntegerDoubleHashingProbeSequenceFunctionFactory();
        ProbeSequenceFunction<Integer> probeSequenceFunction = sequenceFunctionFactory.create(7);

        assertEquals(4, probeSequenceFunction.calculateHash(4, 0));
        assertEquals(2, probeSequenceFunction.calculateHash(4, 1));
        assertEquals(0, probeSequenceFunction.calculateHash(4, 2));
        assertEquals(5, probeSequenceFunction.calculateHash(4, 3));
        assertEquals(3, probeSequenceFunction.calculateHash(4, 4));
        assertEquals(1, probeSequenceFunction.calculateHash(4, 5));
        assertEquals(6, probeSequenceFunction.calculateHash(4, 6));
    }

}