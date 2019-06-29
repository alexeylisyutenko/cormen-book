package ru.alexeylisyutenko.cormen.chapter10.storage;

import ru.alexeylisyutenko.cormen.chapter10.ListObject;

/**
 * Storage representing heap for allocating and freeing ListObjects.
 */
public interface ListObjectsStorage {

    /**
     * Allocates new {@link ListObject}.
     *
     * @return allocated {@link ListObject}
     */
    ListObject allocateObject();

    /**
     * Frees the storage occupied by {@link ListObject}.
     * Argument listObject must belong to this instance of a storage.
     *
     * @param listObject list object that is going to be freed.
     */
    void freeObject(ListObject listObject);

    /**
     * Checks weather or not a list object belongs to this storage.
     *
     * @param listObject list object we're checking
     * @return true - list object belongs to this storage, false - does not belong
     */
    boolean listObjectBelongsToStorage(ListObject listObject);

    /**
     * Get list object from the storage by its pointer.
     *
     * @param pointer pointer to the {@link ListObject} in the storage
     * @return list object with corresponding pointer
     */
    ListObject getByPointer(int pointer);

}
