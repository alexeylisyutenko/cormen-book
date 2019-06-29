package ru.alexeylisyutenko.cormen.chapter10.storage;

import ru.alexeylisyutenko.cormen.chapter10.ListObject;
import ru.alexeylisyutenko.cormen.chapter10.ListObjectException;

import java.util.Arrays;

import static ru.alexeylisyutenko.cormen.chapter10.ListConstants.NIL;

public class MultipleArrayListObjectsStorage implements ListObjectsStorage {

    private static final int FREE_POSITION_PREV_MARK = -100;

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
            prevPointers[i] = FREE_POSITION_PREV_MARK;
        }
        nextPointers[size - 1] = NIL;
        prevPointers[size - 1] = FREE_POSITION_PREV_MARK;
    }

    @Override
    public ListObject allocateObject() {
        if (freePointer == NIL) {
            throw new ListObjectException("Not enough space");
        }
        MultipleArrayListObject listObject = new MultipleArrayListObject(freePointer);
        freePointer = listObject.getNext();
        initializeListObject(listObject);
        return listObject;
    }

    private void initializeListObject(MultipleArrayListObject listObject) {
        listObject.setKey(NIL);
        listObject.setNext(NIL);
        listObject.setPrev(NIL);
    }

    @Override
    public void freeObject(ListObject listObject) {
        if (!listObjectBelongsToStorage(listObject)) {
            throw new ListObjectException("ListObject does not belong to this MultipleArrayListObjectsStorage instance");
        }
        listObject.setNext(freePointer);
        prevPointers[listObject.getPointer()] = FREE_POSITION_PREV_MARK;
        freePointer = listObject.getPointer();
    }

    @Override
    public boolean listObjectBelongsToStorage(ListObject listObject) {
        if (!(listObject instanceof MultipleArrayListObject)) {
            return false;
        }
        MultipleArrayListObjectsStorage listObjectsStorage = ((MultipleArrayListObject) listObject).getStorage();
        return listObjectsStorage == this;
    }

    @Override
    public ListObject getByPointer(int pointer) {
        if (pointer < 0 || pointer >= nextPointers.length) {
            throw new ListObjectException("Incorrect pointer");
        }
        if (prevPointers[pointer] == FREE_POSITION_PREV_MARK) {
            throw new ListObjectException("There is no allocated object with such pointer");
        }
        return new MultipleArrayListObject(pointer);
    }

    public int[] getNextPointers() {
        return nextPointers;
    }

    public int[] getKeys() {
        return keys;
    }

    public int[] getPrevPointers() {
        return prevPointers;
    }

    public int getFreePointer() {
        return freePointer;
    }

    @Override
    public String toString() {
        return "MultipleArrayListObjectsStorage{" + "\r\n" +
                "\tnextPointers  " + Arrays.toString(nextPointers) + "\r\n" +
                "\tkeys          " + Arrays.toString(keys) + "\r\n" +
                "\tprevPointers  " + Arrays.toString(prevPointers) + "\r\n" +
                "\tfreePointer   " + freePointer + "\r\n" +
                "}";
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
