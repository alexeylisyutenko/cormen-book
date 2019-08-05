package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.HashTable;
import ru.alexeylisyutenko.cormen.chapter11.RandomizedHashTableTestHelper;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.DivisionMethodIntegerHashFunctionFactory;

class BuiltInChainingHashTableTest {

    @Test
    void demo() {
//        HashTable<Integer, String> hashTable = new BuiltInChainingHashTable<>(new MultiplicationMethodIntegerHashFunctionFactory(), 8);
        HashTable<Integer, String> hashTable = new BuiltInChainingHashTable<>(new DivisionMethodIntegerHashFunctionFactory(), 8);

        hashTable.insert(0, "some value");
        System.out.println(hashTable);

        hashTable.insert(8, "some value - 2");
        System.out.println(hashTable);

        hashTable.insert(16, "some value - 3");
        System.out.println(hashTable);

        System.out.println(hashTable.search(0));
        System.out.println(hashTable.search(8));
        System.out.println(hashTable.search(16));

        hashTable.insert(1, "some value - 4");
        System.out.println(hashTable);

        System.out.println(hashTable.search(0));
        System.out.println(hashTable.search(8));
        System.out.println(hashTable.search(16));
        System.out.println(hashTable.search(1));

        hashTable.delete(1);
        System.out.println(hashTable);
    }

    @Test
    void randomizedHashTableTest() {
        RandomizedHashTableTestHelper.run(hashTableSize -> new BuiltInChainingHashTable<>(new DivisionMethodIntegerHashFunctionFactory(), hashTableSize), 100000, 1000);
    }

}