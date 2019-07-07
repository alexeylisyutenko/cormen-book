package ru.alexeylisyutenko.cormen.chapter10.mergeableheap;

import ru.alexeylisyutenko.cormen.chapter10.ListObject;
import ru.alexeylisyutenko.cormen.chapter10.list.DoublyLinkedSimpleList;
import ru.alexeylisyutenko.cormen.chapter10.list.SimpleList;
import ru.alexeylisyutenko.cormen.chapter10.storage.ListObjectsStorage;
import ru.alexeylisyutenko.cormen.chapter10.storage.MultipleArrayListObjectsStorage;

public class SortedListMergeableHeap implements MergeableHeap {

    private final ListObjectsStorage storage;
    private final SimpleList list;

    public SortedListMergeableHeap(int storageSize) {
        this.storage = new MultipleArrayListObjectsStorage(storageSize);
        this.list = new DoublyLinkedSimpleList(storage);
    }

    @Override
    public void insert(int key) {
        // Find appropriate heap position to insert the key.
        ListObject current = list.getObjectByPointer(list.getNil().getNext());
        while (current != list.getNil() &&  current.getKey() <= key) {
            current = list.getObjectByPointer(current.getNext());
        }

        // Insert the key to the position pointed by current ListObject.s
        ListObject newListObject = storage.allocateObject();
        newListObject.setKey(key);
        insertListObjectBefore(newListObject, current);
    }

    private void insertListObjectBefore(ListObject listObjectToInsert, ListObject insertBeforeListObject) {
        ListObject previous = list.getObjectByPointer(insertBeforeListObject.getPrev());

        listObjectToInsert.setPrev(previous.getPointer());
        listObjectToInsert.setNext(insertBeforeListObject.getPointer());

        previous.setNext(listObjectToInsert.getPointer());
        insertBeforeListObject.setPrev(listObjectToInsert.getPointer());
    }

    @Override
    public int minimum() {
        ensureHeapIsNotEmpty();
        ListObject firstListObject = list.getObjectByPointer(list.getNil().getNext());
        return firstListObject.getKey();
    }

    private void ensureHeapIsNotEmpty() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
    }

    @Override
    public int extractMinimum() {
        ensureHeapIsNotEmpty();
        ListObject firstListObject = list.getObjectByPointer(list.getNil().getNext());
        int minimumKey = firstListObject.getKey();
        list.delete(firstListObject);
        list.getStorage().freeObject(firstListObject);
        return minimumKey;
    }

    @Override
    public MergeableHeap union(MergeableHeap anotherHeap) {
        SortedListMergeableHeap mergedHeap = new SortedListMergeableHeap(storage.getSize() * 2);
        SimpleList mergedHeapList = mergedHeap.list;
        ListObject mergedHeapTail = mergedHeapList.getNil();

        MergeableHeap firstHeap = this;
        MergeableHeap secondHeap = anotherHeap;

        while (!firstHeap.isEmpty() || !secondHeap.isEmpty()) {
            int firstHeapMinimum = firstHeap.isEmpty() ? Integer.MAX_VALUE : firstHeap.minimum();
            int secondHeapMinimum = secondHeap.isEmpty() ? Integer.MAX_VALUE : secondHeap.minimum();

            // Create next ListObject for the merged heap.
            ListObject next = mergedHeap.list.getStorage().allocateObject();
            next.setPrev(mergedHeapTail.getPointer());
            next.setNext(mergedHeapList.getNil().getPointer());
            mergedHeapTail.setNext(next.getPointer());

            if (firstHeapMinimum < secondHeapMinimum) {
                // Insert key from the first heap.
                next.setKey(firstHeap.extractMinimum());
            } else {
                // Insert key from the second heap.
                next.setKey(secondHeap.extractMinimum());
            }

            mergedHeapTail = next;
        }

        return mergedHeap;
    }

    @Override
    public boolean isEmpty() {
        return list.getHead() == null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');
        list.iterate(listObject -> stringBuilder.append(listObject.getKey()).append(", "));

        String result = stringBuilder.toString();
        if (result.endsWith(", ")) {
            result = result.substring(0, result.length() - 2);
        }
        result = result + "]";

        return result;
    }

}
