package ru.alexeylisyutenko.cormen.chapter8;

import org.openjdk.jmh.annotations.*;
import ru.alexeylisyutenko.cormen.chapter8.BucketSortPointsByOriginDistance.DoublePoint;

import java.util.concurrent.TimeUnit;

import static ru.alexeylisyutenko.cormen.chapter8.BucketSortPointsByOriginDistance.DoublePointRandom.generateRandomPoints;

@State(Scope.Thread)
public class BucketSortPointsByOriginDistanceBenchmark {

    @Param({"10", "50", "100", "200", "500", "1000", "10000", "100000"})
    int size;

    DoublePoint[] array;

    @Setup(Level.Iteration)
    public void setup() {
        array = generateRandomPoints(size);
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    @BenchmarkMode(Mode.AverageTime)
    @Warmup(iterations = 4, time = 3)
    @Measurement(iterations = 4, time = 3)
    public DoublePoint[] sortPointsByDistance() {
        return BucketSortPointsByOriginDistance.sort(array);
    }

}
