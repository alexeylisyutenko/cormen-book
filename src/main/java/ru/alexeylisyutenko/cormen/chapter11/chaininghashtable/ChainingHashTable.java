package ru.alexeylisyutenko.cormen.chapter11.chaininghashtable;

import lombok.Getter;
import lombok.Setter;
import ru.alexeylisyutenko.cormen.chapter11.HashTable;
import ru.alexeylisyutenko.cormen.chapter11.HashTableException;
import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.HashFunctionFactory;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings("unchecked")
public class ChainingHashTable<K, V> implements HashTable<K, V> {

    private final HashFunction<K> hashFunction;
    private final int hashTableSize;
    private final HashEntry<K, V>[] table;

    public ChainingHashTable(HashFunctionFactory<K> hashFunctionFactory, int hashTableSize) {
        validateArguments(hashTableSize, hashFunctionFactory);
        this.hashTableSize = hashTableSize;
        this.hashFunction = hashFunctionFactory.create(hashTableSize);
        this.table = (HashEntry<K, V>[])new HashEntry[hashTableSize];
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

        // If a key already exists then exception should be thrown.
        ensureThereIsNoSuchKeyInTheTable(key);

        // Add a new entry to the table.
        int hash = calculateHash(key);
        HashEntry<K, V> hashEntry = new HashEntry<>(key, value);
        hashEntry.setPrevious(null);
        hashEntry.setNext(table[hash]);
        if (table[hash] != null) {
            table[hash].setPrevious(hashEntry);
        }
        table[hash] = hashEntry;
    }

    private void ensureThereIsNoSuchKeyInTheTable(K key) {
        if (search(key) != null) {
            throw new HashTableException(String.format("An item with a key '%s' is already in the hash table", key.toString()));
        }
    }

    @Override
    public V search(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        HashEntry<K, V> hashEntry = searchHashEntry(key);
        return hashEntry != null ? hashEntry.getValue() : null;
    }

    private HashEntry<K, V> searchHashEntry(K key) {
        int hash = calculateHash(key);
        HashEntry<K, V> currentHashEntry = table[hash];
        while (currentHashEntry != null) {
            if (currentHashEntry.getKey().equals(key)) {
                return currentHashEntry;
            }
            currentHashEntry = currentHashEntry.getNext();
        }
        return null;
    }

    private int calculateHash(K key) {
        int hash = hashFunction.calculateHash(key);
        if (hash < 0 || hash >= hashTableSize) {
            throw new HashTableException("Hash function returned incorrect hash value: " + hash);
        }
        return hash;
    }

    @Override
    public void delete(K key) {
        Objects.requireNonNull(key, "key cannot be null");

        // Try to find a hash entry to delete.
        HashEntry<K, V> hashEntry = searchHashEntry(key);
        if (hashEntry == null) {
            throw new HashTableException(String.format("There is no item with a key '%s' in the hash table", key.toString()));
        }

        // Delete a hash entry.
        if (hashEntry.getPrevious() == null) {
            int hash = calculateHash(key);
            table[hash] = hashEntry.getNext();
        } else {
            hashEntry.getPrevious().setNext(hashEntry.getNext());
        }
        if (hashEntry.getNext() != null) {
            hashEntry.getNext().setPrevious(hashEntry.getPrevious());
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }

    @Getter
    @Setter
    private static class HashEntry<K, V> {
        private K key;
        private V value;
        private HashEntry previous;
        private HashEntry next;

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "HashEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }
    }

}
