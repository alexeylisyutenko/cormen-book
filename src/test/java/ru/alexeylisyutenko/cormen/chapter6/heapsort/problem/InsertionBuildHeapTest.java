package ru.alexeylisyutenko.cormen.chapter6.heapsort.problem;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter6.heapsort.Heap;
import ru.alexeylisyutenko.cormen.chapter6.heapsort.HeapArray;

import static org.junit.jupiter.api.Assertions.*;

class InsertionBuildHeapTest {

    @Test
    void compareTwoBuildHeapVersions() {
        HeapArray heapArray = new HeapArray(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        Heap.buildMaxHeap(heapArray);
        System.out.println(heapArray);

        HeapArray heapArray2 = new HeapArray(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        InsertionBuildHeap.buildMaxHeap2(heapArray2);
        System.out.println(heapArray2);
    }

}