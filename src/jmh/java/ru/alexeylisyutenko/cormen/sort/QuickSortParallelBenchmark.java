package ru.alexeylisyutenko.cormen.sort;

import org.openjdk.jmh.annotations.*;
import ru.alexeylisyutenko.cormen.chapter7.QuickSort;
import ru.alexeylisyutenko.cormen.chapter7.QuickSortParallel;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ru.alexeylisyutenko.helper.Helpers.randomPositiveIntArray;

@State(Scope.Thread)
public class QuickSortParallelBenchmark {

    @Param({"10000000", "100000000"})
    int size;

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
    public int[] quickSortParallel() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        QuickSortParallel.sort(arrayToSort);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] standardParallelSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        Arrays.parallelSort(arrayToSort);
        return arrayToSort;
    }

}
