package ru.alexeylisyutenko.cormen.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class QuickSortParallel {

    public static void sort(int[] array) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
        ForkJoinPool.commonPool().invoke(new QuickSortRecursiveAction(atomicIntegerArray, 0, array.length - 1));
        copyAtomicIntegerArrayToSourceArray(array, atomicIntegerArray);
    }

    private static void copyAtomicIntegerArrayToSourceArray(int[] array, AtomicIntegerArray atomicIntegerArray) {
        for (int i = 0; i < array.length; i++) {
            array[i] = atomicIntegerArray.get(i);
        }
    }

    private static class QuickSortRecursiveAction extends RecursiveAction {
        private static final int THRESHOLD = 1 << 13;

        private final AtomicIntegerArray atomicIntegerArray;
        private final int p;
        private final int r;

        QuickSortRecursiveAction(AtomicIntegerArray atomicIntegerArray, int p, int r) {
            this.atomicIntegerArray = atomicIntegerArray;
            this.p = p;
            this.r = r;
        }

        @Override
        protected void compute() {
            if (r - p < THRESHOLD) {
                directQuickSort(p, r);
            } else {
                int q = partition(p, r);
                QuickSortRecursiveAction task1 = new QuickSortRecursiveAction(atomicIntegerArray, p, q - 1);
                QuickSortRecursiveAction task2 = new QuickSortRecursiveAction(atomicIntegerArray, q + 1, r);
                task1.fork();
                task2.compute();
                task1.join();
            }
        }

        private void directQuickSort(int p, int r) {
            while (p < r) {
                int q = partition(p, r);
                directQuickSort(p, q - 1);
                p = q + 1;
            }
        }

        private int partition(int p, int r) {
            int x = atomicIntegerArray.get(r);
            int i = p - 1;
            for (int j = p; j < r; j++) {
                if (atomicIntegerArray.get(j) <= x) {
                    i++;
                    exchange(i, j);
                }
            }
            exchange(i + 1, r);
            return i + 1;
        }

        private void exchange(int firstIndex, int secondIndex) {
            int temp = atomicIntegerArray.get(firstIndex);
            atomicIntegerArray.set(firstIndex, atomicIntegerArray.get(secondIndex));
            atomicIntegerArray.set(secondIndex, temp);
        }

    }

}
