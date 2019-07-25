package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultHashEntryStorageTest {

    private DefaultHashEntryStorage<Integer, String> storage;

    @BeforeEach
    void setup() {
        storage = new DefaultHashEntryStorage<>(3);
    }

    @Test
    void demo() {
        System.out.println(storage);

        HashEntry<Integer, String> hashEntry1 = storage.allocateHashEntry();
        HashEntry<Integer, String> hashEntry2 = storage.allocateHashEntry();
        HashEntry<Integer, String> hashEntry3 = storage.allocateHashEntry();
        System.out.println(storage);

        storage.releaseHashEntry(hashEntry1);
        storage.releaseHashEntry(hashEntry3);
        System.out.println(storage);

    }

    @Test
    void getHashEntryShouldWorkProperly() {
        HashEntry<Integer, String> hashEntry = storage.getHashEntry(1);
        assertEquals(1, hashEntry.getIndex());
        assertEquals(null, hashEntry.getKey());
        assertEquals(null, hashEntry.getValue());
        assertEquals(2, hashEntry.getNextIndex());
        assertEquals(0, hashEntry.getPreviousIndex());
    }

}