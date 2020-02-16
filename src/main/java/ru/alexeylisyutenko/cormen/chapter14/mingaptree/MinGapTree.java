package ru.alexeylisyutenko.cormen.chapter14.mingaptree;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;

/**
 * Exercise 14.3-6.
 */
public interface MinGapTree extends BinarySearchTree<Integer> {
    /**
     * Returns the magnitude of two closest numbers in this tree.
     * <br><br>
     * For example, if a tree contains numbers {1; 5; 9; 15; 18; 22}, then this method will return 3 = 18-15,
     * since 15 and 18 are the two closest numbers.
     *
     * @return the magnitude of two closest numbers in this tree
     */
    int minGap();
}
