package ru.alexeylisyutenko.cormen.chapter11.hashfunction;

import java.util.Objects;

public class DivisionMethodIntegerHashFunction implements HashFunction<Integer> {
    private final int hashTableSize;

    public DivisionMethodIntegerHashFunction(int hashTableSize) {
        this.hashTableSize = hashTableSize;
    }

    @Override
    public int calculateHash(Integer key) {
        Objects.requireNonNull(key, "key cannot be null");
        return key % hashTableSize;
    }
}
