package ru.alexeylisyutenko.cormen.chapter10;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter10.storage.ListObjectsStorage;
import ru.alexeylisyutenko.cormen.chapter10.storage.MultipleArrayListObjectsStorage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MultipleArrayListObjectsStorageTest {

    @Test
    void mainOperationsDemo() {
        ListObjectsStorage listObjectsStorage = new MultipleArrayListObjectsStorage(10);

        System.out.println(listObjectsStorage);

        ListObject listObject = listObjectsStorage.allocateObject();
        listObject.setKey(31337);
        System.out.println(listObject);

        ListObject byPointer = listObjectsStorage.getByPointer(0);
        System.out.println(byPointer);

        System.out.println(listObjectsStorage);

        listObjectsStorage.freeObject(listObject);
        System.out.println(listObjectsStorage);
    }

    @Test
    void allocationAndFreeingShouldWorkProperly() {
        ListObjectsStorage listObjectsStorage = new MultipleArrayListObjectsStorage(2);

        ListObject listObject1 = listObjectsStorage.allocateObject();
        assertEquals(0, listObject1.getPointer());

        ListObject listObject2 = listObjectsStorage.allocateObject();
        assertEquals(1, listObject2.getPointer());

        assertThrows(ListObjectException.class, listObjectsStorage::allocateObject);

        listObjectsStorage.freeObject(listObject1);

        ListObject listObject3 = listObjectsStorage.allocateObject();
        assertEquals(0, listObject3.getPointer());

        listObjectsStorage.freeObject(listObject2);

        ListObject listObject4 = listObjectsStorage.allocateObject();
        assertEquals(1, listObject4.getPointer());
    }

    @Test
    void listObjectBelongsToStorageShouldWorkProperly() {
        ListObjectsStorage listObjectsStorage = new MultipleArrayListObjectsStorage(2);

        ListObject listObject1 = listObjectsStorage.allocateObject();

        assertTrue(listObjectsStorage.listObjectBelongsToStorage(listObject1));
        assertFalse(listObjectsStorage.listObjectBelongsToStorage(new DefaultListObject(0)));

        ListObjectsStorage listObjectsStorage2 = new MultipleArrayListObjectsStorage(2);
        ListObject listObject2 = listObjectsStorage2.allocateObject();

        assertFalse(listObjectsStorage.listObjectBelongsToStorage(listObject2));
    }

    @Test
    void getByPointerShouldWorkProperly() {
        ListObjectsStorage listObjectsStorage = new MultipleArrayListObjectsStorage(3);

        ListObject listObject = listObjectsStorage.allocateObject();
        listObject.setKey(1234);

        ListObject listObjectByPointer = listObjectsStorage.getByPointer(listObject.getPointer());
        assertEquals(listObject.getPointer(), listObjectByPointer.getPointer());
        assertEquals(1234, listObjectByPointer.getKey());
        assertEquals(listObject.getNext(), listObjectByPointer.getNext());
        assertEquals(listObject.getPrev(), listObjectByPointer.getPrev());

        assertThrows(ListObjectException.class, () -> listObjectsStorage.getByPointer(1));
    }

    @Test
    void getSizeShouldWorkProperly() {
        ListObjectsStorage listObjectsStorage = new MultipleArrayListObjectsStorage(10);
        assertEquals(10, listObjectsStorage.getSize());
    }

    @Test
    void compactifyDemo() {
        ListObjectsStorage storage = new MultipleArrayListObjectsStorage(6);

        System.out.println(storage);

        ListObject listObject1 = storage.allocateObject();
        listObject1.setKey(1);

        ListObject listObject2 = storage.allocateObject();
        listObject2.setKey(2);

        ListObject listObject3 = storage.allocateObject();
        listObject3.setKey(3);

        ListObject listObject4 = storage.allocateObject();
        listObject4.setKey(4);

        ListObject listObject5 = storage.allocateObject();
        listObject5.setKey(5);

        ListObject listObject6 = storage.allocateObject();
        listObject6.setKey(6);

        storage.freeObject(listObject1);
        storage.freeObject(listObject2);
        storage.freeObject(listObject4);
        System.out.println(storage);

        System.out.println("List object 5: " + listObject5);
        System.out.println("List object 6: " + listObject6);

        storage.compactify(System.out::println);

        System.out.println("List object 5: " + listObject5);
        System.out.println("List object 6: " + listObject6);

        System.out.println(storage);
    }

    @Test
    void compactifyOfTheEmptyStorageShouldWorkProperly() {
        MultipleArrayListObjectsStorage storage = new MultipleArrayListObjectsStorage(3);
        List<ListObject> compactifiedListObjects = new ArrayList<>();
        storage.compactify(compactifiedListObjects::add);

        assertTrue(compactifiedListObjects.isEmpty());

        assertArrayEquals(new int[] {1, 2, -1}, storage.getNextPointers());
        assertArrayEquals(new int[] {-100, -100, -100}, storage.getPrevPointers());
        assertEquals(0, storage.getFreePointer());
    }

    @Test
    void compactifyOfAlreadyCompactStorageShouldWorkProperly() {
        MultipleArrayListObjectsStorage storage = new MultipleArrayListObjectsStorage(3);

        storage.allocateObject().setKey(1);
        storage.allocateObject().setKey(2);

        List<ListObject> compactifiedListObjects = new ArrayList<>();
        storage.compactify(compactifiedListObjects::add);

        assertTrue(compactifiedListObjects.isEmpty());

        assertArrayEquals(new int[] {-1, -1, -1}, storage.getNextPointers());
        assertArrayEquals(new int[] {1, 2, -1}, storage.getKeys());
        assertArrayEquals(new int[] {-1, -1, -100}, storage.getPrevPointers());
        assertEquals(2, storage.getFreePointer());
    }

    @Test
    void compactifyOnFullStorageShouldWorkProperly() {
        MultipleArrayListObjectsStorage storage = new MultipleArrayListObjectsStorage(3);

        storage.allocateObject().setKey(1);
        storage.allocateObject().setKey(2);
        storage.allocateObject().setKey(3);

        List<ListObject> compactifiedListObjects = new ArrayList<>();
        storage.compactify(compactifiedListObjects::add);

        assertTrue(compactifiedListObjects.isEmpty());

        assertArrayEquals(new int[] {-1, -1, -1}, storage.getNextPointers());
        assertArrayEquals(new int[] {1, 2, 3}, storage.getKeys());
        assertArrayEquals(new int[] {-1, -1, -1}, storage.getPrevPointers());
        assertEquals(-1, storage.getFreePointer());
    }

    @Test
    void compactifyShouldWorkProperly() {
        MultipleArrayListObjectsStorage storage = new MultipleArrayListObjectsStorage(6);

        ListObject listObject1 = storage.allocateObject();
        listObject1.setKey(1);

        ListObject listObject2 = storage.allocateObject();
        listObject2.setKey(2);

        ListObject listObject3 = storage.allocateObject();
        listObject3.setKey(3);

        ListObject listObject4 = storage.allocateObject();
        listObject4.setKey(4);

        ListObject listObject5 = storage.allocateObject();
        listObject5.setKey(5);

        ListObject listObject6 = storage.allocateObject();
        listObject6.setKey(6);

        storage.freeObject(listObject1);
        storage.freeObject(listObject2);
        storage.freeObject(listObject4);

        List<ListObject> compactifiedListObjects = new ArrayList<>();
        storage.compactify(compactifiedListObjects::add);

        assertEquals(2, compactifiedListObjects.size());
        assertEquals(6, compactifiedListObjects.get(0).getKey());
        assertEquals(5, compactifiedListObjects.get(1).getKey());

        assertArrayEquals(new int[] {-1, -1, -1, 4, 5, -1}, storage.getNextPointers());
        assertArrayEquals(new int[] {6, 5, 3, -1, 5, 6}, storage.getKeys());
        assertArrayEquals(new int[] {-1, -1, -1, -100, -100, -100}, storage.getPrevPointers());
        assertEquals(3, storage.getFreePointer());

    }

}