package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class HashEntry<K, V> {

    public static int NIL_HASH_ENTRY_INDEX = -1;

    private final int index;
    private boolean empty;
    private K key;
    private V value;
    private int nextIndex;
    private int previousIndex;

    public HashEntry(int index) {
        this.index = index;
    }
}
