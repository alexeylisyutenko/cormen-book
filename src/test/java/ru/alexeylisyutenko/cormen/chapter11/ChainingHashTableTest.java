package ru.alexeylisyutenko.cormen.chapter11;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.chaininghashtable.ChainingHashTable;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.DivisionMethodIntegerHashFunctionFactory;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.MultiplicationMethodIntegerHashFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;

class ChainingHashTableTest {

    @Test
    void simpleHashTableDemo() {
//        HashTable<Integer, String> hashTable = new ChainingHashTable<>(new DivisionMethodIntegerHashFunctionFactory(), 13);
        HashTable<Integer, String> hashTable = new ChainingHashTable<>(new MultiplicationMethodIntegerHashFunctionFactory(), 8);

        hashTable.insert(RandomUtils.nextInt(), "Hello - 1");
        hashTable.insert(RandomUtils.nextInt(), "Hello - 2");
        hashTable.insert(RandomUtils.nextInt(), "Hello - 3");

        System.out.println(hashTable);
    }

    @Test
    void mainChainingHashTableOperationsShouldWorkProperly() {
        HashTable<Integer, String> hashTable = new ChainingHashTable<>(new DivisionMethodIntegerHashFunctionFactory(), 13);

        assertNull(hashTable.search(123));
        hashTable.insert(123, "test 1");
        assertEquals("test 1", hashTable.search(123));

        assertNull(hashTable.search(555));
        hashTable.insert(555, "test 2");
        assertEquals("test 2", hashTable.search(555));

        HashTableException insertException = assertThrows(HashTableException.class, () -> hashTable.insert(123, "some value"));
        assertEquals("An item with a key '123' is already in the hash table", insertException.getMessage());

        HashTableException deleteException = assertThrows(HashTableException.class, () -> hashTable.delete(777));
        assertEquals("There is no item with a key '777' in the hash table", deleteException.getMessage());

        assertEquals("test 1", hashTable.search(123));
        hashTable.delete(123);
        assertNull(hashTable.search(123));

        assertEquals("test 2", hashTable.search(555));
        hashTable.delete(555);
        assertNull(hashTable.search(555));

        hashTable.insert(0, "test 3");
        hashTable.insert(13, "test 4");
        assertEquals("test 3", hashTable.search(0));
        assertEquals("test 4", hashTable.search(13));

        hashTable.insert(-13, "negative key");
        assertEquals("negative key", hashTable.search(-13));
    }

}