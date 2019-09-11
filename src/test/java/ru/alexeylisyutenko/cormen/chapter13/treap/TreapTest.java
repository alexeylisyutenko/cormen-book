package ru.alexeylisyutenko.cormen.chapter13.treap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class TreapTest {

    private Treap<Integer> treap;

    @BeforeEach
    void setup() {
        treap = new Treap<>();
    }

    @Test
    void insertionDemo() {
        IntStream.rangeClosed(1, 8).forEach(treap::insert);
        treap.print();
    }

}