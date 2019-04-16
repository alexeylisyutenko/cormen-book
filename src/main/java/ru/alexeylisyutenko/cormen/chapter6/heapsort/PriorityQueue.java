package ru.alexeylisyutenko.cormen.chapter6.heapsort;

public interface PriorityQueue {

    int heapMaximum();

    int heapExtractMaximum();

    void heapIncreaseKey(int index, int key);

    void maxHeapInsert(int key);

}
