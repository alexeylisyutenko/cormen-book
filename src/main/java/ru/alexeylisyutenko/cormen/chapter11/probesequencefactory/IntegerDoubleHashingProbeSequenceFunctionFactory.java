package ru.alexeylisyutenko.cormen.chapter11.probesequencefactory;

import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.HashFunctionFactory;
import ru.alexeylisyutenko.cormen.chapter11.probesequence.DoubleHashingProbeSequenceFunction;
import ru.alexeylisyutenko.cormen.chapter11.probesequence.ProbeSequenceFunction;

public class IntegerDoubleHashingProbeSequenceFunctionFactory implements ProbeSequenceFunctionFactory<Integer> {

    private final HashFunctionFactory<Integer> auxiliaryHashFunctionFactory1;
    private final HashFunctionFactory<Integer> auxiliaryHashFunctionFactory2;

    public IntegerDoubleHashingProbeSequenceFunctionFactory(HashFunctionFactory<Integer> auxiliaryHashFunctionFactory1, HashFunctionFactory<Integer> auxiliaryHashFunctionFactory2) {
        this.auxiliaryHashFunctionFactory1 = auxiliaryHashFunctionFactory1;
        this.auxiliaryHashFunctionFactory2 = auxiliaryHashFunctionFactory2;
    }

    public IntegerDoubleHashingProbeSequenceFunctionFactory() {
        this.auxiliaryHashFunctionFactory1 = new DefaultAuxiliaryHashFunctionFactoryOne();
        this.auxiliaryHashFunctionFactory2 = new DefaultAuxiliaryHashFunctionFactoryTwo();
    }

    @Override
    public ProbeSequenceFunction<Integer> create(int hashTableSize) {
        return new DoubleHashingProbeSequenceFunction<>(auxiliaryHashFunctionFactory1.create(hashTableSize),
                auxiliaryHashFunctionFactory2.create(hashTableSize), hashTableSize);
    }


    private static class DefaultAuxiliaryHashFunctionFactoryOne implements HashFunctionFactory<Integer> {
        @Override
        public HashFunction<Integer> create(int hashTableSize) {
            return key -> {
                long longKey = key & 0x00000000ffffffffL;
                return (int) (longKey % hashTableSize);
            };
        }
    }

    private static class DefaultAuxiliaryHashFunctionFactoryTwo implements HashFunctionFactory<Integer> {
        @Override
        public HashFunction<Integer> create(int hashTableSize) {
            return key -> {
                long longKey = key & 0x00000000ffffffffL;
                return (int) (1 + (longKey % (hashTableSize - 1)));
            };
        }
    }

}
