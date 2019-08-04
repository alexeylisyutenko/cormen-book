package ru.alexeylisyutenko.cormen.chapter11.openaddressinghashtable;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter11.hashfunctionfactory.DivisionMethodIntegerHashFunctionFactory;
import ru.alexeylisyutenko.cormen.chapter11.probesequencefactory.IntegerLinearProbeSequenceFunctionFactory;

import static org.junit.jupiter.api.Assertions.*;

class OpenAddressingHashTableTest {

    @Test
    void mainOperationsDemo() {
        IntegerLinearProbeSequenceFunctionFactory sequenceFunctionFactory = new IntegerLinearProbeSequenceFunctionFactory(new DivisionMethodIntegerHashFunctionFactory());
        OpenAddressingHashTable<Integer, String> hashTable = new OpenAddressingHashTable<>(sequenceFunctionFactory, 5);

//        System.out.println(hashTable.search(123));
//        System.out.println(hashTable);
//
//        hashTable.insert(123, "some string");
//        System.out.println(hashTable);
//        System.out.println(hashTable.search(123));
//
//        hashTable.delete(123);
//        System.out.println(hashTable.search(123));
//        System.out.println(hashTable);
//
//        hashTable.insert(123, "some string");
//        System.out.println(hashTable);
//        System.out.println(hashTable.search(123));

        hashTable.insert(0, "0");
        hashTable.insert(1, "1");
        hashTable.insert(3, "3");
        hashTable.insert(5, "5");

        hashTable.delete(5);
        System.out.println(hashTable.search(5));
        System.out.println(hashTable);

    }

}