package ru.alexeylisyutenko.cormen.chapter6;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter6.HeapSort;
import ru.alexeylisyutenko.cormen.chapter6.HeapArray;

class InsertionBuildHeapSortTest {

    @Test
    void compareTwoBuildHeapVersions() {
        HeapArray heapArray = new HeapArray(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        HeapSort.buildMaxHeap(heapArray);
        System.out.println(heapArray);

        HeapArray heapArray2 = new HeapArray(new int[]{4, 1, 3, 2, 16, 9, 10, 14, 8, 7});
        InsertionBuildHeap.buildMaxHeap2(heapArray2);
        System.out.println(heapArray2);
    }

}