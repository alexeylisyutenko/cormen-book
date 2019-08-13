package ru.alexeylisyutenko.cormen.chapter12;

public class BinaryTreeException extends RuntimeException {
    public BinaryTreeException(String message) {
        super(message);
    }

    public BinaryTreeException(String message, Throwable cause) {
        super(message, cause);
    }
}
