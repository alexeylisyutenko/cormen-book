package ru.alexeylisyutenko.cormen.chapter14.josephuspermutation;

import ru.alexeylisyutenko.cormen.chapter14.DefaultOrderStatisticTree;
import ru.alexeylisyutenko.cormen.chapter14.OrderStatisticTree;

import java.util.ArrayList;

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
        for (int i = 1; i <= size; i++) {
            orderStatisticTree.insert(i);
        }

        // Thew whole loop runs in O(n log n).
        int[] permutation = new int[size];
        int rank = 1;
        for (int k = size; k > 0; k--) {
            rank = ((rank + step - 2) % k) + 1;
            int key = orderStatisticTree.selectOrderStatistic(rank);
            permutation[size - k] = key;
            orderStatisticTree.delete(key);
        }
        return permutation;
    }
}
