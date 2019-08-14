package ru.alexeylisyutenko.cormen.chapter12;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBinarySearchTreeTest {

    @Test
    void demo() {
        DefaultBinarySearchTree<Integer> tree = new DefaultBinarySearchTree<>();

        System.out.println(tree.contains(123));

        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(7);
        tree.insert(17);
        tree.insert(20);
        tree.insert(2);
        tree.insert(4);
        tree.insert(13);
        tree.insert(9);

        tree.print();

        System.out.println(tree.contains(123));

        System.out.println(tree.size());


    }

}