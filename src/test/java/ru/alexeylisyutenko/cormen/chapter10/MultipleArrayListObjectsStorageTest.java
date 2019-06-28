package ru.alexeylisyutenko.cormen.chapter10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultipleArrayListObjectsStorageTest {

    @Test
    void mainOperationsDemo() {
        ListObjectsStorage listObjectsStorage = new MultipleArrayListObjectsStorage(10);

        System.out.println(listObjectsStorage);

        ListObject listObject = listObjectsStorage.allocateObject();
        listObject.setKey(31337);
        System.out.println(listObject);

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
    }

    @Test
    void compactShouldWorkProperly() {

    }

}