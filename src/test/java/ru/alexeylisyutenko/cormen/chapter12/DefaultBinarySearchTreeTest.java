package ru.alexeylisyutenko.cormen.chapter12;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultBinarySearchTreeTest {

    private DefaultBinarySearchTree<Integer> tree;

    @BeforeEach
    void setup() {
        tree = new DefaultBinarySearchTree<>();
    }

    @Test
    void demo() {
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

//        System.out.println(tree.contains(9));
//        System.out.println(tree.contains(13));
//        System.out.println(tree.contains(555));
//        System.out.println(tree.size());
//        System.out.println(tree.getMinimum());
//        System.out.println(tree.getMaximum());
//        System.out.println(tree.getSuccessorOf(13));
//        System.out.println(tree.getPredecessorOf(20));

        tree.delete(15);

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
    void sizeShouldWorkProperly() {
        assertEquals(0, tree.size());

        tree.insert(15);
        assertEquals(1, tree.size());
        tree.insert(6);
        assertEquals(2, tree.size());
        tree.insert(18);
        assertEquals(3, tree.size());

        tree.delete(6);
        assertEquals(2, tree.size());
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

        BinaryTreeException exception = assertThrows(BinaryTreeException.class, () -> tree.getSuccessorOf(555));
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

        BinaryTreeException exception = assertThrows(BinaryTreeException.class, () -> tree.getPredecessorOf(555));
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