package ru.alexeylisyutenko.other.ballsandboxes;

import java.util.*;

public final class BallsAndBoxes {
    public static int findNumberOfDistBallsIndistBoxes(int balls, int boxes) {
        // Generate all possible combinations.


        return 0;
    }

    public static List<Composition> generateAllPossibleCompositions(int balls, int boxes) {
        List<Composition> compositions = new ArrayList<>();


        return compositions;
    }

    private static void generateAllPossibleCompositionsAux(int ballCount, int boxCount, int currentBall, Composition currentComposition, List<Composition> compositions) {
        if (currentBall > ballCount) {
            compositions.add(currentComposition);
            return;
        }

        for (int box = 0; box < boxCount; box++) {
            // Укладываем currentBall в соответствующую коробку.
            currentComposition.putBallIntoBox(currentBall, box);
        }

    }

    public static void main(String[] args) {
        Composition composition = new Composition(4);

    }

}
