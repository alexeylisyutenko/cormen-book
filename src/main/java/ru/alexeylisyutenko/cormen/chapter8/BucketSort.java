package ru.alexeylisyutenko.cormen.chapter8;

import java.util.ArrayList;

public class BucketSort {

    public static void sort(double[] array) {
        int number = array.length;

        // Prepare n buckets.
        ArrayList<ArrayList<Double>> buckets = new ArrayList<>(number);
        for (int i = 0; i < number; i++) {
            buckets.add(new ArrayList<>(2));
        }

        // Populate buckets with data from the input array.
        for (int i = 0; i < array.length; i++) {
            int bucketIndex = (int) (number * array[i]);
            buckets.get(bucketIndex).add(array[i]);
        }

        // Sort numbers in each bucket.
        for (int i = 0; i < buckets.size(); i++) {
            ArrayList<Double> bucket = buckets.get(i);
            sortNumbersInBucket(bucket);
        }

        // Combine all buckets.
        int currentIndex = 0;
        for (int i = 0; i < number; i++) {
            ArrayList<Double> bucket = buckets.get(i);
            for (int j = 0; j < bucket.size(); j++) {
                array[currentIndex] = bucket.get(j);
                currentIndex++;
            }
        }
    }

    private static void sortNumbersInBucket(ArrayList<Double> bucket) {
        if (bucket.size() < 2) {
            return;
        }
        for (int i = 1; i < bucket.size(); i++) {
            Double current = bucket.get(i);
            int j = i - 1;
            while (j >= 0 && bucket.get(j) > current) {
                bucket.set(j + 1, bucket.get(j));
                j--;
            }
            bucket.set(j + 1, current);
        }
    }

}
