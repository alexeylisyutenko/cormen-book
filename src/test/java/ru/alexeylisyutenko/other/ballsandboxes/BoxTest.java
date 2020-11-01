package ru.alexeylisyutenko.other.ballsandboxes;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BoxTest {

    @Test
    void valueOf() {
        Box emptyBox = Box.of();
        assertTrue(emptyBox.getBalls().isEmpty());

        Box box = Box.of(1, 2, 3);
        assertFalse(box.getBalls().isEmpty());
        assertEquals(Set.of(1, 2, 3), box.getBalls());
    }

    @Test
    void putBall() {
        Box box = Box.of();

        Box box1 = box.putBall(1);
        assertFalse(box1.getBalls().isEmpty());
        assertEquals(Set.of(1), box1.getBalls());
        assertNotSame(box, box1);

        Box box2 = box1.putBall(1);
        assertFalse(box2.getBalls().isEmpty());
        assertEquals(Set.of(1), box2.getBalls());
        assertNotSame(box, box2);
        assertNotSame(box1, box2);

        Box box3 = box2.putBall(2);
        assertFalse(box3.getBalls().isEmpty());
        assertEquals(Set.of(1, 2), box3.getBalls());
        assertNotSame(box, box3);
        assertNotSame(box1, box3);
        assertNotSame(box2, box3);

        assertThrows(IllegalArgumentException.class, () -> box.putBall(0));
    }

    @Test
    void testToString() {
        assertEquals("(1, 2, 3)", Box.of(1, 2, 3).toString());
        assertEquals("()", Box.of().toString());
    }
}