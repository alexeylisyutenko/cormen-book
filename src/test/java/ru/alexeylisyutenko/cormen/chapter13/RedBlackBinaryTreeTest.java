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
        tree.insert(1);
        tree.insert(2);
        tree.insert(4);
        tree.insert(5);
        tree.insert(7);
        tree.insert(8);
        tree.insert(11);
        tree.insert(14);
        tree.insert(15);

        tree.print();

        tree.inorderWalk(key -> System.out.print(key + " "));
        System.out.println();

        tree.preorderWalk(key -> System.out.print(key + " "));
        System.out.println();

        tree.postorderWalk(key -> System.out.print(key + " "));
        System.out.println();

        System.out.println("Size: " + tree.size());

        System.out.println("contains(1): " + tree.contains(1));
        System.out.println("contains(5): " + tree.contains(5));
        System.out.println("contains(15): " + tree.contains(15));
        System.out.println("contains(100): " + tree.contains(100));
        System.out.println("contains(0): " + tree.contains(0));
        System.out.println("contains(3): " + tree.contains(3));
    }

}