package ru.alexeylisyutenko.other.dynamicarray;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DynamicArrayImplTest {

    private DynamicArray<String> dynamicArray;

    @BeforeEach
    void setup() {
        dynamicArray = new DynamicArrayImpl<>();
    }

    @Test
    void testMainFunctionality() {
        assertEquals(0, dynamicArray.size());

        for (int i = 0; i < 16; i++) {
            dynamicArray.insertAtEnd(i + "");
        }

        assertEquals(16, dynamicArray.size());
        assertEquals(16, dynamicArray.capacity());

        assertEquals("0", dynamicArray.get(0));
        assertEquals("15", dynamicArray.get(15));

        dynamicArray.set(7, "Some new value");
        assertEquals("Some new value", dynamicArray.get(7));

        dynamicArray.insertAtEnd("16");
        assertEquals(17, dynamicArray.size());
        assertEquals(32, dynamicArray.capacity());

        assertEquals("16", dynamicArray.get(16));

        String removedValue = dynamicArray.remove(14);
        assertEquals("14", removedValue);

        assertEquals(16, dynamicArray.size());
        assertEquals(32, dynamicArray.capacity());

        assertEquals("15", dynamicArray.get(14));
        assertEquals("16", dynamicArray.get(15));
    }

}