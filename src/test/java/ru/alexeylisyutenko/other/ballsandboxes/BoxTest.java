package ru.alexeylisyutenko.other.ballsandboxes;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void valueOf() {
        Box emptyBox = Box.valueOf();
        assertTrue(emptyBox.getBalls().isEmpty());

        Box box = Box.valueOf(Set.of(1, 2, 3));
        assertFalse(box.getBalls().isEmpty());
        assertEquals(Set.of(1, 2, 3), box.getBalls());
    }

    @Test
    void putBall() {
        Box box = Box.valueOf();

        Box box1 = box.putBall(1);
        assertFalse(box1.getBalls().isEmpty());
        assertEquals(Set.of(1), box1.getBalls());
        assertNotSame(box, box1);


    }

    @Test
    void testToString() {
    }
}