package ru.alexeylisyutenko.maximumsubarray;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class MaximumSubarrayInfo {
    private final int maxLeft;
    private final int maxRight;
    private final long sum;
}
