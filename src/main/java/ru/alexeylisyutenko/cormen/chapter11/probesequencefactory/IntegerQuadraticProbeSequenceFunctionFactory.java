package ru.alexeylisyutenko.cormen.chapter11.probesequencefactory;

import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.HashFunctionFactory;
import ru.alexeylisyutenko.cormen.chapter11.probesequence.ProbeSequenceFunction;
import ru.alexeylisyutenko.cormen.chapter11.probesequence.QuadraticProbeSequenceFunction;

public class IntegerQuadraticProbeSequenceFunctionFactory implements ProbeSequenceFunctionFactory<Integer> {

    private final HashFunctionFactory<Integer> auxiliaryHashFunctionFactory;
    private final int c1;
    private final int c2;

    public IntegerQuadraticProbeSequenceFunctionFactory(HashFunctionFactory<Integer> auxiliaryHashFunctionFactory, int c1, int c2) {
        this.auxiliaryHashFunctionFactory = auxiliaryHashFunctionFactory;
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public ProbeSequenceFunction<Integer> create(int hashTableSize) {
        return new QuadraticProbeSequenceFunction<>(auxiliaryHashFunctionFactory.create(hashTableSize), hashTableSize, c1, c2);
    }
}
