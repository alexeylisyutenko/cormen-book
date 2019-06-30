package ru.alexeylisyutenko.cormen.chapter10.list;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter10.ListObject;
import ru.alexeylisyutenko.cormen.chapter10.storage.MultipleArrayListObjectsStorage;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

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

        // Print list.
        System.out.println("Print list");
        list.iterate(System.out::println);
        System.out.println();

        // Search list object by key.
        ListObject foundListObject = list.search(100);
        System.out.println(foundListObject);
        System.out.println();

        // Delete list object.
        list.delete(foundListObject);
        list.getStorage().freeObject(foundListObject);
        System.out.println(list);
    }

    @Test
    void insertShouldWorkProperly() {
        MultipleArrayListObjectsStorage storage = new MultipleArrayListObjectsStorage(2);
        DoublyLinkedSimpleList list = new DoublyLinkedSimpleList(storage);

        // Insert first element.
        ListObject listObject = list.getStorage().allocateObject();
        listObject.setKey(1);
        list.insert(listObject);

        assertArrayEquals(new int[]{-1, -1}, storage.getNextPointers());
        assertArrayEquals(new int[]{1, 0}, storage.getKeys());
        assertArrayEquals(new int[]{-1, -100}, storage.getPrevPointers());
        assertEquals(1, storage.getFreePointer());
        assertEquals(0, list.getHead().getPointer());
        assertEquals(1, list.getHead().getKey());

        // Insert second element.
        ListObject listObject2 = list.getStorage().allocateObject();
        listObject2.setKey(2);
        list.insert(listObject2);

        assertArrayEquals(new int[]{-1, 0}, storage.getNextPointers());
        assertArrayEquals(new int[]{1, 2}, storage.getKeys());
        assertArrayEquals(new int[]{1, -1}, storage.getPrevPointers());
        assertEquals(-1, storage.getFreePointer());
        assertEquals(1, list.getHead().getPointer());
        assertEquals(2, list.getHead().getKey());
    }

    @Test
    void searchShouldWorkProperly() {
        MultipleArrayListObjectsStorage storage = new MultipleArrayListObjectsStorage(2);
        DoublyLinkedSimpleList list = new DoublyLinkedSimpleList(storage);

        assertNull(list.search(-1));

        ListObject listObject = list.getStorage().allocateObject();
        listObject.setKey(-1);
        list.insert(listObject);

        ListObject foundListObject1 = list.search(-1);
        assertEquals(-1, foundListObject1.getKey());

        ListObject listObject2 = list.getStorage().allocateObject();
        listObject2.setKey(100);
        list.insert(listObject2);

        ListObject foundListObject2 = list.search(100);
        assertEquals(100, foundListObject2.getKey());
    }

    @Test
    void deleteShouldWorkProperly() {
        MultipleArrayListObjectsStorage storage = new MultipleArrayListObjectsStorage(2);
        DoublyLinkedSimpleList list = new DoublyLinkedSimpleList(storage);

        // Insert first element.
        ListObject listObject = list.getStorage().allocateObject();
        listObject.setKey(1);
        list.insert(listObject);

        // Insert second element.
        ListObject listObject2 = list.getStorage().allocateObject();
        listObject2.setKey(2);
        list.insert(listObject2);

        assertArrayEquals(new int[]{-1, 0}, storage.getNextPointers());
        assertArrayEquals(new int[]{1, 2}, storage.getKeys());
        assertArrayEquals(new int[]{1, -1}, storage.getPrevPointers());
        assertEquals(-1, storage.getFreePointer());
        assertEquals(1, list.getHead().getPointer());
        assertEquals(2, list.getHead().getKey());

        // Delete element.
        list.delete(listObject2);
        list.getStorage().freeObject(listObject2);

        assertArrayEquals(new int[]{-1, -1}, storage.getNextPointers());
        assertArrayEquals(new int[]{1, 2}, storage.getKeys());
        assertArrayEquals(new int[]{-1, -100}, storage.getPrevPointers());
        assertEquals(1, storage.getFreePointer());
        assertEquals(0, list.getHead().getPointer());
        assertEquals(1, list.getHead().getKey());

        // Insert one more element element.
        ListObject listObject3 = list.getStorage().allocateObject();
        listObject3.setKey(2);
        list.insert(listObject3);

        assertArrayEquals(new int[]{-1, 0}, storage.getNextPointers());
        assertArrayEquals(new int[]{1, 2}, storage.getKeys());
        assertArrayEquals(new int[]{1, -1}, storage.getPrevPointers());
        assertEquals(-1, storage.getFreePointer());
        assertEquals(1, list.getHead().getPointer());
        assertEquals(2, list.getHead().getKey());

        // Delete element.
        list.delete(listObject);
        list.getStorage().freeObject(listObject);

        assertArrayEquals(new int[]{-1, -1}, storage.getNextPointers());
        assertArrayEquals(new int[]{1, 2}, storage.getKeys());
        assertArrayEquals(new int[]{-100, -1}, storage.getPrevPointers());
        assertEquals(0, storage.getFreePointer());
        assertEquals(1, list.getHead().getPointer());
        assertEquals(2, list.getHead().getKey());
    }

    @Test
    void compactifyDemo() {
        MultipleArrayListObjectsStorage storage = new MultipleArrayListObjectsStorage(6);
        DoublyLinkedSimpleList list = new DoublyLinkedSimpleList(storage);

        // Add 6 list elements with keys from 1 to 6.
        ListObject listObject1 = list.getStorage().allocateObject();
        listObject1.setKey(1);
        list.insert(listObject1);

        ListObject listObject2 = list.getStorage().allocateObject();
        listObject2.setKey(2);
        list.insert(listObject2);

        ListObject listObject3 = list.getStorage().allocateObject();
        listObject3.setKey(3);
        list.insert(listObject3);

        ListObject listObject4 = list.getStorage().allocateObject();
        listObject4.setKey(4);
        list.insert(listObject4);

        ListObject listObject5 = list.getStorage().allocateObject();
        listObject5.setKey(5);
        list.insert(listObject5);

        ListObject listObject6 = list.getStorage().allocateObject();
        listObject6.setKey(6);
        list.insert(listObject6);

        // Delete elements with keys 1,2, and 4.
        list.delete(listObject1);
        list.getStorage().freeObject(listObject1);
        list.delete(listObject2);
        list.getStorage().freeObject(listObject2);
        list.delete(listObject4);
        list.getStorage().freeObject(listObject4);

//        list.delete(listObject3);
//        list.getStorage().freeObject(listObject3);

        list.delete(listObject6);
        list.getStorage().freeObject(listObject6);

//        list.delete(listObject5);
//        list.getStorage().freeObject(listObject5);

        // Print storage state and list.
        System.out.println(storage);
        list.iterate(System.out::println);
        System.out.println();

        // Compacify list.
        list.compatify();

        // Print storage state and list.
        System.out.println(storage);
        list.iterate(System.out::println);
    }

}