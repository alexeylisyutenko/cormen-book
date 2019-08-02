package ru.alexeylisyutenko.cormen.chapter11.probesequencefactory;

import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.HashFunctionFactory;
import ru.alexeylisyutenko.cormen.chapter11.probesequence.LinearProbeSequenceFunction;
import ru.alexeylisyutenko.cormen.chapter11.probesequence.ProbeSequenceFunction;

public class IntegerLinearProbeSequenceFunctionFactory implements ProbeSequenceFunctionFactory<Integer> {
    private final HashFunctionFactory<Integer> auxiliaryHashFunctionFactory;

    public IntegerLinearProbeSequenceFunctionFactory(HashFunctionFactory<Integer> auxiliaryHashFunctionFactory) {
        this.auxiliaryHashFunctionFactory = auxiliaryHashFunctionFactory;
    }

    @Override
    public ProbeSequenceFunction<Integer> create(int hashTableSize) {
        return new LinearProbeSequenceFunction<>(auxiliaryHashFunctionFactory.create(hashTableSize), hashTableSize);
    }
}
