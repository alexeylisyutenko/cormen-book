package ru.alexeylisyutenko.cormen.chapter12.base;

public class BinarySearchTreeException extends RuntimeException {
    public BinarySearchTreeException(String message) {
        super(message);
    }

    public BinarySearchTreeException(String message, Throwable cause) {
        super(message, cause);
    }
}
