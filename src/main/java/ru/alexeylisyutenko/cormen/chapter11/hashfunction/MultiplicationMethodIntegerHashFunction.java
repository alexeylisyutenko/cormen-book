package ru.alexeylisyutenko.cormen.chapter11.hashfunction;

import java.util.Objects;

public class MultiplicationMethodIntegerHashFunction implements HashFunction<Integer> {

    private static final long S = 2654435769L;

    private final int bits;
    private final long mask;

    public MultiplicationMethodIntegerHashFunction(int hashTableSize) {
        if (!isPowerOfTwo(hashTableSize)) {
            throw new IllegalArgumentException("hashTableSize must be a power of 2");
        }
        this.bits = (int)(Math.log(hashTableSize) / Math.log(2));
        this.mask = (1L << bits) - 1;
    }

    private boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }
        return (int) (Math.ceil((Math.log(n) / Math.log(2)))) ==
                (int) (Math.floor(((Math.log(n) / Math.log(2)))));
    }

    @Override
    public int calculateHash(Integer key) {
        Objects.requireNonNull(key, "key cannot be null");

        long longKey = key & 0x00000000ffffffffL;
        long product = longKey * S;
        return (int)((product >> (32 - bits)) & mask);
    }
}
