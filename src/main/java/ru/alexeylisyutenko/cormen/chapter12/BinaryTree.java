package ru.alexeylisyutenko.cormen.chapter12;

import java.util.function.Consumer;

/**
 * Binary tree.
 *
 * @param <K> key type
 */
public interface BinaryTree<K extends Comparable> {

    /**
     *
     * @param consumer
     */
    void inorderWalk(Consumer<K> consumer);

    /**
     *
     * @param consumer
     */
    void preorderWalk(Consumer<K> consumer);

    /**
     *
     * @param consumer
     */
    void postorderWalk(Consumer<K> consumer);

    void insert(K key);

    void delete(K key);

    boolean contains(K key);

    K getMinimum();

    K getMaximum();

    K getSuccessorOf(K key);

    K getPredecessorOf(K key);

}
