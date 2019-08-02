package ru.alexeylisyutenko.cormen.chapter11.hashfunction;

/**
 * Hash function representation used to calculate calculateHash values in calculateHash tables.
 */
public interface HashFunction<K> {
    /**
     * Calculate calculateHash value for some key.
     *
     * @param key key to calculate calculateHash for
     * @return calculated calculateHash value
     */
    int calculateHash(K key);
}
