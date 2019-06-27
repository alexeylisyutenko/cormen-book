package ru.alexeylisyutenko.cormen.chapter10;

import java.util.Arrays;

public class MultipleArrayListObjectsStorage implements ListObjectsStorage {

    public static final int NIL = -1;

    private final int[] nextPointers;
    private final int[] keys;
    private final int[] prevPointers;

    private int freePointer;

    public MultipleArrayListObjectsStorage(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Incorrect size");
        }
        this.nextPointers = new int[size];
        this.keys = new int[size];
        this.prevPointers = new int[size];
        this.freePointer = 0;
        initializeFreeList(size);
    }

    private void initializeFreeList(int size) {
        for (int i = 0; i < size - 1; i++) {
            nextPointers[i] = i + 1;
        }
        nextPointers[size - 1] = NIL;
    }

    @Override
    public ListObject allocateObject() {
        if (freePointer == NIL) {
            throw new ListObjectException("Not enough space");
        }
        MultipleArrayListObject listObject = new MultipleArrayListObject(freePointer);
        freePointer = listObject.getNext();
        return listObject;
    }

    @Override
    public void freeObject(ListObject listObject) {
        if (!(listObject instanceof MultipleArrayListObject)) {
            throw new ListObjectException("Unsupported listObject class");
        }
        MultipleArrayListObjectsStorage listObjectsStorage = ((MultipleArrayListObject) listObject).getStorage();
        if (listObjectsStorage != this) {
            throw new ListObjectException("ListObject does not belong to this MultipleArrayListObjectsStorage instance");
        }
        listObject.setNext(freePointer);
        freePointer = listObject.getPointer();
    }

    @Override
    public String toString() {
        return "MultipleArrayListObjectsStorage{" +
                "\r\n\tnext=" + Arrays.toString(nextPointers) +
                "\r\n\tkeys=" + Arrays.toString(keys) +
                "\r\n\tprev=" + Arrays.toString(prevPointers) +
                "\r\n\tfree=" + freePointer +
                "\r\n}";
    }

    private class MultipleArrayListObject implements ListObject {

        private final int pointer;

        private MultipleArrayListObject(int pointer) {
            this.pointer = pointer;
        }

        @Override
        public int getNext() {
            return nextPointers[pointer];
        }

        @Override
        public void setNext(int next) {
            if (next < -1 || next >= nextPointers.length) {
                throw new ListObjectException("Incorrect next argument");
            }
            nextPointers[pointer] = next;
        }

        @Override
        public int getKey() {
            return keys[pointer];
        }

        @Override
        public void setKey(int key) {
            keys[pointer] = key;
        }

        @Override
        public int getPrev() {
            return prevPointers[pointer];
        }

        @Override
        public void setPrev(int prev) {
            if (prev < -1 || prev >= prevPointers.length) {
                throw new ListObjectException("Incorrect prev argument");
            }
            prevPointers[pointer] = prev;
        }

        @Override
        public int getPointer() {
            return pointer;
        }

        private MultipleArrayListObjectsStorage getStorage() {
            return MultipleArrayListObjectsStorage.this;
        }

        @Override
        public String toString() {
            return "MultipleArrayListObject{" +
                    "next=" + getNext() +
                    ", key=" + getKey() +
                    ", prev=" + getPrev() +
                    ", pointer=" + getPointer() +
                    "}";
        }
    }

}
