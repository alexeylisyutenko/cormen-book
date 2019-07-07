package ru.alexeylisyutenko.cormen.chapter10.mergeableheap;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SortedListMergeableHeapTest {

    @Test
    void sortedListMergeableHeapDemo() {
        SortedListMergeableHeap mergeableHeap = new SortedListMergeableHeap(20);

        // Insert some elements into the heap.
        System.out.println("Insert new keys");

        mergeableHeap.insert(2);
        System.out.println(mergeableHeap);

        mergeableHeap.insert(4);
        System.out.println(mergeableHeap);

        mergeableHeap.insert(6);
        System.out.println(mergeableHeap);

        mergeableHeap.insert(5);
        System.out.println(mergeableHeap);

        mergeableHeap.insert(1);
        System.out.println(mergeableHeap);

        mergeableHeap.insert(3);
        System.out.println(mergeableHeap);

        System.out.println();

        // Get minimum key.
        System.out.println("Get minimum key");
        System.out.println(mergeableHeap.minimum());
        System.out.println(mergeableHeap);
        System.out.println();

        // Extract minimum key.
        System.out.println("Extract minimum key");
        System.out.println(mergeableHeap.extractMinimum());
        System.out.println(mergeableHeap.extractMinimum());
        System.out.println(mergeableHeap.extractMinimum());
        System.out.println(mergeableHeap.extractMinimum());
        System.out.println(mergeableHeap);
        System.out.println();
    }

    @Test
    void unionTwoHeapsDemo() {
        // Prepare first mergeable heap.
        SortedListMergeableHeap firstMergeableHeap = new SortedListMergeableHeap(20);
        firstMergeableHeap.insert(2);
        firstMergeableHeap.insert(4);
        firstMergeableHeap.insert(6);
        firstMergeableHeap.insert(8);
        firstMergeableHeap.insert(12);
        firstMergeableHeap.insert(15);
        firstMergeableHeap.insert(20);

        System.out.println(firstMergeableHeap);

        // Prepare second mergeable heap.
        SortedListMergeableHeap secondMergeableHeap = new SortedListMergeableHeap(20);
        secondMergeableHeap.insert(1);
        secondMergeableHeap.insert(7);
        secondMergeableHeap.insert(3);
        secondMergeableHeap.insert(9);
        secondMergeableHeap.insert(10);
        secondMergeableHeap.insert(16);
        secondMergeableHeap.insert(18);

        System.out.println(secondMergeableHeap);

        // Merge two heaps.
        MergeableHeap mergedHeap = firstMergeableHeap.union(secondMergeableHeap);
        System.out.println(mergedHeap);
    }

}