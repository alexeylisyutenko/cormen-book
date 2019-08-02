package ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory;

import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;

/**
 *  Factory of calculateHash functions.
 */
public interface HashFunctionFactory<K> {
    /**
     * Create a new calculateHash function.
     *
     * @param hashTableSize size of a calculateHash table where a new calculateHash function will be used at
     * @return a new calculateHash function
     */
    HashFunction<K> create(int hashTableSize);
}
