package ru.alexeylisyutenko.other;

public class PrintAllSubsets {

    public static void print(int[] elements) {
        printSubsetTailRecursion(elements, 0, "");
    }

    private static void printSubset(int[] elements, int index, String subset) {
        if (index == elements.length) {
            System.out.println("{" + subset + "}");
        } else {
            // Current element is in subset.
            printSubset(elements, index + 1, subset.isEmpty() ? subset + elements[index] : subset + ", " + elements[index]);

            // Current element is not in a subset.
            printSubset(elements, index + 1, subset);
        }
    }

    private static void printSubsetTailRecursion(int[] elements, int index, String subset) {
        while (index < elements.length) {
            printSubset(elements, index + 1, subset.isEmpty() ? subset + elements[index] : subset + ", " + elements[index]);
            index++;
        }
        System.out.println("{" + subset + "}");
    }


}
