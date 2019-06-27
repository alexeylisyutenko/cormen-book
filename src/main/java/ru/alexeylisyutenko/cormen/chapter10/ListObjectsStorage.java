package ru.alexeylisyutenko.cormen.chapter10;

/**
 * Storage representing heap for allocating and freeing ListObjects.
 */
public interface ListObjectsStorage {

    ListObject allocateObject();

    void freeObject(ListObject listObject);

}
