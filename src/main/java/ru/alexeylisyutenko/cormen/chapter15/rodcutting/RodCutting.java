package ru.alexeylisyutenko.cormen.chapter15.rodcutting;

/**
 * Cormen. 15.1 Rod Cutting.
 */
public interface RodCutting {
    /**
     * Recursive top-down implementation.
     *
     * @param prices price list
     * @param size   size of a rod
     * @return best possible revenue
     */
    int cutRod(int[] prices, int size);
}
