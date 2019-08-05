package ru.alexeylisyutenko.cormen.chapter11.probesequence;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.hashfunction.DivisionMethodIntegerHashFunction;

import static org.junit.jupiter.api.Assertions.*;

class QuadraticProbeSequenceFunctionTest {

    @Test
    void calculateHashShouldWorkProperly() {
        QuadraticProbeSequenceFunction<Integer> probeSequenceFunction =
                new QuadraticProbeSequenceFunction<>(new DivisionMethodIntegerHashFunction(11), 11, 1, 3);

        assertEquals(0, probeSequenceFunction.calculateHash(0, 0));
        assertEquals(4, probeSequenceFunction.calculateHash(0, 1));
        assertEquals(3, probeSequenceFunction.calculateHash(0, 2));
        assertEquals(8, probeSequenceFunction.calculateHash(0, 3));
        assertEquals(8, probeSequenceFunction.calculateHash(0, 4));
        assertEquals(3, probeSequenceFunction.calculateHash(0, 5));
        assertEquals(4, probeSequenceFunction.calculateHash(0, 6));
        assertEquals(0, probeSequenceFunction.calculateHash(0, 7));

        // This choice of constants c1 and c2 is incorrect here since there is no way to pass through all slots in a table.
    }

}