package ru.alexeylisyutenko.cormen.chapter13.treap;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.utils.BinaryTreePrinter;

import java.util.function.Consumer;

public class Treap<K extends Comparable<K>> implements BinarySearchTree<K> {
    private TreapNode<K> root;

    @Override
    public void insert(K key) {

    }

    @Override
    public void delete(K key) {

    }

    @Override
    public void inorderWalk(Consumer<K> consumer) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public void preorderWalk(Consumer<K> consumer) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public void postorderWalk(Consumer<K> consumer) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public int size() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public boolean contains(K key) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public K getMinimum() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public K getMaximum() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public K getSuccessorOf(K key) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public K getPredecessorOf(K key) {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public void clear() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    public void print() {
        BinaryTreePrinter.printNode(root);
    }
}
