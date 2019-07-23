package ru.alexeylisyutenko.cormen.chapter11;

/**
 * Hash table.
 */
public interface HashTable<K, V> {

    /**
     * Insert new entry into the hash table.
     * <p>
     * If there is an entry with such key in the table {@link HashTableException} will bi thrown.
     * </p>
     *
     * @param key entry key
     * @param value entry value
     * @throws HashTableException if there is an entry with such key
     */
    void insert(K key, V value);

    /**
     * Search an entry in the table by key.
     *
     * @param key a key to search
     * @return if an entry with such key exists a value associated with corresponding key is returned, otherwise null
     */
    V search(K key);

    /**
     * Deletes an entry from the table.
     *
     * @param key key of an entry to delete
     * @throws HashTableException if there is an entry with such key
     */
    void delete(K key);

}
