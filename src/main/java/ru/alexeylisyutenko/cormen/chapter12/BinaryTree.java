package ru.alexeylisyutenko.cormen.chapter12;

import java.util.function.Consumer;

/**
 * Binary tree.
 *
 * @param <K> key type
 */
public interface BinaryTree<K extends Comparable<K>> {

    /**
     * Insert a node with corresponding key to the tree.
     *
     * @param key a key to insert
     */
    void insert(K key);

    /**
     * Delete a node with some key.
     *
     * @param key a key to delete
     * @throws BinaryTreeException if there is no such key in the tree
     */
    void delete(K key);

    /**
     * Inorder tree traversal.
     *
     * @param consumer action called for each key in the tree
     */
    void inorderWalk(Consumer<K> consumer);

    /**
     * Preorder tree traversal.
     *
     * @param consumer action called for each key in the tree
     */
    void preorderWalk(Consumer<K> consumer);

    /**
     * Postorder tree traversal.
     *
     * @param consumer an action called for each key in the tree
     */
    void postorderWalk(Consumer<K> consumer);

    /**
     * Returns the number of keys in the tree.
     *
     * @return number of keys in the tree
     */
    int size();

    /**
     * Checks if a node with such key exists in the tree.
     *
     * @param key a key to check
     * @return true - if such key exists, otherwise - false
     */
    boolean contains(K key);

    /**
     * Returns minimum key from the tree.
     *
     * @return minimum key if the tree is not empty, null otherwise
     */
    K getMinimum();

    /**
     * Returns maximum key from the tree.
     *
     * @return maximum key if the tree is not empty, null otherwise
     */
    K getMaximum();

    /**
     * Returns some key's successor.
     *
     * @param key a key whose successor will be returned
     * @return successor if key is not the maximum key, or if the key is the maximum key then null is returned
     * @throws BinaryTreeException if there is no such key in the tree
     */
    K getSuccessorOf(K key);

    /**
     * Returns some key's predecessor.
     *
     * @param key a key whose predecessor will be returned
     * @return predecessor if key is not the minimum key, or if the key is the minimum key then null is returned
     * @throws BinaryTreeException if there is no such key in the tree
     */
    K getPredecessorOf(K key);

    /**
     * Removes all keys from the tree.
     */
    void clear();

}
