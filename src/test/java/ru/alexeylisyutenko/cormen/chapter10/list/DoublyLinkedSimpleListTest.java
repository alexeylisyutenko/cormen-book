package ru.alexeylisyutenko.cormen.chapter10.list;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter10.ListObject;
import ru.alexeylisyutenko.cormen.chapter10.storage.MultipleArrayListObjectsStorage;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedSimpleListTest {

    @Test
    void listDemo() {
        DoublyLinkedSimpleList list = new DoublyLinkedSimpleList(new MultipleArrayListObjectsStorage(10));

        // Insert first object.
        ListObject listObject = list.getStorage().allocateObject();
        listObject.setKey(5);
        list.insert(listObject);

        System.out.println("After first insertion:");
        System.out.println(list);
        System.out.println();

        // Insert second object.
        ListObject listObject2 = list.getStorage().allocateObject();
        listObject2.setKey(100);
        list.insert(listObject2);

        System.out.println("After second insertion:");
        System.out.println(list);
        System.out.println();

        ListObject foundListObject = list.search(100);
        System.out.println(foundListObject);
    }

}