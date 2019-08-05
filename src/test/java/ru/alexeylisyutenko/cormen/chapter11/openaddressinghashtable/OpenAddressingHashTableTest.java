package ru.alexeylisyutenko.cormen.chapter11.openaddressinghashtable;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.HashTableException;
import ru.alexeylisyutenko.cormen.chapter11.RandomizedHashTableTestHelper;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.DivisionMethodIntegerHashFunctionFactory;
import ru.alexeylisyutenko.cormen.chapter11.probesequencefactory.IntegerDoubleHashingProbeSequenceFunctionFactory;
import ru.alexeylisyutenko.cormen.chapter11.probesequencefactory.IntegerLinearProbeSequenceFunctionFactory;
import ru.alexeylisyutenko.helper.Primes;

import static org.junit.jupiter.api.Assertions.*;

class OpenAddressingHashTableTest {

    @Test
    void mainOperationsDemo() {
        IntegerLinearProbeSequenceFunctionFactory sequenceFunctionFactory = new IntegerLinearProbeSequenceFunctionFactory(new DivisionMethodIntegerHashFunctionFactory());
        OpenAddressingHashTable<Integer, String> hashTable = new OpenAddressingHashTable<>(sequenceFunctionFactory, 5);

        hashTable.insert(0, "0");
        hashTable.insert(1, "1");
        hashTable.insert(3, "3");
        hashTable.insert(5, "5");

        hashTable.delete(5);
        System.out.println(hashTable.search(5));
        System.out.println(hashTable);

    }

    @Test
    void mainOperationsShouldWorkProperly() {
        IntegerLinearProbeSequenceFunctionFactory sequenceFunctionFactory = new IntegerLinearProbeSequenceFunctionFactory(new DivisionMethodIntegerHashFunctionFactory());
        OpenAddressingHashTable<Integer, String> hashTable = new OpenAddressingHashTable<>(sequenceFunctionFactory, 5);

        assertNull(hashTable.search(0));
        HashTableException exception = assertThrows(HashTableException.class, () -> hashTable.delete(0));
        assertEquals("There is no item with a key '0' in the calculateHash table", exception.getMessage());

        hashTable.insert(0, "0");
        assertEquals("0", hashTable.search(0));

        hashTable.insert(5, "5");
        assertEquals("0", hashTable.search(0));
        assertEquals("5", hashTable.search(5));

        hashTable.delete(0);
        assertNull(hashTable.search(0));
        assertEquals("5", hashTable.search(5));
    }

    @Test
    void randomizedLinearProbingHashTableTest() {
        RandomizedHashTableTestHelper.run(hashTableSize -> {
            IntegerLinearProbeSequenceFunctionFactory sequenceFunctionFactory = new IntegerLinearProbeSequenceFunctionFactory(new DivisionMethodIntegerHashFunctionFactory());
            return new OpenAddressingHashTable<>(sequenceFunctionFactory, hashTableSize);
        }, 100000, 1000);
    }

    @Test
    void randomizedDoubleHashingProbingHashTableTest() {
        RandomizedHashTableTestHelper.run(hashTableSize -> {
            hashTableSize = Primes.smallestPrimeGreaterThan(hashTableSize);
            return new OpenAddressingHashTable<>(new IntegerDoubleHashingProbeSequenceFunctionFactory(), hashTableSize);
        }, 100000, 1000);
    }

}