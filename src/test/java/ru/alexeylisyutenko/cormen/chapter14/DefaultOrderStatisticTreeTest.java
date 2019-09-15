package ru.alexeylisyutenko.cormen.chapter14;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

class DefaultOrderStatisticTreeTest {

    private OrderStatisticTree<Integer> tree;

    @BeforeEach
    void setup() {
        tree = new DefaultOrderStatisticTree<>();
    }

    @Disabled
    @Test
    void insertionDemo() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);

        tree.print();
    }

    @Disabled
    @Test
    void deletionDemo() {
        for (int i = 1; i <= 10; i ++) {
            tree.insert(i);
        }
        tree.print();

        tree.delete(4);
        tree.print();
    }

    @Disabled
    @Test
    void selectOrderStatisticDemo() {
        tree.insert(41);
        tree.insert(38);
        tree.insert(31);
        tree.insert(12);
        tree.print();

        System.out.println(tree.selectOrderStatistic(2));
    }

    @Test
    void selectOrderStatisticShouldWorkProperly() {
        for (int i = 1; i <= 5; i ++) {
            tree.insert(i);
        }

        assertEquals(Integer.valueOf(1), tree.selectOrderStatistic(1));
        assertEquals(Integer.valueOf(2), tree.selectOrderStatistic(2));
        assertEquals(Integer.valueOf(3), tree.selectOrderStatistic(3));
        assertEquals(Integer.valueOf(4), tree.selectOrderStatistic(4));
        assertEquals(Integer.valueOf(5), tree.selectOrderStatistic(5));
    }

    @Test
    void selectOrderStatisticShouldThrowExceptionWhenRankIsLessThanOne() {
        for (int i = 1; i <= 5; i ++) {
            tree.insert(i);
        }

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> tree.selectOrderStatistic(0));
        assertEquals("rank cannot be less than 1", exception.getMessage());
    }

    @Test
    void selectOrderStatisticShouldThrowExceptionWhenThereIsNoSuchRank() {
        for (int i = 1; i <= 5; i ++) {
            tree.insert(i);
        }
        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.selectOrderStatistic(6));
        assertEquals("There is no node with rank '6' in the tree", exception.getMessage());
    }

    @Test
    void selectOrderStatisticShouldWorkProperlyOnEmptyTree() {
        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.selectOrderStatistic(1));
        assertEquals("There is no node with rank '1' in the tree", exception.getMessage());
    }

    @Test
    void selectOrderStatisticShouldWorkProperlyOnSingleNodeTree() {
        tree.insert(555);

        assertEquals(Integer.valueOf(555), tree.selectOrderStatistic(1));

        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.selectOrderStatistic(2));
        assertEquals("There is no node with rank '2' in the tree", exception.getMessage());
    }

    @Test
    void findRankShouldWorkProperly() {
        for (int i = 1; i <= 5; i ++) {
            tree.insert(i);
        }

        assertEquals(1, tree.findRank(1));
        assertEquals(2, tree.findRank(2));
        assertEquals(3, tree.findRank(3));
        assertEquals(4, tree.findRank(4));
        assertEquals(5, tree.findRank(5));
    }

    @Test
    void findRankShouldThrowExceptionWhenThereIsNoSuchKey() {
        for (int i = 1; i <= 5; i ++) {
            tree.insert(i);
        }

        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.findRank(6));
        assertEquals("There is no such key in the tree: 6", exception.getMessage());
    }

    @Test
    void findRankShouldWorkProperlyOnSingleElementTree() {
        tree.insert(555);

        assertEquals(1, tree.findRank(555));
    }

    @Disabled
    @Test
    void findIthSuccessorNodeDemo() {
        for (int i = 1; i <= 10; i ++) {
            tree.insert(i);
        }
        tree.print();

        System.out.println(tree.getIthSuccessorOf(2, 3));

    }

    @Test
    void getIthSuccessorOfShouldWorkProperly() {
        int testSize = 100;
        for (int i = 1; i <= testSize; i ++) {
            tree.insert(i);
        }

        for (int key = 1; key < testSize ; key++) {
            for (int successorIndex = 1; successorIndex <= testSize - key; successorIndex++) {
                int expectedValue = key + successorIndex;
                assertEquals(Integer.valueOf(expectedValue), tree.getIthSuccessorOf(key, successorIndex));
            }
        }
    }

    @Test
    void getIthSuccessorOfShouldThrowExceptionIfThereIsNoSuchNode() {
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);

        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.getIthSuccessorOf(2, 2));
        assertEquals("There is no such successor node. Key: '2', successorIndex: '2'.", exception.getMessage());
    }

}