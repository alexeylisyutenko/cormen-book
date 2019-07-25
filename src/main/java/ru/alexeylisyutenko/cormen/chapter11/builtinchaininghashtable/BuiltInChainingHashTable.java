package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

import ru.alexeylisyutenko.cormen.chapter11.HashTable;
import ru.alexeylisyutenko.cormen.chapter11.hashfunction.HashFunction;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.HashFunctionFactory;

import java.util.Objects;

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

    }

    @Override
    public V search(K key) {
        return null;
    }

    @Override
    public void delete(K key) {

    }

}
