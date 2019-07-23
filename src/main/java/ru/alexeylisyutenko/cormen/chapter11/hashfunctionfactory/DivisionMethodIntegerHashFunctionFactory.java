package ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory;

import ru.alexeylisyutenko.cormen.chapter11.hashfunction.DivisionMethodIntegerHashFunction;
import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;

public class DivisionMethodIntegerHashFunctionFactory implements HashFunctionFactory<Integer> {
    @Override
    public HashFunction<Integer> create(int hashTableSize) {
        return new DivisionMethodIntegerHashFunction(hashTableSize);
    }
}
