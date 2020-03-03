package ru.alexeylisyutenko.cormen.chapter15.rodcutting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultRodCuttingTest {

    public static final int[] DEFAULT_PRICES = new int[]{1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    private RodCutting rodCutting;

    @BeforeEach
    void setup() {
        rodCutting = new DefaultRodCutting();
    }

    @Test
    void cutRod() {
        assertEquals(1, rodCutting.cutRod(DEFAULT_PRICES, 1));
        assertEquals(5, rodCutting.cutRod(DEFAULT_PRICES, 2));
        assertEquals(8, rodCutting.cutRod(DEFAULT_PRICES, 3));
        assertEquals(10, rodCutting.cutRod(DEFAULT_PRICES, 4));
        assertEquals(13, rodCutting.cutRod(DEFAULT_PRICES, 5));
        assertEquals(17, rodCutting.cutRod(DEFAULT_PRICES, 6));
        assertEquals(18, rodCutting.cutRod(DEFAULT_PRICES, 7));
        assertEquals(22, rodCutting.cutRod(DEFAULT_PRICES, 8));
        assertEquals(25, rodCutting.cutRod(DEFAULT_PRICES, 9));
        assertEquals(30, rodCutting.cutRod(DEFAULT_PRICES, 10));
    }

}