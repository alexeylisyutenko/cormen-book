package ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable;

import ru.alexeylisyutenko.cormen.chapter11.HashTableException;

import java.util.Arrays;

import static ru.alexeylisyutenko.cormen.chapter11.builtinchaininghashtable.HashEntry.NIL_HASH_ENTRY_INDEX;

class DefaultHashEntryStorage<K, V> implements HashEntryStorage<K, V> {
    private final HashEntry<K, V>[] table;
    private int freeListHeadIndex;

    DefaultHashEntryStorage(int hashTableSize) {
        this.table = (HashEntry<K, V>[]) new HashEntry[hashTableSize];
        initializeHashEntries(hashTableSize);
    }

    private void initializeHashEntries(int hashTableSize) {
        for (int i = 0; i < hashTableSize; i++) {
            HashEntry<K, V> hashEntry = new HashEntry<>(i);
            hashEntry.setEmpty(true);
            hashEntry.setNextIndex(i == hashTableSize - 1 ? NIL_HASH_ENTRY_INDEX : i + 1);
            hashEntry.setPreviousIndex(i == 0 ? NIL_HASH_ENTRY_INDEX : i - 1);
            table[i] = hashEntry;
        }
    }

    @Override
    public HashEntry<K, V> getHashEntry(int index) {
        return table[index];
    }

    @Override
    public HashEntry<K, V> allocateHashEntry() {
        if (freeListHeadIndex == NIL_HASH_ENTRY_INDEX) {
            throw new HashTableException("Out of space");
        }
        HashEntry<K, V> hashEntry = getHashEntry(freeListHeadIndex);
        if (hashEntry.getNextIndex() != NIL_HASH_ENTRY_INDEX) {
            getHashEntry(hashEntry.getNextIndex()).setPreviousIndex(NIL_HASH_ENTRY_INDEX);
        }
        freeListHeadIndex = hashEntry.getNextIndex();
        hashEntry.setEmpty(false);
        return hashEntry;
    }

    @Override
    public void releaseHashEntry(HashEntry<K, V> hashEntry) {
        hashEntry.setEmpty(true);
        hashEntry.setPreviousIndex(NIL_HASH_ENTRY_INDEX);
        hashEntry.setNextIndex(freeListHeadIndex);

        if (freeListHeadIndex != NIL_HASH_ENTRY_INDEX) {
            getHashEntry(freeListHeadIndex).setPreviousIndex(hashEntry.getIndex());
        }

        freeListHeadIndex = hashEntry.getIndex();
    }

    @Override
    public int getSize() {
        return table.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DefaultHashEntryStorage{").append("\r\n");
        sb.append("   freeListHeadIndex=").append(freeListHeadIndex).append("\r\n");
        sb.append("   table={").append("\r\n");
        for (HashEntry<K, V> hashEntry : table) {
            sb.append("      ").append(hashEntry).append("\r\n");
        }
        sb.append("   }").append("\r\n");
        sb.append('}');
        return sb.toString();
    }

}
