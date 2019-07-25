package ru.alexeylisyutenko.cormen.chapter11;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable.BuiltInChainingHashTable;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.MultiplicationMethodIntegerHashFunctionFactory;

class BuiltInChainingHashTableTest {

    @Test
    void mainOperationsDemo() {
        HashTable<Integer, String> hashTable = new BuiltInChainingHashTable<>(new MultiplicationMethodIntegerHashFunctionFactory(), 8);


    }

}