package ru.alexeylisyutenko.datastructures.dynamicarray;

public interface DynamicArray<T> {

    void insertAtEnd(T value);

    T get(int index);

    void set(int index, T value);

    T remove(int index);

    int size();

    int capacity();

}
