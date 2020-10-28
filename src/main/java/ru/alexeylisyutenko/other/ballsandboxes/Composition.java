package ru.alexeylisyutenko.other.ballsandboxes;

import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class Composition {
    private final List<Box> boxes;

//        private final List<Set<Integer>> boxes2;

    public Composition(int boxCount) {
        if (boxCount < 0) {
            throw new IllegalArgumentException("boxCount cannot be negative");
        }

        this.boxes = new ArrayList<>(boxCount);
        for (int i = 0; i < boxCount; i++) {
//                this.boxes.add(new Box());
        }
    }

    public List<Box> getBoxes() {
        return Collections.unmodifiableList(boxes);
    }

    public Composition putBallIntoBox(int ball, int box) {
        if (box < 1 || box > boxes.size()) {
            throw new IllegalArgumentException("Incorrect box number");
        }


        // TODO: Finish this!

        return null;
    }

    @Override
    public String toString() {
        return boxes.stream().map(Box::toString).collect(Collectors.joining(", "));
    }
}
