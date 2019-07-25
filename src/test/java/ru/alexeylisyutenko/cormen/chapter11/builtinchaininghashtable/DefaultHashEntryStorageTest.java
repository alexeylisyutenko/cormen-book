package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.HashTableException;

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

        storage.releaseHashEntry(hashEntry2);
        storage.releaseHashEntry(hashEntry3);
        System.out.println(storage);
    }

    @Test
    void getHashEntryShouldWorkProperly() {
        HashEntry<Integer, String> hashEntry = storage.getHashEntry(1);
        assertTrue(hashEntry.isEmpty());
        assertEquals(1, hashEntry.getIndex());
        assertNull(hashEntry.getKey());
        assertNull(hashEntry.getValue());
        assertEquals(2, hashEntry.getNextIndex());
        assertEquals(0, hashEntry.getPreviousIndex());

        assertThrows(IndexOutOfBoundsException.class, () -> storage.getHashEntry(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> storage.getHashEntry(3));
    }

    @Test
    void allocateAndReleaseShouldWorkProperly() {
        // Allocate first entry.
        HashEntry<Integer, String> hashEntry1 = storage.allocateHashEntry();
        assertFalse(hashEntry1.isEmpty());
        assertEquals(0, hashEntry1.getIndex());
        assertNull(hashEntry1.getKey());
        assertNull(hashEntry1.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry1.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry1.getPreviousIndex());

        // Allocate second entry.
        HashEntry<Integer, String> hashEntry2 = storage.allocateHashEntry();
        assertFalse(hashEntry2.isEmpty());
        assertEquals(1, hashEntry2.getIndex());
        assertNull(hashEntry2.getKey());
        assertNull(hashEntry2.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry2.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry2.getPreviousIndex());

        // Allocate third entry.
        HashEntry<Integer, String> hashEntry3 = storage.allocateHashEntry();
        assertFalse(hashEntry3.isEmpty());
        assertEquals(2, hashEntry3.getIndex());
        assertNull(hashEntry3.getKey());
        assertNull(hashEntry3.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry3.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry3.getPreviousIndex());

        // Fourth allocation must case exception.
        HashTableException exception = assertThrows(HashTableException.class, () -> storage.allocateHashEntry());
        assertEquals("Out of space", exception.getMessage());

        // Change some attributes of an entry and release it.
        hashEntry2.setKey(12345);
        hashEntry2.setValue("some value");
        hashEntry2.setPreviousIndex(0);
        hashEntry2.setNextIndex(1);
        storage.releaseHashEntry(hashEntry2);
        assertTrue(storage.getHashEntry(1).isEmpty());

        // Allocate one more entry.
        HashEntry<Integer, String> hashEntry4 = storage.allocateHashEntry();
        assertFalse(hashEntry4.isEmpty());
        assertEquals(1, hashEntry4.getIndex());
        assertNull(hashEntry4.getKey());
        assertNull(hashEntry4.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry4.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry4.getPreviousIndex());
    }

    @Test
    void isHashEntryEmptyShouldWorkProperly() {
        assertTrue(storage.isHashEntryEmpty(0));
        assertTrue(storage.isHashEntryEmpty(1));
        assertTrue(storage.isHashEntryEmpty(2));

        HashEntry<Integer, String> hashEntry1 = storage.allocateHashEntry();
        HashEntry<Integer, String> hashEntry2 = storage.allocateHashEntry();
        HashEntry<Integer, String> hashEntry3 = storage.allocateHashEntry();

        assertFalse(storage.isHashEntryEmpty(0));
        assertFalse(storage.isHashEntryEmpty(1));
        assertFalse(storage.isHashEntryEmpty(2));

        storage.releaseHashEntry(hashEntry2);

        assertFalse(storage.isHashEntryEmpty(0));
        assertTrue(storage.isHashEntryEmpty(1));
        assertFalse(storage.isHashEntryEmpty(2));
    }

    @Test
    void allocateParticularHashEntryShouldWorkProperly() {
        // Allocate an entry with index 1.
        HashEntry<Integer, String> hashEntry2 = storage.allocateParticularHashEntry(1);
        assertFalse(hashEntry2.isEmpty());
        assertEquals(1, hashEntry2.getIndex());
        assertNull(hashEntry2.getKey());
        assertNull(hashEntry2.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry2.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry2.getPreviousIndex());

        HashEntry<Integer, String> hashEntry1 = storage.getHashEntry(0);
        assertTrue(hashEntry1.isEmpty());
        assertEquals(0, hashEntry1.getIndex());
        assertNull(hashEntry1.getKey());
        assertNull(hashEntry1.getValue());
        assertEquals(2, hashEntry1.getNextIndex());
        assertEquals(-1, hashEntry1.getPreviousIndex());

        HashEntry<Integer, String> hashEntry3 = storage.getHashEntry(2);
        assertTrue(hashEntry3.isEmpty());
        assertEquals(2, hashEntry3.getIndex());
        assertNull(hashEntry3.getKey());
        assertNull(hashEntry3.getValue());
        assertEquals(-1, hashEntry3.getNextIndex());
        assertEquals(0, hashEntry3.getPreviousIndex());

        // Allocate an entry with index 0.
        hashEntry1 = storage.allocateParticularHashEntry(0);
        assertFalse(hashEntry1.isEmpty());
        assertEquals(0, hashEntry1.getIndex());
        assertNull(hashEntry1.getKey());
        assertNull(hashEntry1.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry1.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry1.getPreviousIndex());

        hashEntry2 = storage.getHashEntry(1);
        assertFalse(hashEntry2.isEmpty());
        assertEquals(1, hashEntry2.getIndex());
        assertNull(hashEntry2.getKey());
        assertNull(hashEntry2.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry2.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry2.getPreviousIndex());

        hashEntry3 = storage.getHashEntry(2);
        assertTrue(hashEntry3.isEmpty());
        assertEquals(2, hashEntry3.getIndex());
        assertNull(hashEntry3.getKey());
        assertNull(hashEntry3.getValue());
        assertEquals(-1, hashEntry3.getNextIndex());
        assertEquals(-1, hashEntry3.getPreviousIndex());

        // Allocate an entry with index 2.
        hashEntry3 = storage.allocateParticularHashEntry(2);
        assertFalse(hashEntry3.isEmpty());
        assertEquals(2, hashEntry3.getIndex());
        assertNull(hashEntry3.getKey());
        assertNull(hashEntry3.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry3.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry3.getPreviousIndex());

        hashEntry1 = storage.getHashEntry(0);
        assertFalse(hashEntry1.isEmpty());
        assertEquals(0, hashEntry1.getIndex());
        assertNull(hashEntry1.getKey());
        assertNull(hashEntry1.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry1.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry1.getPreviousIndex());

        hashEntry2 = storage.getHashEntry(1);
        assertFalse(hashEntry2.isEmpty());
        assertEquals(1, hashEntry2.getIndex());
        assertNull(hashEntry2.getKey());
        assertNull(hashEntry2.getValue());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry2.getNextIndex());
        assertEquals(HashEntry.NIL_HASH_ENTRY_INDEX, hashEntry2.getPreviousIndex());
    }

    @Test
    void allocateParticularHashEntryShouldFailWhenEntryInNotEmpty() {
        HashEntry<Integer, String> hashEntry2 = storage.allocateParticularHashEntry(1);

        HashTableException exception1 = assertThrows(HashTableException.class, () -> storage.allocateParticularHashEntry(1));
        assertEquals("Hash entry with index '1' is not empty", exception1.getMessage());

        HashEntry<Integer, String> hashEntry1 = storage.allocateHashEntry();
        assertEquals(0, hashEntry1.getIndex());

        HashTableException exception2 = assertThrows(HashTableException.class, () -> storage.allocateParticularHashEntry(0));
        assertEquals("Hash entry with index '0' is not empty", exception2.getMessage());
    }

}