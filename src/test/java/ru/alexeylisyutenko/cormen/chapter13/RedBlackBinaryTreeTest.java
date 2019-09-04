package ru.alexeylisyutenko.cormen.chapter13;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.alexeylisyutenko.helper.Helpers.generateRandomDistinctIntegers;

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
    void deletionDemo() {
        for (int i = 1; i < 16; i += 2) {
            tree.insert(i);
            tree.insert(i + 1);
        }
        tree.print();

        tree.delete(4);
        tree.print();
    }

    // Cormen exercise 13.4-3.
    @Test
    void insertionAndDeletionDemo() {
        tree.insert(41);
        tree.insert(38);
        tree.insert(31);
        tree.insert(12);
        tree.insert(19);
        tree.insert(8);

        tree.delete(8);
        tree.print();

        tree.delete(12);
        tree.print();

        tree.delete(19);
        tree.print();

        tree.delete(31);
        tree.print();

        tree.delete(38);
        tree.print();

        tree.delete(41);
        tree.print();
    }

    @Test
    void oneMoreDemo() {
        tree.insert(5);
        tree.insert(4);
        tree.insert(3);
        tree.insert(2);
//        tree.insert(1);
//        tree.insert(0);
        tree.print();
        System.out.println("Black height: " + tree.getBlackHeight());

        tree.delete(5);
        tree.print();
        System.out.println("Black height: " + tree.getBlackHeight());

        tree.delete(4);
        tree.print();
        System.out.println("Black height: " + tree.getBlackHeight());
    }

    @Test
    @RepeatedTest(1000)
    void randomizedBlackHeightTest() {
        int size = RandomUtils.nextInt(1, 1000);
        List<Integer> integers = generateRandomDistinctIntegers(size);
        integers.forEach(tree::insert);
        integers.forEach(tree::delete);

        assertEquals(0, tree.getBlackHeight());
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
    void deleteShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(7);
        tree.insert(21);
        tree.insert(16);
        tree.insert(2);
        tree.insert(4);
        tree.insert(13);
        tree.insert(9);
        tree.insert(17);

        assertTrue(tree.contains(2));
        tree.delete(2);
        assertFalse(tree.contains(2));

        assertTrue(tree.contains(13));
        tree.delete(13);
        assertFalse(tree.contains(13));

        assertTrue(tree.contains(15));
        tree.delete(15);
        assertFalse(tree.contains(15));

        assertTrue(tree.contains(3));
        assertTrue(tree.contains(4));
        assertTrue(tree.contains(6));
        assertTrue(tree.contains(7));
        assertTrue(tree.contains(9));
        assertTrue(tree.contains(18));
        assertTrue(tree.contains(16));
        assertTrue(tree.contains(17));
        assertTrue(tree.contains(21));
    }

    @Test
    void specialCaseDeletionShouldWorkProperly() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(5);
        tree.insert(7);
        tree.insert(8);

        System.out.println("After insertion:");
        tree.print();

        tree.delete(1);
        System.out.println("After deletion of 1:");
        tree.print();

        tree.delete(7);
        System.out.println("After deletion of 7:");
        tree.print();

        tree.delete(8);

        tree.print();
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

    @Test
    @RepeatedTest(1000)
    void randomizedInsertionAndDeletionTest() {
        int size = RandomUtils.nextInt(1, 2000);
        singleRandomizedTest(size);
    }

    private void singleRandomizedTest(int size) {
        // Prepare lists.
        List<Integer> integers = generateRandomDistinctIntegers(size, 1, 2 * size);
        List<Integer> integersToDelete = new ArrayList<>();
        List<Integer> integersToLeave = new ArrayList<>();
        for (Integer integer : integers) {
            if (RandomUtils.nextBoolean()) {
                integersToDelete.add(integer);
            } else {
                integersToLeave.add(integer);
            }
        }

        // Add to integers to the tree.
        integers.forEach(tree::insert);

        // Check that all integers.
        for (Integer integer : integers) {
            assertTrue(tree.contains(integer));
        }

        // Delete integers.
        for (Integer integer : integersToDelete) {
            tree.delete(integer);
        }

        // Check that there are no deleted integers.
        for (Integer integer : integersToDelete) {
            assertFalse(tree.contains(integer));
        }

        // Check there are left integers.
        for (Integer integer : integersToLeave) {
            assertTrue(tree.contains(integer));
        }
    }

    @Disabled
    @Test
    void findMaxBlackNodeDemo() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(7);
        tree.insert(21);
        tree.insert(16);
        tree.insert(2);
        tree.insert(4);
        tree.insert(13);
        tree.insert(9);
        tree.insert(17);

        tree.print();
        System.out.println("Black height: " + tree.getBlackHeight());

        RedBlackSearchTreeNode<Integer> maxBlackNode = tree.findMaxBlackNode(2);
        System.out.println("Max black node: " + maxBlackNode);
    }

    @Test
    void findMaxBlackNodeShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(7);
        tree.insert(21);
        tree.insert(16);
        tree.insert(2);
        tree.insert(4);
        tree.insert(13);
        tree.insert(9);
        tree.insert(17);

        assertEquals(Integer.valueOf(15), tree.findMaxBlackNode(2).getKey());
        assertEquals(Integer.valueOf(21), tree.findMaxBlackNode(1).getKey());

        assertThrows(BinarySearchTreeException.class, () -> tree.findMaxBlackNode(0));

        tree.clear();

        assertThrows(BinarySearchTreeException.class, () -> tree.findMaxBlackNode(1));
        assertThrows(BinarySearchTreeException.class, () -> tree.findMaxBlackNode(0));

        tree.insert(5);

        assertEquals(Integer.valueOf(5), tree.findMaxBlackNode(1).getKey());
    }

    @Test
    void findMinBlackNodeShouldWorkProperly() {
        tree.insert(15);
        tree.insert(6);
        tree.insert(18);
        tree.insert(3);
        tree.insert(7);
        tree.insert(21);
        tree.insert(16);
        tree.insert(2);
        tree.insert(4);
        tree.insert(13);
        tree.insert(9);
        tree.insert(17);

        assertEquals(Integer.valueOf(15), tree.findMinBlackNode(2).getKey());
        assertEquals(Integer.valueOf(3), tree.findMinBlackNode(1).getKey());

        assertThrows(BinarySearchTreeException.class, () -> tree.findMinBlackNode(0));

        tree.clear();

        assertThrows(BinarySearchTreeException.class, () -> tree.findMinBlackNode(1));
        assertThrows(BinarySearchTreeException.class, () -> tree.findMinBlackNode(0));

        tree.insert(5);

        assertEquals(Integer.valueOf(5), tree.findMinBlackNode(1).getKey());
    }

}