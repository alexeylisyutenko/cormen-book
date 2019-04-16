package ru.alexeylisyutenko.cormen.chapter6.heapsort;

public class Heap {

    public static void sort(int[] array) {
        HeapArray heapArray = new HeapArray(array);
        heapSort(heapArray);
    }

    public static void maxHeapify(HeapArray heapArray, int index) {
        while (true) {
            int left = left(index);
            int right = right(index);
            int largest;
            if (left <= heapArray.getHeapSize() && heapArray.get(left) > heapArray.get(index)) {
                largest = left;
            } else {
                largest = index;
            }
            if (right <= heapArray.getHeapSize() && heapArray.get(right) > heapArray.get(largest)) {
                largest = right;
            }
            if (largest == index) {
                return;
            }
            heapArray.exchange(index, largest);
            index = largest;
        }
    }

    public static void buildMaxHeap(HeapArray heapArray) {
        for (int i = heapArray.getHeapSize() / 2; i >= 1; i--) {
            maxHeapify(heapArray, i);
        }
    }

    public static void heapSort(HeapArray heapArray) {
        buildMaxHeap(heapArray);
        for (int i = heapArray.getHeapSize(); i >= 2; i--) {
            heapArray.exchange(1, i);
            heapArray.setHeapSize(heapArray.getHeapSize() - 1);
            maxHeapify(heapArray, 1);
        }
    }

    public static int left(int index) {
        return 2 * index;
    }

    public static int right(int index) {
        return 2 * index + 1;
    }

    public static int parent(int index) {
        return index / 2;
    }

}
