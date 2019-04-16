package ru.alexeylisyutenko.cormen.chapter6.heapsort;

import lombok.ToString;
import ru.alexeylisyutenko.helper.Helpers;

@ToString
public class PriorityQueueImpl implements PriorityQueue {

    private final HeapArray heapArray;

    public PriorityQueueImpl() {
        this.heapArray = new HeapArray(new int[16]);
        this.heapArray.setHeapSize(0);
    }

    @Override
    public int heapMaximum() {
        if (heapArray.getHeapSize() < 1) {
            throw new IllegalStateException("Heap is empty");
        }
        return heapArray.get(1);
    }

    @Override
    public int heapExtractMaximum() {
        if (heapArray.getHeapSize() < 1) {
            throw new IllegalStateException("Heap is empty");
        }
        int maximum = heapArray.get(1);
        heapArray.set(1, heapArray.get(heapArray.getHeapSize()));
        heapArray.decHeapSize();
        Heap.maxHeapify(heapArray, 1);
        return maximum;
    }

    @Override
    public void heapIncreaseKey(int index, int key) {
        if (heapArray.get(index) > key) {
            throw new IllegalStateException(String.format("New key must be greater than current key value. Current value: %d.", heapArray.get(index)));
        }
        heapArray.set(index, key);
        while (index > 1 && heapArray.get(Heap.parent(index)) < key) {
            heapArray.exchange(Heap.parent(index), index);
            index = Heap.parent(index);
        }
    }

    @Override
    public void maxHeapInsert(int key) {
        heapArray.incHeapSize();
        heapArray.set(heapArray.getHeapSize(), Integer.MIN_VALUE);
        heapIncreaseKey(heapArray.getHeapSize(), key);
    }

}
