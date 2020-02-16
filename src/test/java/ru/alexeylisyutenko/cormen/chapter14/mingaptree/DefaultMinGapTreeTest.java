package ru.alexeylisyutenko.cormen.chapter14.mingaptree;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter14.DefaultOrderStatisticTree;
import ru.alexeylisyutenko.cormen.chapter14.OrderStatisticTree;

import static org.junit.jupiter.api.Assertions.*;

class DefaultMinGapTreeTest {

    private MinGapTree tree;

    @BeforeEach
    void setup() {
        tree = new DefaultMinGapTree();
    }

    @Test
    void demo() {
        tree.insert(1);
        tree.insert(5);
        tree.insert(9);
        tree.insert(15);
        tree.insert(18);
        tree.insert(22);

        tree.print();

        System.out.println("Minimum gap: " + tree.minGap());
    }

    @Test
    void randomizedDemo() {
        int elements = 4;
        for (int i = 0; i < elements; i++) {
            tree.insert(RandomUtils.nextInt(0, 10));
        }
        tree.print();

        tree.inorderWalk(number -> System.out.print(number + " "));
        System.out.println();

        System.out.println("Minimum gap: " + tree.minGap());
        System.out.println();
    }

}