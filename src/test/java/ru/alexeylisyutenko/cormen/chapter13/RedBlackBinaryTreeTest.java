package ru.alexeylisyutenko.cormen.chapter13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class RedBlackBinaryTreeTest {

    private RedBlackBinarySearchTree<Integer> tree;

    @BeforeEach
    void setup() {
        tree = new RedBlackBinarySearchTree<>();
    }

    @Disabled
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

        System.out.println("Minimum: " + tree.getMinimum());
        System.out.println("Maximum: " + tree.getMaximum());

        System.out.println("Successor of 2: " + tree.getSuccessorOf(2));
        System.out.println("Successor of 4: " + tree.getSuccessorOf(4));
        System.out.println("Successor of 15: " + tree.getSuccessorOf(15));

        System.out.println("Predecessor of 2: " + tree.getPredecessorOf(2));
        System.out.println("Predecessor of 11: " + tree.getPredecessorOf(11));
        System.out.println("Predecessor of 1: " + tree.getPredecessorOf(1));
    }

    @Disabled
    @Test
    void demo2() {
        tree.insert(41);
        tree.insert(38);
        tree.insert(31);
        tree.insert(12);
        tree.insert(19);
        tree.insert(18);

        tree.print();
    }


    @Test
    void insertShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);

        assertTrue(tree.contains(15));
        assertTrue(tree.contains(6));
        assertTrue(tree.contains(18));
        assertTrue(tree.contains(3));
        assertFalse(tree.contains(555));
    }
    @Test
    void sizeShouldWorkProperly() {
        assertEquals(0, tree.size());
        tree.insert(15);
        assertEquals(1, tree.size());
        tree.insert(6);
        assertEquals(2, tree.size());
        tree.insert(18);
        assertEquals(3, tree.size());
    }

    @Test
    void getMinimumShouldWorkProperly() {
        assertNull(tree.getMinimum());

        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);

        assertEquals(Integer.valueOf(3), tree.getMinimum());
    }

    @Test
    void getMaximumShouldWorkProperly() {
        assertNull(tree.getMaximum());

        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);

        assertEquals(Integer.valueOf(18), tree.getMaximum());
    }

    @Test
    void getSuccessorOfShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(9);

        assertEquals(Integer.valueOf(6), tree.getSuccessorOf(3));
        assertEquals(Integer.valueOf(15), tree.getSuccessorOf(9));
        assertEquals(Integer.valueOf(18), tree.getSuccessorOf(15));
        assertNull(tree.getSuccessorOf(18));

        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.getSuccessorOf(555));
        assertEquals("There is no such key in the tree: 555", exception.getMessage());
    }


    @Test
    void getPredecessorOfShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(9);

        assertEquals(Integer.valueOf(3), tree.getPredecessorOf(6));
        assertEquals(Integer.valueOf(15), tree.getPredecessorOf(18));
        assertEquals(Integer.valueOf(6), tree.getPredecessorOf(9));
        assertNull(tree.getPredecessorOf(3));

        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.getPredecessorOf(555));
        assertEquals("There is no such key in the tree: 555", exception.getMessage());
    }

    @Test
    void inorderWalkShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(9);

        List<Integer> walkResult = new ArrayList<>();
        tree.inorderWalk(walkResult::add);
        assertArrayEquals(new Integer[]{3, 6, 9, 15, 18}, walkResult.toArray());
    }

    @Test
    void preorderWalkShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(9);

        List<Integer> walkResult = new ArrayList<>();
        tree.preorderWalk(walkResult::add);
        assertArrayEquals(new Integer[]{15, 6, 3, 9, 18}, walkResult.toArray());
    }

    @Test
    void postorderWalkShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(9);

        List<Integer> walkResult = new ArrayList<>();
        tree.postorderWalk(walkResult::add);
        assertArrayEquals(new Integer[]{3, 9, 6, 18, 15}, walkResult.toArray());
    }

}