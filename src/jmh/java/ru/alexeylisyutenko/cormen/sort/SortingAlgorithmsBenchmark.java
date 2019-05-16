package ru.alexeylisyutenko.cormen.sort;

import org.openjdk.jmh.annotations.*;
import ru.alexeylisyutenko.cormen.chapter6.heapsort.Heap;
import ru.alexeylisyutenko.cormen.chapter7.HoareQuickSort;
import ru.alexeylisyutenko.cormen.chapter7.QuickSort;
import ru.alexeylisyutenko.cormen.chapter8.RadixSort;

import java.util.concurrent.TimeUnit;

import static ru.alexeylisyutenko.helper.Helpers.randomPositiveIntArray;

@State(Scope.Thread)
public class SortingAlgorithmsBenchmark {


    @Param({"10", "100", "1000", "10000", "100000"})
    int size;

    int[] array;

    @Setup
    public void setup() {
        array = randomPositiveIntArray(100, (int) Math.pow(10, 6) - 1);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public void radixSort() {
        RadixSort.sort(array, 6);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public void quickSort() {
        QuickSort.sort(array);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public void hoareQuickSort() {
        HoareQuickSort.sort(array);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public void heapSort() {
        Heap.sort(array);
    }

}
