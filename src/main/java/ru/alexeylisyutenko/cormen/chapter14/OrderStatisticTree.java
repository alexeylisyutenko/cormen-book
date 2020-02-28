package ru.alexeylisyutenko.cormen.chapter14;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;

public interface OrderStatisticTree<K extends Comparable<K>> extends BinarySearchTree<K> {
    /**
     * Returns a key with a given rank.
     *
     * @param rank rank of a key we're looking for
     * @return key of a node with a given rank
     * @throws BinarySearchTreeException if there is no node with such rank
     */
    K selectOrderStatistic(int rank);

    /**
     * Find a rank of a given key within the tree.
     *
     * @param key a key whose rank we're looking for
     * @return rank of a given key
     * @throws BinarySearchTreeException if there is no node with such key
     */
    int findRank(K key);

    /**
     * Returns ith successor of a node with particular key.
     *
     * @param key a key whose ith successor will be returned
     * @param successorIndex index of successor
     * @return ith successor of a node with a given key
     * @throws BinarySearchTreeException if there is no such key in the tree, or if there is no node with such successor index
     */
    K getIthSuccessorOf(K key, int successorIndex);

    /**
     * This method return number of keys in the tree that are greater than some particular key.
     *
     * @param key a key for which we are looking greater keys
     * @return number of keys in the tree that are greater than particular key
     */
    int countGreaterKeys(K key);

    // TODO: Add javadoc and test.
    K getIthSuccessorCircularOf(K key, int successorIndex);
}
