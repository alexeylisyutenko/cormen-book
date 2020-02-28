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

        // Populate a tree with numbers from 1 to size. Each insertion takes O(log n), therefore the whole insertion process takes O(n log n).
        OrderStatisticTree<Integer> orderStatisticTree = new DefaultOrderStatisticTree<>();
        for (int i = 1; i <= size; i++) {
            orderStatisticTree.insert(i);
        }

        int[] permutation = new int[size];
//        int key = orderStatisticTree.getIthSuccessorCircularOf(1, step - 1);
//        for (int i = 0; i < size; i++) {
//            permutation[i] = key;
//
//            // Save original rank
//            int rank = orderStatisticTree.findRank(key);
//
//            int nextRank = rank + step;
//            if (nextRank < orderStatisticTree.size())
//
////            int successorsRank = (((rank + step) - 1) % orderStatisticTree.size()) + 1;
//
//            orderStatisticTree.delete(key);
//
//            key = orderStatisticTree.selectOrderStatistic(successorsRank);
//        }
        return permutation;
    }
}
