package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.HashTable;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.DivisionMethodIntegerHashFunctionFactory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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
        int testCount = 100000;
        for (int i = 0; i < testCount; i++) {
            singleRandomizedTest();
        }
    }

    private void singleRandomizedTest() {
        int hashTableSize = RandomUtils.nextInt(1, 256);
        HashTable<Integer, String> hashTable = new BuiltInChainingHashTable<>(new DivisionMethodIntegerHashFunctionFactory(), hashTableSize);

        // Insert n random items.
        int numberOfItemsToInsert = RandomUtils.nextInt((hashTableSize / 2) + 1, hashTableSize + 1);
        List<Integer> keys = generateRandomDistinctIntegers(numberOfItemsToInsert);
        for (Integer integer : keys) {
            hashTable.insert(integer, integer + "");
        }

        // Check that all inserted items can be successfully searched.
        for (Integer key : keys) {
            assertEquals(key + "", hashTable.search(key));
        }

        // Select random elements to delete from the table.
        Set<Integer> indexesOfNumbersToDelete = getIndexesOfNumbersToDelete(keys.size());
        List<Integer> keysToDelete = new ArrayList<>();
        List<Integer> keysToKeep = new ArrayList<>();
        for (int i = 0; i < keys.size(); i++) {
            if (indexesOfNumbersToDelete.contains(i)) {
                keysToDelete.add(keys.get(i));
            } else {
                keysToKeep.add(keys.get(i));
            }
        }

        // Delete some items from the hash table.
        for (Integer keyToDelete : keysToDelete) {
            hashTable.delete(keyToDelete);
        }

        // Check that deleted keys cannot be searched.
        for (Integer key : keysToDelete) {
            assertNull(hashTable.search(key));
        }

        // Check that not deleted keys still can be searched
        for (Integer key : keysToKeep) {
            assertEquals(key + "", hashTable.search(key));
        }

        // Insert deleted keys back again.
        for (Integer key : keysToDelete) {
            hashTable.insert(key, key + "");
        }

        // Check that all inserted items can be successfully searched.
        for (Integer key : keys) {
            assertEquals(key + "", hashTable.search(key));
        }
    }

    private List<Integer> generateRandomDistinctIntegers(int count) {
        HashSet<Integer> integerHashSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            int randomInt;
            do {
                randomInt = RandomUtils.nextBoolean() ? RandomUtils.nextInt() : -RandomUtils.nextInt();
            } while (integerHashSet.contains(randomInt));
            integerHashSet.add(randomInt);
        }
        return new ArrayList<>(integerHashSet);
    }

    private Set<Integer> getIndexesOfNumbersToDelete(int numberOfItemsToInsert) {
        List<Integer> indexes = IntStream.range(0, numberOfItemsToInsert).boxed().collect(toList());
        Collections.shuffle(indexes);
        return indexes.stream().limit(numberOfItemsToInsert / 2).collect(Collectors.toSet());
    }

}