package ru.alexeylisyutenko.cormen.chapter10.mergeableheap;

public interface MergeableHeap {

    /**
     * Insert a new element into the heap.
     *
     * @param key key of the new element
     */
    void insert(int key);

    /**
     * Returns key of a minimum element from the heap.
     *
     * @return key of a minimum element
     */
    int minimum();

    /**
     * Removes minimum element from the heap and returns its key.
     *
     * @return key of a minimum element
     */
    int extractMinimum();

    /**
     * Merges this heap with another heap and returns new merged heap.
     *
     * @param anotherHeap heap to merge this one with
     * @return new merged heap
     */
    MergeableHeap union(MergeableHeap anotherHeap);

    /**
     * Returns true if this heap is empty.
     *
     * @return true - this heap is empty, false - otherwise
     */
    boolean isEmpty();

}
