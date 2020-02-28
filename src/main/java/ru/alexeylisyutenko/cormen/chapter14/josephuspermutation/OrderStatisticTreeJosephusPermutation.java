package ru.alexeylisyutenko.cormen.chapter14.josephuspermutation;

import ru.alexeylisyutenko.cormen.chapter14.DefaultOrderStatisticTree;
import ru.alexeylisyutenko.cormen.chapter14.OrderStatisticTree;

public class OrderStatisticTreeJosephusPermutation implements JosephusPermutation {
    @Override
    public int[] generate(int size, int step) {
        if (size < 0) {
            throw new IllegalArgumentException("size must not be negative");
        }
        if (step <= 0) {
            throw new IllegalArgumentException("size must be greater than zero");
        }

        // Populate a tree with numbers from 0 to size.
        // Each insertion takes O(log n), therefore the whole insertion process takes O(n log n).
        OrderStatisticTree<Integer> orderStatisticTree = new DefaultOrderStatisticTree<>();
        for (int i = 0; i <= size; i++) {
            orderStatisticTree.insert(i);
        }

        // Thew whole loop runs in O(n log n).
        int[] permutation = new int[size];
        int key = 0;
        for (int i = 0; i < size; i++) {
            // Find circular predecessor for the current key. O(log n).
            int predecessorKey = getPredecessorCircularOf(orderStatisticTree, key);

            // Delete current key from the tree. O(log n).
            orderStatisticTree.delete(key);

            // Find next key. O(log n)
            key = orderStatisticTree.getIthSuccessorCircularOf(predecessorKey, step);

            // Save the current key to the resulting array.
            permutation[i] = key;
        }
        return permutation;
    }

    private int getPredecessorCircularOf(OrderStatisticTree<Integer> orderStatisticTree, int key) {
        int rank = orderStatisticTree.findRank(key);
        if (rank == 1) {
            return orderStatisticTree.getMaximum();
        } else {
            return orderStatisticTree.selectOrderStatistic(rank - 1);
        }
    }
}
