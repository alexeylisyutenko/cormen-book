package ru.alexeylisyutenko.cormen.chapter11.hashfunction;

/**
 * Hash function representation used to calculate hash values in hash tables.
 */
public interface HashFunction<K> {
    /**
     * Calculate hash value for some key.
     *
     * @param key key to calculate hash for
     * @return calculated hash value
     */
    int calculateHash(K key);
}
