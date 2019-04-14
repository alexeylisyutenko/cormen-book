package ru.alexeylisyutenko.cormen.chapter6.heapsort;

import lombok.ToString;

@ToString
public class HeapArray {

    private final int[] array;
    private int heapSize;

    public HeapArray(int[] array) {
        this.array = array;
        this.heapSize = array.length;
    }

    public int get(int index) {
        return array[index - 1];
    }

    public void set(int index, int value) {
        array[index - 1] = value;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        if (heapSize < 0 || heapSize > array.length) {
            throw new IllegalArgumentException("heapSize");
        }
        this.heapSize = heapSize;
    }

    public int getArraySize() {
        return array.length;
    }

    public int[] getArray() {
        return array;
    }

    public void exchange(int firstIndex, int secondIndex) {
        int temp = array[firstIndex - 1];
        array[firstIndex - 1] = array[secondIndex - 1];
        array[secondIndex - 1] = temp;
    }

}
