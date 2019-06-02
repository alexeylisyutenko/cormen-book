package ru.alexeylisyutenko.cormen.sort;

import org.openjdk.jmh.annotations.*;
import ru.alexeylisyutenko.cormen.chapter7.QuickSort;
import ru.alexeylisyutenko.cormen.chapter8.columnsort.ColumnSort;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ru.alexeylisyutenko.helper.Helpers.randomPositiveIntArray;

@State(Scope.Thread)
public class ColumnSortBenchmark {

    int s = 10;
    int r = 500;

    int size = s * r;

    int[] array;

    @Setup(Level.Iteration)
    public void setup() {
        array = randomPositiveIntArray(size, size * 10);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public int[] quickSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        QuickSort.sort(arrayToSort);
        return arrayToSort;
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    public int[] columnSort() {
        int[] arrayToSort = Arrays.copyOf(array, array.length);
        ColumnSort.sort(arrayToSort, r, s, false);
        return arrayToSort;
    }

}
