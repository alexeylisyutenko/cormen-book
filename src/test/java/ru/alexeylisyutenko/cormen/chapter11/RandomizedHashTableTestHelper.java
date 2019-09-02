package ru.alexeylisyutenko.cormen.chapter11;

import org.apache.commons.lang3.RandomUtils;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RandomizedHashTableTestHelper {

    public static void run(HashTableSupplier supplier, int testCount, int hashTableSizeUpperBound) {
        for (int i = 0; i < testCount; i++) {
            singleRandomizedTest(supplier, hashTableSizeUpperBound);
        }
    }

    private static void singleRandomizedTest(HashTableSupplier supplier, int hashTableSizeUpperBound) {
        int hashTableSize = RandomUtils.nextInt(1, hashTableSizeUpperBound);
        HashTable<Integer, String> hashTable = supplier.create(hashTableSize);

        // Insert n random items.
        int numberOfItemsToInsert = RandomUtils.nextInt((hashTableSize / 2) + 1, hashTableSize + 1);
        List<Integer> keys = Helpers.generateRandomDistinctIntegers(numberOfItemsToInsert);
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

        // Delete some items from the calculateHash table.
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

    private static Set<Integer> getIndexesOfNumbersToDelete(int numberOfItemsToInsert) {
        List<Integer> indexes = IntStream.range(0, numberOfItemsToInsert).boxed().collect(toList());
        Collections.shuffle(indexes);
        return indexes.stream().limit(numberOfItemsToInsert / 2).collect(Collectors.toSet());
    }

    @FunctionalInterface
    public interface HashTableSupplier {
        HashTable<Integer, String> create(int hashTableSize);
    }

}
