package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

interface HashEntryStorage<K, V> {
    HashEntry<K, V> getHashEntry(int index);

    HashEntry<K, V> allocateHashEntry();

    void releaseHashEntry(HashEntry<K, V> hashEntry);

    int getSize();
}
