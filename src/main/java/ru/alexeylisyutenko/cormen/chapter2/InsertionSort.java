package ru.alexeylisyutenko.cormen.chapter2;

public class InsertionSort {

    public static void sort(int[] array) {
        if (array.length < 2) {
            return;
        }

        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && current < array[j]) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }

}
