package ru.alexeylisyutenko.cormen.sort;

import org.openjdk.jmh.annotations.*;
import ru.alexeylisyutenko.cormen.chapter2.InsertionSort;
import ru.alexeylisyutenko.cormen.chapter2.MergeSort;
import ru.alexeylisyutenko.cormen.chapter2.MergeSortWithInsertionSortBaseCase;
import ru.alexeylisyutenko.cormen.chapter6.HeapSort;
import ru.alexeylisyutenko.cormen.chapter7.HoareQuickSort;
import ru.alexeylisyutenko.cormen.chapter7.QuickSort;
import ru.alexeylisyutenko.cormen.chapter7.QuickSortWithEqualElements;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ru.alexeylisyutenko.helper.Helpers.randomPositiveIntArray;

@State(Scope.Thread)
public class SortingAlgorithmsBenchmark {

    @Param({"10", "100", "1000", "10000", "100000"})
    int size;

//    @Param({"10", "100", "200", "300", "300"})
//    int size;

    int[] array;

    @Setup(Level.Iteration)
    public void setup() {
        array = randomPositiveIntArray(size, (int) Math.pow(10, 7) - 1);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] baseline() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] quickSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        QuickSort.sort(arrayToSort);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] hoareQuickSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        HoareQuickSort.sort(arrayToSort);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] quickSortWithEqualElements() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        QuickSortWithEqualElements.sort(arrayToSort);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] heapSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        HeapSort.sort(arrayToSort);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] insertionSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        InsertionSort.sort(arrayToSort);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] mergeSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        MergeSort.sort(arrayToSort);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] mergeSortWithInsertionSortBaseCase() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        MergeSortWithInsertionSortBaseCase.sort(arrayToSort);
        return arrayToSort;
    }

}
