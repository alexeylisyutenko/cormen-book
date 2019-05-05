package ru.alexeylisyutenko.cormen.chapter7;

import org.apache.commons.lang3.tuple.Pair;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

public class QuickSortWithEqualElements {

    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    public static void quickSort(int[] array, int p, int r) {
        while (p < r) {
            Pair<Integer, Integer> partition = partition(array, p, r);
            quickSort(array, p, partition.getLeft() - 1);
            p = partition.getRight() + 1;
        }
    }

    public static Pair<Integer, Integer> partition(int[] array, int p, int r) {
        int x = array[r];
        exchange(array, r, p);
        int i = p;
        int lt = p;
        int gt = r;
        while (i <= gt) {
            if (array[i] < x) {
                exchange(array, lt, i);
                lt++;
                i++;
            } else if (array[i] > x) {
                exchange(array, i, gt);
                gt--;
            } else if (array[i] == x) {
                i++;
            }
        }
        return Pair.of(lt, gt);
    }

}
