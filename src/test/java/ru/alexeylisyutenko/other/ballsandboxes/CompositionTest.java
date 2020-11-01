package ru.alexeylisyutenko.other.ballsandboxes;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CompositionTest {
    @Test
    void ofSize() {
        Composition composition = Composition.ofSize(3);
        assertEquals(List.of(Box.of(), Box.of(), Box.of()), composition.getBoxes());

        assertThrows(IllegalArgumentException.class, () -> Composition.ofSize(-1));
    }

    @Test
    void putBallIntoBox() {
        Composition composition = Composition.ofSize(3);
        assertEquals(List.of(Box.of(), Box.of(), Box.of()), composition.getBoxes());

        Composition composition1 = composition.putBallIntoBox(2, 5);
        assertEquals(List.of(Box.of(), Box.of(5), Box.of()), composition1.getBoxes());
        assertNotSame(composition, composition1);
        assertEquals(List.of(Box.of(), Box.of(), Box.of()), composition.getBoxes());

        assertThrows(IllegalArgumentException.class, () -> composition.putBallIntoBox(4, 7));
        assertThrows(IllegalArgumentException.class, () -> composition.putBallIntoBox(0, 7));
    }

    @Test
    void testToString() {
        Composition composition = Composition.ofSize(3)
                .putBallIntoBox(1, 1)
                .putBallIntoBox(1, 2)
                .putBallIntoBox(1, 3)
                .putBallIntoBox(3, 4)
                .putBallIntoBox(3, 5)
                .putBallIntoBox(3, 6);

        assertEquals("(1, 2, 3), (), (4, 5, 6)", composition.toString());
    }
}