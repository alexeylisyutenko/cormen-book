package ru.alexeylisyutenko.cormen.chapter12.utils;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BinaryTreePrinter<T> {

    private final BinarySearchTreeNode<T> root;
    private final BinarySearchTreeNode<T> nil;

    private BinaryTreePrinter(BinarySearchTreeNode<T> root, BinarySearchTreeNode<T> nil) {
        this.root = root;
        this.nil = nil;
    }

    public static <T extends Comparable<?>> void printNode(BinarySearchTreeNode<T> root) {
        printNode(root, null);
    }

    public static <T extends Comparable<?>> void printNode(BinarySearchTreeNode<T> root, BinarySearchTreeNode<T> nil) {
        BinaryTreePrinter<T> treePrinter = new BinaryTreePrinter<>(root, nil);
        treePrinter.doPrint();
    }

    private void doPrint() {
        int maxLevel = maxLevel(root);
        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private void printNodeInternal(List<BinarySearchTreeNode<T>> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || isAllElementsNull(nodes)) {
            return;
        }

        int floor = maxLevel - level;
        int edgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces);

        List<BinarySearchTreeNode<T>> newNodes = new ArrayList<>();
        for (BinarySearchTreeNode<T> node : nodes) {
            if (!isNull(node)) {
                System.out.print(node.toString());
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(nil);
                newNodes.add(nil);
                System.out.print(" ");
            }

            // Between spaces printing.
            int currentBetweenSpaces;
            if (isNull(node)) {
                currentBetweenSpaces = betweenSpaces;
            } else {
                currentBetweenSpaces = Math.max(0, betweenSpaces - node.toString().length() + 1);
            }
            printWhitespaces(currentBetweenSpaces);
        }
        System.out.println();

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i);
                if (isNull(nodes.get(j))) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (!isNull(nodes.get(j).getLeft())) {
                    System.out.print("/");
                } else {
                    printWhitespaces(1);
                }

                printWhitespaces(i + i - 1);

                if (!isNull(nodes.get(j).getRight())) {
                    System.out.print("\\");
                } else {
                    printWhitespaces(1);
                }

                printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println();
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private boolean isNull(BinarySearchTreeNode<T> node) {
        return node == nil;
    }

    private void printWhitespaces(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private int maxLevel(BinarySearchTreeNode<T> node) {
        if (isNull(node)) {
            return 0;
        }
        return Math.max(maxLevel(node.getLeft()), maxLevel(node.getRight())) + 1;
    }

    private boolean isAllElementsNull(List<BinarySearchTreeNode<T>> list) {
        for (BinarySearchTreeNode<T> node : list) {
            if (!isNull(node)) {
                return false;
            }
        }
        return true;
    }

}
