package ru.alexeylisyutenko.cormen.chapter11.probesequence;

import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;

import java.math.BigInteger;

public class QuadraticProbeSequenceFunction<K> implements ProbeSequenceFunction<K> {
    private final HashFunction<K> auxiliaryHashFunction;
    private final int hashTableSize;
    private final int c1;
    private final int c2;

    public QuadraticProbeSequenceFunction(HashFunction<K> auxiliaryHashFunction, int hashTableSize, int c1, int c2) {
        this.auxiliaryHashFunction = auxiliaryHashFunction;
        this.hashTableSize = hashTableSize;
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public int calculateHash(K key, int probeNumber) {
        BigInteger i1 = BigInteger.valueOf(auxiliaryHashFunction.calculateHash(key))
                        .mod(BigInteger.valueOf(hashTableSize));

        BigInteger i2 = BigInteger.valueOf(c1)
                .multiply(BigInteger.valueOf(probeNumber))
                .mod(BigInteger.valueOf(hashTableSize));

        BigInteger i3 = BigInteger.valueOf(c2)
                .multiply(BigInteger.valueOf(probeNumber))
                .multiply(BigInteger.valueOf(probeNumber))
                .mod(BigInteger.valueOf(hashTableSize));

        BigInteger rs = i1.add(i2).add(i3).mod(BigInteger.valueOf(hashTableSize));

        return rs.intValue();
    }
}
