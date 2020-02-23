package ru.alexeylisyutenko.cormen.chapter14.pointofmaximumoverlap;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Comparator;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public final class PointOfMaximumOverlapTreeKey implements Comparable<PointOfMaximumOverlapTreeKey> {
    private static final Comparator<PointOfMaximumOverlapTreeKey> COMPARATOR =
            Comparator.comparing(PointOfMaximumOverlapTreeKey::getEndpoint)
                    .thenComparing(Comparator.comparing(PointOfMaximumOverlapTreeKey::getValue).reversed());

    private final int endpoint;
    private final int value;

    @Override
    public int compareTo(PointOfMaximumOverlapTreeKey o) {
        return COMPARATOR.compare(this, o);
    }
}
