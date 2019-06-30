package ru.alexeylisyutenko.cormen.chapter10.list;

import lombok.ToString;
import ru.alexeylisyutenko.cormen.chapter10.DefaultListObject;
import ru.alexeylisyutenko.cormen.chapter10.ListObject;
import ru.alexeylisyutenko.cormen.chapter10.ListObjectException;
import ru.alexeylisyutenko.cormen.chapter10.storage.ListObjectsStorage;
import ru.alexeylisyutenko.cormen.chapter10.storage.MultipleArrayListObjectsStorage;

import java.util.function.Consumer;

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
        ListObject prev = getObjectByPointer(listObject.getPrev());
        ListObject next = getObjectByPointer(listObject.getNext());

        prev.setNext(next.getPointer());
        next.setPrev(prev.getPointer());
    }

    @Override
    public ListObjectsStorage getStorage() {
        return storage;
    }

    @Override
    public void iterate(Consumer<ListObject> action) {
        ListObject current = getObjectByPointer(nil.getNext());
        while (current != nil) {
            action.accept(current);
            current = getObjectByPointer(current.getNext());
        }
    }

    @Override
    public ListObject getHead() {
        ListObject head = getObjectByPointer(nil.getNext());
        return head == nil ? null : head;
    }

    @Override
    public void compatify() {
        if (!(storage instanceof MultipleArrayListObjectsStorage)) {
            throw new ListObjectException("compactify operation supported only for MultipleArrayListObjectsStorage storage");
        }
        storage.compactify(listObject -> {
            ListObject prev = getObjectByPointer(listObject.getPrev());
            ListObject next = getObjectByPointer(listObject.getNext());

            prev.setNext(listObject.getPointer());
            next.setPrev(listObject.getPointer());
        });
    }
}
