package ru.alexeylisyutenko.cormen.chapter14.josephuspermutation;

import com.google.common.collect.Iterables;

import java.util.Iterator;
import java.util.LinkedList;

public class CircularLinkedListJosephusPermutation implements JosephusPermutation {
    @Override
    public int[] generate(int size, int step) {
        if (size < 0) {
            throw new IllegalArgumentException("size must not be negative");
        }
        if (step <= 0) {
            throw new IllegalArgumentException("size must be greater than zero");
        }

        // Populate linked list with numbers from 1 to size.
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 1; i <= size; i++) {
            linkedList.add(i);
        }

        // Iterate over a list and save resulting permutation in array.
        int[] permutation = new int[size];
        Iterator<Integer> circularIterator = Iterables.cycle(linkedList).iterator();
        for (int i = 0; i < size; i++) {
            int currentNumber = Integer.MIN_VALUE;
            for (int j = 0; j < step; j++) {
                currentNumber = circularIterator.next();
            }
            circularIterator.remove();
            permutation[i] = currentNumber;
        }

        return permutation;
    }

}
