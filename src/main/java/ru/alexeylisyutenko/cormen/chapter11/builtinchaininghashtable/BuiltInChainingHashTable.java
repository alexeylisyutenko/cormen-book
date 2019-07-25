package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

import ru.alexeylisyutenko.cormen.chapter11.HashTable;
import ru.alexeylisyutenko.cormen.chapter11.HashTableException;
import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.HashFunctionFactory;

import java.util.Objects;

import static ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable.HashEntry.NIL_HASH_ENTRY_INDEX;

/**
 * Hash table implementation which uses the hash table itself as a storage for linked list elements.
 * This implementation corresponds to an exercise 11.2-4 from Cormen's book.
 */
@SuppressWarnings("unchecked")
public class BuiltInChainingHashTable<K, V> implements HashTable<K, V> {

    private final HashFunction<K> hashFunction;
    private final int hashTableSize;
    private final HashEntryStorage<K, V> storage;

    public BuiltInChainingHashTable(HashFunctionFactory<K> hashFunctionFactory, int hashTableSize) {
        validateArguments(hashTableSize, hashFunctionFactory);
        this.hashTableSize = hashTableSize;
        this.hashFunction = hashFunctionFactory.create(hashTableSize);
        this.storage = new DefaultHashEntryStorage<>(hashTableSize);
    }

    private void validateArguments(int hashTableSize, HashFunctionFactory<K> hashFunctionFactory) {
        Objects.requireNonNull(hashFunctionFactory, "hashFunctionFactory cannot be null");
        if (hashTableSize < 1) {
            throw new IllegalArgumentException("hashTableSize must be positive");
        }
    }

    @Override
    public void insert(K key, V value) {
        Objects.requireNonNull(key, "key cannot be null");
        Objects.requireNonNull(value, "value cannot be null");

        ensureThereIsNoSuchKeyInTheTable(key);

        int hash = calculateHash(key);
        if (storage.isHashEntryEmpty(hash)) {
            // Slot is empty. We just need to use this entry to store the key-value pair.
            HashEntry<K, V> hashEntry = storage.allocateParticularHashEntry(hash);
            hashEntry.setKey(key);
            hashEntry.setValue(value);
            hashEntry.setPreviousIndex(NIL_HASH_ENTRY_INDEX);
            hashEntry.setNextIndex(NIL_HASH_ENTRY_INDEX);
        } else {
            HashEntry<K, V> hashEntry = storage.getHashEntry(hash);
            if (calculateHash(hashEntry.getKey()) == hash) {
                // Slot contains an entry with correct hash. We just need to add a new hash entry to the list.
                HashEntry<K, V> newHashEntry = storage.allocateHashEntry();

                // Move slot's root to the new location.
                moveHashEntry(hashEntry, newHashEntry);

                // Setup previous link to the slot's root.
                newHashEntry.setPreviousIndex(hashEntry.getIndex());

                // Setup new slot's root.
                hashEntry.setKey(key);
                hashEntry.setValue(value);
                hashEntry.setPreviousIndex(NIL_HASH_ENTRY_INDEX);
                hashEntry.setNextIndex(newHashEntry.getIndex());
            } else {
                // Slot contains an entry with incorrect hash. We should move that entry to another position and insert new root entry here.
                HashEntry<K, V> newHashEntry = storage.allocateHashEntry();

                // Move the element, which occupies the slot, to the new location.
                moveHashEntry(hashEntry, newHashEntry);

                // Insert new slot's root.
                hashEntry.setKey(key);
                hashEntry.setValue(value);
                hashEntry.setPreviousIndex(NIL_HASH_ENTRY_INDEX);
                hashEntry.setNextIndex(NIL_HASH_ENTRY_INDEX);
            }
        }
    }

    @Override
    public V search(K key) {
        Objects.requireNonNull(key, "key cannot be null");
        HashEntry<K, V> hashEntry = searchHashEntry(key);
        return hashEntry != null ? hashEntry.getValue() : null;
    }

    @Override
    public void delete(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        // Try to find a hash entry to delete.
        HashEntry<K, V> hashEntry = searchHashEntry(key);
        if (hashEntry == null) {
            throw new HashTableException(String.format("There is no item with a key '%s' in the hash table", key.toString()));
        }

        // Delete an entry.
        boolean inRoot = hashEntry.getPreviousIndex() == NIL_HASH_ENTRY_INDEX;
        if (inRoot) {
            boolean hasNext = hashEntry.getNextIndex() != NIL_HASH_ENTRY_INDEX;
            if (hasNext) {
                HashEntry<K, V> nextHashEntry = storage.getHashEntry(hashEntry.getNextIndex());

                hashEntry.setKey(nextHashEntry.getKey());
                hashEntry.setValue(nextHashEntry.getValue());
                hashEntry.setNextIndex(nextHashEntry.getNextIndex());
                if (nextHashEntry.getNextIndex() != NIL_HASH_ENTRY_INDEX) {
                    storage.getHashEntry(nextHashEntry.getNextIndex()).setPreviousIndex(hashEntry.getIndex());
                }

                storage.releaseHashEntry(nextHashEntry);
            } else {
                storage.releaseHashEntry(hashEntry);
            }
        } else {
            boolean hasNext = hashEntry.getNextIndex() != NIL_HASH_ENTRY_INDEX;
            if (hasNext) {
                HashEntry<K, V> nextHashEntry = storage.getHashEntry(hashEntry.getNextIndex());

                hashEntry.setKey(nextHashEntry.getKey());
                hashEntry.setValue(nextHashEntry.getValue());
                hashEntry.setNextIndex(nextHashEntry.getNextIndex());
                if (nextHashEntry.getNextIndex() != NIL_HASH_ENTRY_INDEX) {
                    storage.getHashEntry(nextHashEntry.getNextIndex()).setPreviousIndex(hashEntry.getIndex());
                }

                storage.releaseHashEntry(nextHashEntry);

            } else {
                storage.getHashEntry(hashEntry.getPreviousIndex()).setNextIndex(NIL_HASH_ENTRY_INDEX);
                storage.releaseHashEntry(hashEntry);
            }
        }
    }

    private void moveHashEntry(HashEntry<K, V> src, HashEntry<K, V> dest) {
        dest.setKey(src.getKey());
        dest.setValue(src.getValue());
        dest.setPreviousIndex(src.getPreviousIndex());
        dest.setNextIndex(src.getNextIndex());

        // TODO: Check the case when two entries are adjacent.

        if (src.getPreviousIndex() != NIL_HASH_ENTRY_INDEX) {
            storage.getHashEntry(src.getPreviousIndex()).setNextIndex(dest.getIndex());
        }
        if (src.getNextIndex() != NIL_HASH_ENTRY_INDEX) {
            storage.getHashEntry(src.getNextIndex()).setPreviousIndex(dest.getIndex());
        }
    }

    @Override
    public String toString() {
        return storage.toString();
    }

    private HashEntry<K, V> searchHashEntry(K key) {
        int hash = calculateHash(key);
        HashEntry<K, V> currentHashEntry = storage.getHashEntry(hash);
        if (currentHashEntry.isEmpty()) {
            return null;
        }
        while (true) {
            if (currentHashEntry.getKey().equals(key)) {
                return currentHashEntry;
            }
            if (currentHashEntry.getNextIndex() == NIL_HASH_ENTRY_INDEX) {
                return null;
            }
            currentHashEntry = storage.getHashEntry(currentHashEntry.getNextIndex());
        }
    }

    private int calculateHash(K key) {
        int hash = hashFunction.calculateHash(key);
        if (hash < 0 || hash >= hashTableSize) {
            throw new HashTableException("Hash function returned incorrect hash value: " + hash);
        }
        return hash;
    }

    private void ensureThereIsNoSuchKeyInTheTable(K key) {
        if (search(key) != null) {
            throw new HashTableException(String.format("An item with a key '%s' is already in the hash table", key.toString()));
        }
    }

}
