package ru.alexeylisyutenko.cormen.chapter13.avltree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AVLBinarySearchTreeTest {

    private AVLBinarySearchTree<Integer> tree;

    @BeforeEach
    void setup() {
        tree = new AVLBinarySearchTree<>();
    }

    @Test
    void insertionDemo() {
        tree.insert(10);
        tree.insert(15);
        tree.insert(5);
//        tree.insert(3);
//        tree.insert(7);
//        tree.insert(1);
//        tree.insert(8);
//        tree.insert(17);
//        tree.insert(16);

        tree.print();
    }

    @Test
    void randomizedInsertionDemo() {
        List<Integer> integers = Helpers.generateRandomDistinctIntegers(20, 0, 20);
        integers.forEach(tree::insert);

        tree.print();
    }

}