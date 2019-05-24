package ru.alexeylisyutenko.cormen.sort;

import org.openjdk.jmh.annotations.*;
import ru.alexeylisyutenko.cormen.chapter8.CountingSort;
import ru.alexeylisyutenko.cormen.chapter8.CountingSortInPlace;
import ru.alexeylisyutenko.cormen.chapter8.CountingSortInPlace2;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ru.alexeylisyutenko.helper.Helpers.randomPositiveIntArray;

@State(Scope.Thread)
public class CountingSortBenchmark {

    private final static int K = 100;

    @Param({"10", "50", "100", "200", "500", "1000", "10000"})
    int size;

    int[] array;

    @Setup(Level.Iteration)
    public void setup() {
        array = randomPositiveIntArray(size, K);
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
    public int[] standartCountingSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        CountingSort.sort(arrayToSort, K);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] inplaceCountingSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        CountingSortInPlace.sort(arrayToSort, K);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] inplaceCountingSort2() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        CountingSortInPlace2.sort(arrayToSort, K);
        return arrayToSort;
    }

}
