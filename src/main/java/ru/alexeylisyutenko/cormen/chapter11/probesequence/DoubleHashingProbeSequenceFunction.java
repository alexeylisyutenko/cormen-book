package ru.alexeylisyutenko.cormen.chapter11.probesequence;

import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;

public class DoubleHashingProbeSequenceFunction<K> implements ProbeSequenceFunction<K> {
    private final HashFunction<K> auxiliaryHashFunction1;
    private final HashFunction<K> auxiliaryHashFunction2;
    private final int hashTableSize;

    public DoubleHashingProbeSequenceFunction(HashFunction<K> auxiliaryHashFunction1, HashFunction<K> auxiliaryHashFunction2, int hashTableSize) {
        this.auxiliaryHashFunction1 = auxiliaryHashFunction1;
        this.auxiliaryHashFunction2 = auxiliaryHashFunction2;
        this.hashTableSize = hashTableSize;
    }

    @Override
    public int calculateHash(K key, int probeNumber) {
        int hash1 = auxiliaryHashFunction1.calculateHash(key);
        int hash2 = auxiliaryHashFunction2.calculateHash(key);
        return (hash1 + (hash2 * probeNumber)) % hashTableSize ;
    }
}
