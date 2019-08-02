package ru.alexeylisyutenko.cormen.chapter11.probesequencefactory;

import ru.alexeylisyutenko.cormen.chapter11.probesequence.ProbeSequenceFunction;

public interface ProbeSequenceFunctionFactory<K> {
    ProbeSequenceFunction<K> create(int hashTableSize);
}
