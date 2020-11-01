package ru.alexeylisyutenko.other.ballsandboxes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class BallsAndBoxes {
    public static int distBallsDistBoxes(int ballCount, int boxCount) {
        return generateAllPossibleCompositions(ballCount, boxCount).size();
    }

    public static int indistBallsDistBoxes(int ballCount, int boxCount) {
        return countCompositions(ballCount, boxCount, (c1, c2) -> {
            for (int i = 1; i <= c1.getBoxCount(); i++) {
                if (c1.getBox(i).getSize() != c2.getBox(i).getSize()) {
                    return false;
                }
            }
            return true;
        });
    }

    public static int indistBallsIndistBoxes(int ballCount, int boxCount) {
        return countCompositions(ballCount, boxCount, (c1, c2) -> {
            Set<Integer> sizes1 = c1.getBoxes().stream().map(Box::getSize).collect(Collectors.toSet());
            Set<Integer> sizes2 = c2.getBoxes().stream().map(Box::getSize).collect(Collectors.toSet());
            return sizes1.equals(sizes2);
        });
    }

    public static int distBallsIndistBoxes(int ballCount, int boxCount) {
        return countCompositions(ballCount, boxCount, (c1, c2) -> {
            for (Box box : c1.getBoxes()) {
                if (!c2.getBoxes().contains(box)) {
                    return false;
                }
            }
            return true;
        });
    }

    public static int distBallsDistBoxesNoEmpty(int ballCount, int boxCount) {
        return (int) generateAllPossibleCompositions(ballCount, boxCount)
                .stream().filter(BallsAndBoxes::noEmptyBoxes).count();
    }

    public static int indistBallsDistBoxesNoEmpty(int ballCount, int boxCount) {
        return countCompositionsFiltered(ballCount, boxCount,
                (c1, c2) -> {
                    for (int i = 1; i <= c1.getBoxCount(); i++) {
                        if (c1.getBox(i).getSize() != c2.getBox(i).getSize()) {
                            return false;
                        }
                    }
                    return true;
                }, BallsAndBoxes::noEmptyBoxes);
    }

    public static int indistBallsIndistBoxesNoEmpty(int ballCount, int boxCount) {
        return countCompositionsFiltered(ballCount, boxCount, (c1, c2) -> {
            Set<Integer> sizes1 = c1.getBoxes().stream().map(Box::getSize).collect(Collectors.toSet());
            Set<Integer> sizes2 = c2.getBoxes().stream().map(Box::getSize).collect(Collectors.toSet());
            return sizes1.equals(sizes2);
        }, BallsAndBoxes::noEmptyBoxes);
    }

    public static int distBallsIndistBoxesNoEmpty(int ballCount, int boxCount) {
        return countCompositionsFiltered(ballCount, boxCount, (c1, c2) -> {
            for (Box box : c1.getBoxes()) {
                if (!c2.getBoxes().contains(box)) {
                    return false;
                }
            }
            return true;
        }, BallsAndBoxes::noEmptyBoxes);
    }

    private static int countCompositionsFiltered(int ballCount, int boxCount, BiPredicate<Composition, Composition> equalsFunction, Predicate<Composition> filter) {
        List<Composition> compositions = generateAllPossibleCompositions(ballCount, boxCount).stream().filter(filter).collect(Collectors.toList());

        ArrayList<Composition> filteredCompositions = new ArrayList<>(compositions.size());
        for (Composition composition : compositions) {
            boolean alreadyExists = filteredCompositions.stream().anyMatch(c -> equalsFunction.test(composition, c));
            if (!alreadyExists) {
                filteredCompositions.add(composition);
            }
        }

        return filteredCompositions.size();

    }

    private static int countCompositions(int ballCount, int boxCount, BiPredicate<Composition, Composition> equalsFunction) {
        return countCompositionsFiltered(ballCount, boxCount, equalsFunction, composition -> true);
    }

    public static List<Composition> generateAllPossibleCompositions(int ballCount, int boxCount) {
        if (ballCount < 0) {
            throw new IllegalArgumentException("ballCount cannot be negative");
        }
        if (boxCount < 0) {
            throw new IllegalArgumentException("boxCount cannot be negative");
        }

        List<Composition> compositions = new ArrayList<>();
        generateAllPossibleCompositionsAux(ballCount, boxCount, 1, Composition.ofSize(boxCount), compositions);
        return compositions;
    }

    private static void generateAllPossibleCompositionsAux(int ballCount, int boxCount, int currentBall,
                                                           Composition currentComposition, List<Composition> compositions) {
        if (currentBall > ballCount) {
            compositions.add(currentComposition);
            return;
        }
        for (int box = 1; box <= boxCount; box++) {
            Composition composition = currentComposition.putBallIntoBox(box, currentBall);
            generateAllPossibleCompositionsAux(ballCount, boxCount, currentBall + 1, composition, compositions);
        }
    }

    private static boolean noEmptyBoxes(Composition composition) {
        for (Box box : composition.getBoxes()) {
            if (box.getSize() == 0) {
                return false;
            }
        }
        return true;
    }
}
