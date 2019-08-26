package ru.alexeylisyutenko.cormen.chapter12.other;

import org.junit.jupiter.api.Test;

public class CompleteBinarySearchTreeAverageDepth {

    @Test
    void calculateAverageDepth() {
        int numberOfNodes = (1 << 20) - 1;
        System.out.println("Number of nodes:" + numberOfNodes);

        int height = (int) Math.floor(Math.log(numberOfNodes) / Math.log(2));
        System.out.println("Height: " + height);

        long sumOfDepths = 0;

        int levels = height + 1;
        for (int i = 0; i < levels; i++) {
            sumOfDepths = sumOfDepths + (i * (1 << i));
        }

        double average = (double) sumOfDepths / numberOfNodes;
        System.out.println("Average depth: " + average);
        System.out.println("Upper bound on average depth: " + calculateUpperBound(numberOfNodes));
    }

    private double calculateUpperBound(int numberOfNodes) {
        int height = (int) Math.floor(Math.log(numberOfNodes) / Math.log(2));

        double p1 = (height + 1) * (1 << (height + 1)) / Math.log(2);
        double p2 = (1 << (height + 1)) / (Math.log(2) * Math.log(2));
        double p3 = 1 / (Math.log(2) * Math.log(2));

        double bound = (p1 - p2 + p3) / numberOfNodes;

        return bound;
    }
}
