package ru.alexeylisyutenko.cormen.chapter6;

@SuppressWarnings("ALL")
public final class InsertionBuildHeap {

    public static void buildMaxHeap2(HeapArray heapArray) {
        int heapArraySize = heapArray.getArraySize();
        heapArray.setHeapSize(1);
        for (int i = 2; i <= heapArraySize; i++) {
            maxHeapInsert(heapArray, heapArray.get(i));
        }
    }

    private static void heapIncreaseKey(HeapArray heapArray, int index, int key) {
        if (heapArray.get(index) > key) {
            throw new IllegalStateException(String.format("New key must be greater than current key value. Current value: %d.", heapArray.get(index)));
        }
        heapArray.set(index, key);
        while (index > 1 && heapArray.get(HeapSort.parent(index)) < key) {
            heapArray.exchange(HeapSort.parent(index), index);
            index = HeapSort.parent(index);
        }
    }

    private static void maxHeapInsert(HeapArray heapArray,int key) {
        heapArray.incHeapSize();
        heapArray.set(heapArray.getHeapSize(), Integer.MIN_VALUE);
        heapIncreaseKey(heapArray, heapArray.getHeapSize(), key);
    }

}
