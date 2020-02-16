package ru.alexeylisyutenko.cormen.chapter14.intervaltree;

import java.util.Optional;

/**
 * Dynamic set of closed intervals. Cormen 14.3.
 */
public interface IntervalTree {
    /**
     * Add a given interval to the tree.
     *
     * @param interval interval to add
     */
    void intervalInsert(Interval interval);

    /**
     * Deletes a given interval from the tree is such interval exists, or throws exception otherwise.
     *
     * @param interval interval to delete
     */
    void intervalDelete(Interval interval);

    /**
     * Returns an interval which overlaps a given interval.
     *
     * @param interval interval that is used to search an interval in the tree
     * @return optional with an interval which overlaps a given interval, or empty optional if there is no such interval
     */
    Optional<Interval> intervalSearch(Interval interval);

    /**
     * Prints current tree to the "standard" output stream.
     */
    void print();
}
