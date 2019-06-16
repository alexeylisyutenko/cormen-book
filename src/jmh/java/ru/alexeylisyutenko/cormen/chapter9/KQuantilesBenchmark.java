package ru.alexeylisyutenko.cormen.chapter9;

import org.openjdk.jmh.annotations.*;
import ru.alexeylisyutenko.cormen.chapter7.QuickSort;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static ru.alexeylisyutenko.helper.Helpers.randomPositiveIntArray;

@State(Scope.Thread)
public class KQuantilesBenchmark {

    @Param({"10", "100", "1000", "10000", "100000", "1000000"})
    int size;
    int k = 10;

    int[] array;

    @Setup(Level.Iteration)
    public void setup() {
        array = randomPositiveIntArray(size, size * 10);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] sorting() {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        return KQuantilesSorting.kQuantiles(arrayCopy, k);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 3, time = 2)
    @Measurement(iterations = 3, time = 2)
    public int[] advanced() {
        int[] arrayCopy = Arrays.copyOf(array, array.length);
        return KQuantiles.kQuantiles(arrayCopy, k);
    }

}
