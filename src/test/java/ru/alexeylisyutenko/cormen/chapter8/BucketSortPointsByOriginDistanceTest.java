package ru.alexeylisyutenko.cormen.chapter8;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter8.BucketSortPointsByOriginDistance.DoublePoint;

import java.util.Arrays;

import static ru.alexeylisyutenko.cormen.chapter8.BucketSortPointsByOriginDistance.DoublePointRandom.generateRandomPoints;

class BucketSortPointsByOriginDistanceTest {

    @Test
    void bucketSortPointsDemo() {
        DoublePoint[] points = generateRandomPoints(50000);
        System.out.println(Arrays.toString(points));
        DoublePoint[] sortedPoints = BucketSortPointsByOriginDistance.sort(points);
        System.out.println(Arrays.toString(sortedPoints));
    }

}