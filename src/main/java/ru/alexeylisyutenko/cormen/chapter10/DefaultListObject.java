package ru.alexeylisyutenko.cormen.chapter10;

import lombok.Data;

@Data
public class DefaultListObject implements ListObject {
    private int next;
    private int key;
    private int prev;
    private int pointer;

    public DefaultListObject(int pointer) {
        this.pointer = pointer;
    }
}
