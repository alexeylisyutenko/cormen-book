package ru.alexeylisyutenko.other.dynamicarray;

public class DynamicArrayImpl<T> implements DynamicArray<T> {

    private static final int INITIAL_CAPACITY = 16;
    private static final int GROW_FACTOR = 2;

    private Object[] data;
    private int size;

    public DynamicArrayImpl() {
        this.data = new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @Override
    public void insertAtEnd(T value) {
        if (size == capacity()) {
            grow();
        }
        data[size++] = value;
    }

    private void grow() {
        Object[] newData = new Object[capacity() * GROW_FACTOR];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public int capacity() {
        return data.length;
    }

    @Override
    public T get(int index) {
        validateIndex(index);
        return (T) data[index];
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void set(int index, T value) {
        validateIndex(index);
        data[index] = value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T remove(int index) {
        validateIndex(index);
        Object valueToRemove = data[index];

        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        return (T) valueToRemove;
    }
}
