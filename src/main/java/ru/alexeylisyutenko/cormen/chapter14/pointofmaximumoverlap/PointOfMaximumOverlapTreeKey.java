package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Comparator;

@Getter
@AllArgsConstructor
public final class PointOfMaximumOverlapTreeKey implements Comparable<PointOfMaximumOverlapTreeKey> {
    private static final Comparator<PointOfMaximumOverlapTreeKey> COMPARATOR =
            Comparator.comparing(PointOfMaximumOverlapTreeKey::getEndpoint).thenComparing(PointOfMaximumOverlapTreeKey::getValue);

    private final int endpoint;
    private final int value;

    @Override
    public int compareTo(PointOfMaximumOverlapTreeKey o) {
        return COMPARATOR.compare(this, o);
    }
}
