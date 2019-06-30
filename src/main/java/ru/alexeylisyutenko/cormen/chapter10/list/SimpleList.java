package ru.alexeylisyutenko.cormen.chapter10.list;

import ru.alexeylisyutenko.cormen.chapter10.ListObject;
import ru.alexeylisyutenko.cormen.chapter10.storage.ListObjectsStorage;

import java.util.function.Consumer;

/**
 * Interface that represents list.
 */
public interface SimpleList {

    /**
     * Search an object by its key.
     *
     * @param key key to search
     * @return object with the corresponding key if such object exists, or null if there is no such object
     */
    ListObject search(int key);

    /**
     * Inserts new object into the list. This method inserts the new list object into the front of the list.
     * Object must be allocated by appropriate {@link ListObjectsStorage}.
     *
     * @param listObject list object to insert
     */
    void insert(ListObject listObject);

    /**
     * Deletes an object from the list. ListObject's storage is not released automatically.
     *
     * @param listObject list object to delete
     */
    void delete(ListObject listObject);

    /**
     * Returns storage used by this list.
     *
     * @return storage used by this list
     */
    ListObjectsStorage getStorage();

    /**
     * Iterate over all list objects.
     *
     * @param action action performed with each list object
     */
    void iterate(Consumer<ListObject> action);

    /**
     * Returns the head element in the list.
     *
     * @return head element in the list or null if the list is empty
     */
    ListObject getHead();

    /**
     * Compacts the storage use by list objects.
     */
    void compatify();
}
