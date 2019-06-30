package ru.alexeylisyutenko.cormen.chapter10.storage;

import org.apache.commons.lang3.StringUtils;
import ru.alexeylisyutenko.cormen.chapter10.ListObject;
import ru.alexeylisyutenko.cormen.chapter10.ListObjectException;

import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static ru.alexeylisyutenko.cormen.chapter10.ListConstants.NIL;

public class MultipleArrayListObjectsStorage implements ListObjectsStorage {

    private static final int FREE_POSITION_PREV_MARK = -100;

    private final int size;
    private final int[] nextPointers;
    private final int[] keys;
    private final int[] prevPointers;

    private int freePointer;

    private final Set<MultipleArrayListObject> listObjects;

    public MultipleArrayListObjectsStorage(int size) {
        if (size < 1) {
            throw new IllegalArgumentException("Incorrect size");
        }
        this.size = size;
        this.nextPointers = new int[size];
        this.keys = new int[size];
        this.prevPointers = new int[size];
        this.freePointer = 0;
        this.listObjects = Collections.newSetFromMap(new WeakHashMap<>());

        initializaKeys();
        initializeFreeList(size);
    }

    private void initializaKeys() {
        for (int i = 0; i < size; i++) {
            keys[i] = NIL;
        }
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

        listObjects.add(listObject);

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
        listObject.setKey(NIL);
        prevPointers[listObject.getPointer()] = FREE_POSITION_PREV_MARK;
        freePointer = listObject.getPointer();

        listObjects.remove(listObject);
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

        MultipleArrayListObject listObject = new MultipleArrayListObject(pointer);
        listObjects.add(listObject);
        return listObject;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void compactify(Consumer<ListObject> listObjectRelocationConsumer) {
        int left = 0;
        int right = size - 1;

        while (left < right) {
            // Find leftmost empty position.
            while (left < size && prevPointers[left] != FREE_POSITION_PREV_MARK) {
                left++;
            }

            // Find rightmost non empty position.
            while (right > 0 && prevPointers[right] == FREE_POSITION_PREV_MARK) {
                right--;
            }

            if (left < right) {
                // Move alement with index right to the element with index left.
                prevPointers[left] = prevPointers[right];
                keys[left] = keys[right];
                nextPointers[left] = nextPointers[right];

                // Mark right element as empty.
                prevPointers[right] = FREE_POSITION_PREV_MARK;

                // Update ListObjects instances.
                updateListObjects(right, left);

                // Notify client code that ListObject was moved.
                listObjectRelocationConsumer.accept(new MultipleArrayListObject(left));
            }
        }

        // Rebuild free list.
        for (int i = left; i < getSize(); i++) {
            if (i == getSize() - 1) {
                nextPointers[getSize() - 1] = NIL;
            } else {
                nextPointers[i] = i + 1;
            }
        }
        freePointer = left == getSize() ? NIL : left;
    }

    private void updateListObjects(int oldPointer, int newPointer) {
        listObjects.stream()
                .filter(listObject -> listObject.getPointer() == oldPointer)
                .forEach(listObject -> listObject.pointer = newPointer);
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
                "\tindexes       " + intArrayToStringFormatted(IntStream.range(0, size).toArray()) + "\r\n" +
                "\tnextPointers  " + intArrayToStringFormatted(nextPointers) + "\r\n" +
                "\tkeys          " + intArrayToStringFormatted(keys) + "\r\n" +
                "\tprevPointers  " + intArrayToStringFormatted(prevPointers) + "\r\n" +
                "\tfreePointer   " + freePointer + "\r\n" +
                "}";
    }

    private String intArrayToStringFormatted(int[] array) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('[');
        for (int i = 0; i < array.length; i++) {
            stringBuilder.append(StringUtils.center("" + array[i], 7));
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    private class MultipleArrayListObject implements ListObject {

        private int pointer;

        private MultipleArrayListObject(int pointer) {
            this.pointer = pointer;
        }

        @Override
        public int getNext() {
            return nextPointers[pointer];
        }

        @Override
        public void setNext(int next) {
            if (next < -1 || next >= size) {
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
            if (prev < -1 || prev >= size) {
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
