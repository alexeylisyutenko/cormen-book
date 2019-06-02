package ru.alexeylisyutenko.cormen.chapter7;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicIntegerArray;

import static ru.alexeylisyutenko.helper.Helpers.exchange;

public class QuickSortParallel {

    public static void sort(int[] array) {
//        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(array);
//        atomicIntegerArray.get


        ForkJoinPool.commonPool().invoke(new QuickSortRecursiveAction(array, 0, array.length - 1));
    }

    private static class QuickSortRecursiveAction extends RecursiveAction {
        private static final int THRESHOLD = 1 << 13;

        private final int[] array;
        private final int p;
        private final int r;

        QuickSortRecursiveAction(int[] array, int p, int r) {
            this.array = array;
            this.p = p;
            this.r = r;
        }

        @Override
        protected void compute() {
            if (r - p < THRESHOLD) {
                synchronizedDirectQuickSort();
            } else {
                int q = synchronizedPartition();
                QuickSortRecursiveAction task1 = new QuickSortRecursiveAction(array, p, q - 1);
                QuickSortRecursiveAction task2 = new QuickSortRecursiveAction(array, q + 1, r);
                task1.fork();
                task2.compute();
                task1.join();
            }
        }

        private void synchronizedDirectQuickSort() {
            synchronized (array) {
                directQuickSort(array, p, r);
            }
        }

        private int synchronizedPartition() {
            synchronized (array) {
                return partition(array, p, r);
            }
        }

        private void directQuickSort(int[] array, int p, int r) {
            while (p < r) {
                int q = partition(array, p, r);
                directQuickSort(array, p, q - 1);
                p = q + 1;
            }
        }

        private int partition(int[] array, int p, int r) {
            int x = array[r];
            int i = p - 1;
            for (int j = p; j < r; j++) {
                if (array[j] <= x) {
                    i++;
                    exchange(array, i, j);
                }
            }
            exchange(array, i + 1, r);
            return i + 1;
        }

    }

}
