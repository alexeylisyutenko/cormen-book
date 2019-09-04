package ru.alexeylisyutenko.cormen.chapter13;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.helper.Helpers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RedBlackTreeJoinAlgorithmTest {

    @Test
    void joinDemo() {
        RedBlackBinarySearchTree<Integer> tree1 = new RedBlackBinarySearchTree<>();
        tree1.insert(15);
        tree1.insert(6);
        tree1.insert(18);
        tree1.insert(3);
        tree1.insert(7);
        tree1.insert(21);
        tree1.insert(16);
        tree1.insert(2);
        tree1.insert(4);
        tree1.insert(13);
        tree1.insert(9);
        tree1.insert(17);

        System.out.println("Tree 1:");
        tree1.print();

        RedBlackBinarySearchTree<Integer> tree2 = new RedBlackBinarySearchTree<>();
        tree2.insert(30);
        tree2.insert(29);
        tree2.insert(31);

        System.out.println("Tree 2:");
        tree2.print();

        RedBlackBinarySearchTree<Integer> combinedTree = RedBlackTreeJoinAlgorithm.join(tree1, tree2, 25);
        System.out.println("Combined tree:");
        combinedTree.print();
    }

    @Test
    void randomizedDemo() {
        RedBlackBinarySearchTree<Integer> tree1 = new RedBlackBinarySearchTree<>();
        Helpers.generateRandomDistinctIntegers(3, 10, 30).forEach(tree1::insert);

        System.out.println("Tree 1:");
        tree1.print();
        System.out.println("Tree 1 black height: " + tree1.getBlackHeight());

        RedBlackBinarySearchTree<Integer> tree2 = new RedBlackBinarySearchTree<>();
        Helpers.generateRandomDistinctIntegers(10, 40, 60).forEach(tree2::insert);

        System.out.println();
        System.out.println("Tree 2:");
        tree2.print();
        System.out.println("Tree 2 black height: " + tree2.getBlackHeight());

        RedBlackBinarySearchTree<Integer> combinedTree = RedBlackTreeJoinAlgorithm.join(tree1, tree2, 35);

        System.out.println();
        System.out.println("Combined tree:");
        combinedTree.print();
        System.out.println("Combined tree black height: " + combinedTree.getBlackHeight());
    }

}