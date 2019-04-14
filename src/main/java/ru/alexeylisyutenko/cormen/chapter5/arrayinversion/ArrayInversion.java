package ru.alexeylisyutenko.cormen.chapter5.arrayinversion;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class ArrayInversion {

    public static void main(String[] args) {
        ArrayInversion arrayInversion = new ArrayInversion();
        arrayInversion.testInversionSizeExpectedValue(50, 1000000);
    }

    public void testInversionSizeExpectedValue(int size, int iterations) {
        long totalInversionCount = 0;

        for (int i = 0; i < iterations; i++) {
            int[] numbers = generateRandomPermutation(size);
            long inversions = calculateInversions(numbers);
            totalInversionCount += inversions;
        }

        System.out.println("Expected value: " + (double) totalInversionCount / iterations);
    }

    public long calculateInversions(int[] numbers) {
        long inversionCount = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] > numbers[j]) {
                    inversionCount++;
                }
            }
        }
        return inversionCount;
    }

    public int[] generateRandomPermutation(int size) {
        List<Integer> numbers = IntStream.range(1, size + 1).boxed().collect(toList());
        Collections.shuffle(numbers);
        return numbers.stream().mapToInt(i -> i).toArray();
    }

    private Pair<Integer, Integer> generateTwoRandomNumbers(int size) {
        int firstNumber = RandomUtils.nextInt(1, size + 1);
        int secondNumber = firstNumber;
        while (secondNumber == firstNumber) {
            secondNumber = RandomUtils.nextInt(1, size + 1);
        }
        return Pair.of(firstNumber, secondNumber);
    }

    public void testFirstIsGreaterThanSecondProbability(int iterations) {
        long successes = 0L;
        for (int i = 0; i < iterations; i++) {
            Pair<Integer, Integer> integerIntegerPair = generateTwoRandomNumbers(100);
            if (integerIntegerPair.getLeft() > integerIntegerPair.getRight()) {
                successes++;
            }
        }
        System.out.println("Probability: " + (double) successes / iterations);
    }

}
