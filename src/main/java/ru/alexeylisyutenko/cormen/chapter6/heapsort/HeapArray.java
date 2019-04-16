package ru.alexeylisyutenko.cormen.chapter6.heapsort;

import lombok.ToString;

import java.util.Arrays;

@ToString
public class HeapArray {

    private int[] array;
    private int heapSize;

    public HeapArray(int[] array) {
        this.array = array;
        this.heapSize = array.length;
    }

    public int get(int index) {
        ensureIndexValidity(index);
        return array[index - 1];
    }

    private void ensureIndexValidity(int index) {
        if (index < 1 || index > heapSize) {
            throw new IllegalArgumentException("Incorrect index");
        }
    }

    public void set(int index, int value) {
        ensureIndexValidity(index);
        array[index - 1] = value;
    }

    public int getHeapSize() {
        return heapSize;
    }

    public void setHeapSize(int heapSize) {
        if (heapSize < 0) {
            throw new IllegalArgumentException("heapSize");
        }
        if (heapSize > array.length) {
            array = Arrays.copyOf(array, this.heapSize * 2);
        }
        this.heapSize = heapSize;
    }

    public void incHeapSize() {
        setHeapSize(heapSize + 1);
    }

    public void decHeapSize() {
        setHeapSize(heapSize - 1);
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
