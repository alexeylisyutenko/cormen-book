package ru.alexeylisyutenko.other.ballsandboxes;

import com.google.common.collect.ImmutableList;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class Composition {
    protected final List<Box> boxes;

    protected Composition(List<Box> boxes) {
        this.boxes = boxes;
    }

    public static Composition ofSize(int boxCount) {
        if (boxCount < 0) {
            throw new IllegalArgumentException("boxCount cannot be negative");
        }

        ImmutableList.Builder<Box> listBuilder = ImmutableList.builder();
        for (int i = 0; i < boxCount; i++) {
            listBuilder.add(Box.of());
        }
        return new Composition(listBuilder.build());
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public Composition putBallIntoBox(int box, int ball) {
        if (box < 1 || box > boxes.size()) {
            throw new IllegalArgumentException("Incorrect box number");
        }

        ArrayList<Box> newBoxes = new ArrayList<>(this.boxes);

        Box boxWithBallAdded = newBoxes.get(box - 1).putBall(ball);
        newBoxes.set(box - 1, boxWithBallAdded);

        return new Composition(ImmutableList.copyOf(newBoxes));
    }

    public int getBoxCount() {
        return boxes.size();
    }

    public Box getBox(int box) {
        return boxes.get(box - 1);
    }

    @Override
    public String toString() {
        return boxes.stream().map(Box::toString).collect(Collectors.joining(" "));
    }
}
