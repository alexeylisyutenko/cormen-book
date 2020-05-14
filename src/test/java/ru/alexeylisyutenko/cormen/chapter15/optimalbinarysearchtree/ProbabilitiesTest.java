package ru.alexeylisyutenko.cormen.chapter15.optimalbinarysearchtree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProbabilitiesTest {

    @Test
    void validConstruction() {
        new Probabilities(
                new double[]{0.3},
                new double[]{0.5, 0.2}
        );
    }

    @Test
    void incorrectSizes() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new Probabilities(new double[]{0.3}, new double[]{0.5}));
        assertEquals("miss.length must be equal to hit.length + 1", exception.getMessage());
    }

    @Test
    void incorrectSum() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class, () -> new Probabilities(new double[]{0.3}, new double[]{0.5, 0.1}));
        assertEquals("Sum of all probabilities must be equal to 1.0", exception.getMessage());
    }

}