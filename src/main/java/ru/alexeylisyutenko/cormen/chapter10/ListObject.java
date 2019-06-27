package ru.alexeylisyutenko.cormen.chapter10;

/**
 * Object representing element in a list structure.
 */
public interface ListObject {

    int getPointer();

    int getNext();

    void setNext(int next);

    int getKey();

    void setKey(int key);

    int getPrev();

    void setPrev(int prev);

}
