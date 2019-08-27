package ru.alexeylisyutenko.cormen.chapter13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RedBlackBinaryTreeTest {

    private RedBlackBinarySearchTree<Integer> tree;

    @BeforeEach
    void setup() {
        tree = new RedBlackBinarySearchTree<>();
    }

    @Test
    void demo() {
        tree.print();

        tree.insert(5);
        tree.insert(6);
        tree.insert(7);

        tree.print();
    }

}