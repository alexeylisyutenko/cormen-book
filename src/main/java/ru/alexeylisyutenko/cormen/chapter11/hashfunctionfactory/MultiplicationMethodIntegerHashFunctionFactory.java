package ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory;

import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;
import ru.alexeylisyutenko.cormen.chapter11.hashfunction.MultiplicationMethodIntegerHashFunction;

public class MultiplicationMethodIntegerHashFunctionFactory implements HashFunctionFactory<Integer> {
    @Override
    public HashFunction<Integer> create(int hashTableSize) {
        return new MultiplicationMethodIntegerHashFunction(hashTableSize);
    }
}
