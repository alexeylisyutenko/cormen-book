package ru.alexeylisyutenko.cormen.chapter11.probesequence;

/**
 * Extended calculateHash function which includes probe number.
 */
public interface ProbeSequenceFunction<K> {
    /**
     * Calculate calculateHash value for some key and probe number.
     *
     * @param key key to calculate calculateHash for
     * @param probeNumber number of the current probe
     * @return calculated calculateHash
     */
    int calculateHash(K key, int probeNumber);
}
