package ru.alexeylisyutenko.cormen.chapter14.mingaptree;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeException;

import java.util.Arrays;
import java.util.stream.IntStream;

import static org.apache.commons.lang3.math.NumberUtils.min;
import static org.junit.jupiter.api.Assertions.*;

class DefaultMinGapTreeTest {

    private MinGapTree tree;

    @BeforeEach
    void setup() {
        tree = new DefaultMinGapTree();
    }

    @Disabled
    @Test
    void demo() {
        tree.insert(1);
        tree.insert(5);
        tree.insert(9);
        tree.insert(15);
        tree.insert(18);
        tree.insert(22);

        tree.print();

        System.out.println("Minimum gap: " + tree.minGap());
    }

    @Disabled
    @Test
    void randomizedDemo() {
        int elements = 4;
        for (int i = 0; i < elements; i++) {
            tree.insert(RandomUtils.nextInt(0, 100));
        }
        tree.print();

        tree.inorderWalk(number -> System.out.print(number + " "));
        System.out.println();

        System.out.println("Minimum gap: " + tree.minGap());
        System.out.println();
    }

    @RepeatedTest(5000)
    void randomizedTest() {
        int elementsNumber = RandomUtils.nextInt(2, 1000);
        int[] elements = IntStream.generate(RandomUtils::nextInt).limit(elementsNumber).toArray();

        for (int element : elements) {
            tree.insert(element);
        }

        assertEquals(findMinGap(elements), tree.minGap());
    }

    @Test
    void minGapMustThrowExceptionWhenTreeIsEmpty() {
        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.minGap());
        assertEquals("Tree is empty", exception.getMessage());
    }

    @Test
    void minGapMustThrowExceptionWhenThereIsOnlyOneElement() {
        tree.insert(17);
        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.minGap());
        assertEquals("There is only one element in the tree. Minimum gap is not defined.", exception.getMessage());
    }

    @Test
    void deleteMustWorkProperly() {
        tree.insert(1);
        tree.insert(5);
        tree.insert(9);
        tree.insert(15);
        tree.insert(18);
        tree.insert(22);

        assertTrue(tree.contains(1));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(9));
        assertTrue(tree.contains(15));
        assertTrue(tree.contains(18));
        assertTrue(tree.contains(22));
        assertEquals(6, tree.size());
        assertEquals(3, tree.minGap());

        tree.delete(15);

        assertTrue(tree.contains(1));
        assertTrue(tree.contains(5));
        assertTrue(tree.contains(9));
        assertFalse(tree.contains(15));
        assertTrue(tree.contains(18));
        assertTrue(tree.contains(22));
        assertEquals(5, tree.size());
        assertEquals(4, tree.minGap());

        tree.delete(22);
        tree.delete(9);

        assertTrue(tree.contains(1));
        assertTrue(tree.contains(5));
        assertFalse(tree.contains(9));
        assertFalse(tree.contains(15));
        assertTrue(tree.contains(18));
        assertFalse(tree.contains(22));
        assertEquals(3, tree.size());
        assertEquals(4, tree.minGap());

        tree.delete(5);

        assertTrue(tree.contains(1));
        assertFalse(tree.contains(5));
        assertFalse(tree.contains(9));
        assertFalse(tree.contains(15));
        assertTrue(tree.contains(18));
        assertFalse(tree.contains(22));
        assertEquals(2, tree.size());
        assertEquals(17, tree.minGap());

        tree.delete(18);
        assertTrue(tree.contains(1));
        assertFalse(tree.contains(5));
        assertFalse(tree.contains(9));
        assertFalse(tree.contains(15));
        assertFalse(tree.contains(18));
        assertFalse(tree.contains(22));
        assertEquals(1, tree.size());
        BinarySearchTreeException exception = assertThrows(BinarySearchTreeException.class, () -> tree.minGap());
        assertEquals("There is only one element in the tree. Minimum gap is not defined.", exception.getMessage());
    }

    private int findMinGap(int[] array) {
        Arrays.sort(array);
        int minGap = Integer.MAX_VALUE;
        for (int i = 0; i < array.length - 1; i++) {
            minGap = min(minGap, array[i + 1] - array[i]);
        }
        return minGap;
    }

}