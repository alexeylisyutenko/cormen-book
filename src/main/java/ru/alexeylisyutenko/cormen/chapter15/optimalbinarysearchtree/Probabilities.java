package ru.alexeylisyutenko.cormen.chapter15.optimalbinarysearchtree;

import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;


/**
 * Class which contains probabilities required for optimal binary search tree construction. Cormen 15.5.
 */
@Getter
@ToString
public class Probabilities {
    public static final double THRESHOLD = 0.0001;
    private final double[] hit;
    private final double[] miss;

    public Probabilities(double[] hit, double[] miss) {
        Objects.requireNonNull(hit);
        Objects.requireNonNull(miss);

        if (hit.length == 0) {
            throw new IllegalArgumentException("There must be at least one hit probability");
        }

        if (miss.length != hit.length + 1) {
            throw new IllegalArgumentException("miss.length must be equal to hit.length + 1");
        }

        double sum = Arrays.stream(hit).sum() + Arrays.stream(miss).sum();
        if (Math.abs(sum - 1.0) > THRESHOLD) {
            throw new IllegalArgumentException("Sum of all probabilities must be equal to 1.0");
        }

        this.hit = hit;
        this.miss = miss;
    }

    public double getHitProbability(int index) {
        return hit[index - 1];
    }

    public double getMissProbability(int index) {
        return miss[index];
    }
}
