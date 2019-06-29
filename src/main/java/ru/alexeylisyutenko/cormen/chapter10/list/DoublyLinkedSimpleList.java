package ru.alexeylisyutenko.cormen.chapter10.list;

import lombok.ToString;
import ru.alexeylisyutenko.cormen.chapter10.DefaultListObject;
import ru.alexeylisyutenko.cormen.chapter10.ListObject;
import ru.alexeylisyutenko.cormen.chapter10.storage.ListObjectsStorage;

import static ru.alexeylisyutenko.cormen.chapter10.ListConstants.NIL;

@ToString
public class DoublyLinkedSimpleList implements SimpleList {

    private final ListObjectsStorage storage;
    private final ListObject nil;

    public DoublyLinkedSimpleList(ListObjectsStorage storage) {
        this.storage = storage;
        this.nil = new DefaultListObject(NIL);
        nil.setKey(NIL);
        nil.setNext(NIL);
        nil.setPrev(NIL);
    }

    private ListObject getObjectByPointer(int pointer) {
        if (pointer == NIL) {
            return nil;
        } else {
            return storage.getByPointer(pointer);
        }
    }

    @Override
    public ListObject search(int key) {
        ListObject current = getObjectByPointer(nil.getNext());
        while (current != nil && current.getKey() != key) {
            current = getObjectByPointer(current.getNext());
        }
        return current == nil ? null : current;
    }

    @Override
    public void insert(ListObject listObject) {
        ListObject currentHead = getObjectByPointer(nil.getNext());

        listObject.setNext(currentHead.getPointer());
        currentHead.setPrev(listObject.getPointer());

        nil.setNext(listObject.getPointer());
        listObject.setPrev(nil.getPointer());
    }

    @Override
    public void delete(ListObject listObject) {

    }

    @Override
    public ListObjectsStorage getStorage() {
        return storage;
    }

}
