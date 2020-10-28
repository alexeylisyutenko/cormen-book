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

    public static Box valueOf(Set<Integer> balls) {
        return new Box(new HashSet<>(balls));
    }

    public static Box valueOf() {
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

    @Override
    public String toString() {
        return balls.stream().map(Object::toString).collect(Collectors.joining(", ", "(", ")"));
    }
}
