package ru.alexeylisyutenko.other.ballsandboxes;

import org.junit.jupiter.api.Test;

import static ru.alexeylisyutenko.other.ballsandboxes.BallsAndBoxes.*;

class BallsAndBoxesTest {
    @Test
    void demo() {
        System.out.println("distBallsDistBoxes: " + distBallsDistBoxes(7, 4));
        System.out.println("indistBallsDistBoxes: " + indistBallsDistBoxes(10, 4));
        System.out.println("indistBallsIndistBoxes: " + indistBallsIndistBoxes(10, 4));
        System.out.println("distBallsIndistBoxes: " + distBallsIndistBoxes(7, 4));

        System.out.println("distBallsDistBoxesNoEmpty: " + distBallsDistBoxesNoEmpty(7, 4));
        System.out.println("indistBallsDistBoxesNoEmpty: " + indistBallsDistBoxesNoEmpty(10, 4));
        System.out.println("indistBallsIndistBoxesNoEmpty: " + indistBallsIndistBoxesNoEmpty(10, 4));
        System.out.println("distBallsIndistBoxesNoEmpty: " + distBallsIndistBoxesNoEmpty(7, 4));
    }
}