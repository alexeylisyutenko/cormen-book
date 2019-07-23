package ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory;

import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;

/**
 *  Factory of hash functions.
 */
public interface HashFunctionFactory<K> {
    /**
     * Create a new hash function.
     *
     * @param hashTableSize size of a hash table where a new hash function will be used at
     * @return a new hash function
     */
    HashFunction<K> create(int hashTableSize);
}
