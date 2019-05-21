package ru.alexeylisyutenko.cormen.chapter8;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("Duplicates")
public class BucketSortPointsByOriginDistance {

    /**
     * Sorts points in a unit circle by their distance from the origin.
     * <p>This algorithm assumes that points are uniformly distributed.</p>
     *
     * @param points array of points to sort
     * @return array of sorted points by their distance to the origin
     */
    public static DoublePoint[] sort(DoublePoint[] points) {
        int number = points.length;

        // Prepare n buckets.
        ArrayList<ArrayList<DoublePoint>> buckets = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            buckets.add(new ArrayList<>());
        }

        // Add points to corresponding buckets.
        for (int i = 0; i < number; i++) {
            DoublePoint currentPoint = points[i];
            int bucketIndex = (int) Math.ceil(number * (Math.pow(currentPoint.getX(), 2) + Math.pow(currentPoint.getY(), 2))) - 1;
            buckets.get(bucketIndex).add(currentPoint);
        }

        // Sort points in each bucket.
        for (int i = 0; i < buckets.size(); i++) {
            ArrayList<DoublePoint> bucket = buckets.get(i);
            sortPointsInBucket(bucket);
        }

        // Combine all points from all buckets.
        DoublePoint[] sortedPoints = new DoublePoint[number];
        int currentIndex = 0;
        for (int i = 0; i < number; i++) {
            ArrayList<DoublePoint> bucket = buckets.get(i);
            for (DoublePoint doublePoint : bucket) {
                sortedPoints[currentIndex] = doublePoint;
                currentIndex++;
            }
        }
        return sortedPoints;
    }

    private static void sortPointsInBucket(ArrayList<DoublePoint> bucket) {
        if (bucket.size() < 2) {
            return;
        }
        for (int i = 1; i < bucket.size(); i++) {
            DoublePoint current = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j).getDistance() > current.getDistance()) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, current);
        }
    }

    /**
     * Point in a unit circle.
     */
    public static class DoublePoint {
        private final double x;
        private final double y;
        private final double distance;

        public DoublePoint(double x, double y) {
            double d = calculateDistanceToOrigin(x, y);
            if (d == 0 || d > 1.0) {
                throw new IllegalArgumentException(String.format("Incorrect distance to the origin: %f. Point must be in a unit circle", d));
            }
            this.x = x;
            this.y = y;
            this.distance = d;
        }

        private double calculateDistanceToOrigin(double x, double y) {
            return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public double getDistance() {
            return distance;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ", " + distance + ")";
        }
    }

    /**
     * Utility class for generating random points in a unit circle.
     */
    public static class DoublePointRandom {
        public static DoublePoint[] generateRandomPoints(int size) {
            DoublePoint[] points = new DoublePoint[size];
            for (int i = 0; i < points.length; i++) {
                points[i] = generateRandomPoint();
            }
            return points;
        }

        public static DoublePoint generateRandomPoint() {
            ThreadLocalRandom random = ThreadLocalRandom.current();

            double r = Math.sqrt(random.nextDouble());
            double theta = random.nextDouble() * 2 * Math.PI;
            double x = r * Math.cos(theta);
            double y = r * Math.sin(theta);

            return new DoublePoint(x, y);
        }
    }

}
