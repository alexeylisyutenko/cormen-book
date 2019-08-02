package ru.alexeylisyutenko.cormen.chapter11.probesequence;

import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;

import java.util.Objects;

public class LinearProbeSequenceFunction<K> implements ProbeSequenceFunction<K> {
    private final HashFunction<K> auxiliaryHashFunction;
    private final int hashTableSize;

    public LinearProbeSequenceFunction(HashFunction<K> auxiliaryHashFunction, int hashTableSize) {
        this.auxiliaryHashFunction = auxiliaryHashFunction;
        this.hashTableSize = hashTableSize;
    }

    @Override
    public int calculateHash(K key, int probeNumber) {
        Objects.requireNonNull(key, "key cannot be null");
        if (probeNumber < 0 || probeNumber >= hashTableSize) {
            throw new IllegalArgumentException(String.format("Illegal probeNumber: %d", probeNumber));
        }
        return (int) ((long) auxiliaryHashFunction.calculateHash(key) + probeNumber) % hashTableSize;
    }
}
