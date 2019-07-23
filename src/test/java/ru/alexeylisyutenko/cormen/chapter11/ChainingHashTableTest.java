package ru.alexeylisyutenko.cormen.chapter11;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.DivisionMethodIntegerHashFunctionFactory;

class ChainingHashTableTest {

    @Test
    void simpleHashTableDemo() {
        HashTable<Integer, String> hashTable = new ChainingHashTable<>(new DivisionMethodIntegerHashFunctionFactory(), 13);

        System.out.println(hashTable.search(0));
        hashTable.insert(0, "Hello");
        System.out.println(hashTable.search(0));

        hashTable.insert(13, "Hello - 2");

        hashTable.delete(13);

        System.out.println(hashTable);
    }

    @Test
    void mainChainingHashTableOperationsShouldWorkProperly() {
        HashTable<Integer, String> hashTable = new ChainingHashTable<>(new DivisionMethodIntegerHashFunctionFactory(), 13);


    }

}