package ru.alexeylisyutenko.cormen.chapter11.openaddressinghashtable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.alexeylisyutenko.cormen.chapter11.HashTable;
import ru.alexeylisyutenko.cormen.chapter11.HashTableException;
import ru.alexeylisyutenko.cormen.chapter11.probesequence.ProbeSequenceFunction;
import ru.alexeylisyutenko.cormen.chapter11.probesequencefactory.ProbeSequenceFunctionFactory;

import java.util.Arrays;
import java.util.Objects;

@SuppressWarnings({"unchecked"})
public class OpenAddressingHashTable<K, V> implements HashTable<K, V> {

    private final ProbeSequenceFunction<K> probeSequenceFunction;
    private final int hashTableSize;
    private final HashEntry<K, V>[] table;

    public OpenAddressingHashTable(ProbeSequenceFunctionFactory<K> probeSequenceFunctionFactory, int hashTableSize) {
        validateArguments(probeSequenceFunctionFactory, hashTableSize);
        this.probeSequenceFunction = probeSequenceFunctionFactory.create(hashTableSize);
        this.hashTableSize = hashTableSize;
        this.table = (HashEntry<K, V>[]) new HashEntry[hashTableSize];
    }

    private void validateArguments(ProbeSequenceFunctionFactory<K> probeSequenceFunctionFactory, int hashTableSize) {
        Objects.requireNonNull(probeSequenceFunctionFactory, "probeSequenceFunctionFactory cannot be null");
        if (hashTableSize < 1) {
            throw new IllegalArgumentException("hashTableSize must be positive");
        }
    }

    @Override
    public void insert(K key, V value) {
        int probeNumber = 0;
        int hash;
        do {
            hash = probeSequenceFunction.calculateHash(key, probeNumber);
            if (table[hash] == null || table[hash].isDeleted()) {
                if (table[hash] == null) {
                    table[hash] = new HashEntry<>();
                }
                table[hash].setKey(key);
                table[hash].setValue(value);
                table[hash].setDeleted(false);
                return;
            }
            probeNumber++;
        } while (table[hash] != null && probeNumber < hashTableSize);

        throw new HashTableException("There is no more space in the hash table");
    }

    @Override
    public V search(K key) {
        int probeNumber = 0;
        int hash;
        do {
            hash = probeSequenceFunction.calculateHash(key, probeNumber);
            if (table[hash] != null && !table[hash].isDeleted() && table[hash].getKey().equals(key)) {
                return table[hash].getValue();
            }
            probeNumber++;
        } while (table[hash] != null && probeNumber < hashTableSize);
        return null;
    }

    @Override
    public void delete(K key) {
        int probeNumber = 0;
        int hash;
        do {
            hash = probeSequenceFunction.calculateHash(key, probeNumber);
            if (table[hash] != null && table[hash].getKey().equals(key)) {
                table[hash].setDeleted(true);
                return;
            }
            probeNumber++;
        } while (table[hash] != null && probeNumber < hashTableSize);

        throw new HashTableException(String.format("There is no item with a key '%s' in the calculateHash table", key.toString()));
    }

    @Override
    public String toString() {
        return Arrays.toString(table);
    }

    @Getter
    @Setter
    @ToString
    private static class HashEntry<K, V> {
        private K key;
        private V value;
        private boolean deleted;
    }

}
