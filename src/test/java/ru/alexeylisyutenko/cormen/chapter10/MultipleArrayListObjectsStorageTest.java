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
    void allocationShouldWorkProperly() {

    }

}