package ru.alexeylisyutenko.cormen.chapter11;

public class HashTableException extends RuntimeException {
    public HashTableException(String message) {
        super(message);
    }

    public HashTableException(String message, Throwable cause) {
        super(message, cause);
    }
}
