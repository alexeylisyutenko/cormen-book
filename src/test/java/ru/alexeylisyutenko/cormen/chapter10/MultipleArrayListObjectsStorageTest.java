package ru.alexeylisyutenko.cormen.chapter10;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter10.storage.ListObjectsStorage;
import ru.alexeylisyutenko.cormen.chapter10.storage.MultipleArrayListObjectsStorage;

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

}