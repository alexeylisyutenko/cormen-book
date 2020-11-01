package ru.alexeylisyutenko.other.ballsandboxes;

import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode
public class Box {
    private final Set<Integer> balls;

    private Box(Set<Integer> balls) {
        this.balls = balls;
    }

    public static Box of(Integer... balls) {
        return new Box(Set.of(balls));
    }

    public static Box of() {
        return new Box(new HashSet<>());
    }

    public Box putBall(int ball) {
        if (ball < 1) {
            throw new IllegalArgumentException("ball must be positive");
        }

        Set<Integer> newBalls = new HashSet<>(balls);
        newBalls.add(ball);
        return new Box(newBalls);
    }

    public Set<Integer> getBalls() {
        return Collections.unmodifiableSet(balls);
    }

    public int getSize() {
        return balls.size();
    }

    @Override
    public String toString() {
        return balls.stream().sorted().map(Object::toString).collect(Collectors.joining(", ", "(", ")"));
    }
}
