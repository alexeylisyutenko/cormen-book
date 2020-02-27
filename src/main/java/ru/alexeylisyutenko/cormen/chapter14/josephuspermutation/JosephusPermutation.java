package ru.alexeylisyutenko.cormen.chapter14.josephuspermutation;

/**
 * Interface for Josephus-permutation generation algorithms. Problem 14-2. Cormen.
 */
public interface JosephusPermutation {
    /**
     * Generates Josephus-permutation of defined size and with defined step.
     *
     * @param size size of a permutation returned
     * @param step
     * @return array of integers which contains Josephus-permutation
     */
    int[] generate(int size, int step);
}
