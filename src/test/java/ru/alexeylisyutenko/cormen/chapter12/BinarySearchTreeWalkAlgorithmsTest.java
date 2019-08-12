package ru.alexeylisyutenko.cormen.chapter12;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeWalkAlgorithmsTest {

    @Test
    void treeWalkDemo() {
        BinaryTreeNode<Integer> root = BinaryTreeDemoFactory.createDemoBinarySearchTree();
        BinarySearchTreeWalkAlgorithms.postorderTreeWalk(root, System.out::println);
    }

    void inorderTreeWalkTest(List<Integer> expectedOrder, BinaryTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.inorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void inorderTreeWalkShouldWorkProperly() {
        inorderTreeWalkTest(List.of(2, 5, 5, 6, 7, 8), BinaryTreeDemoFactory.createDemoBinarySearchTree());
        inorderTreeWalkTest(List.of(), BinaryTreeDemoFactory.createEmptyNodeDemoBinarySearchTree());
        inorderTreeWalkTest(List.of(1), BinaryTreeDemoFactory.createSingleNodeDemoBinarySearchTree());
        inorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        inorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

    void preorderTreeWalkTest(List<Integer> expectedOrder, BinaryTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.preorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void preorderTreeWalkShouldWorkProperly() {
        preorderTreeWalkTest(List.of(6, 5, 2, 5, 7, 8), BinaryTreeDemoFactory.createDemoBinarySearchTree());
        preorderTreeWalkTest(List.of(), BinaryTreeDemoFactory.createEmptyNodeDemoBinarySearchTree());
        preorderTreeWalkTest(List.of(1), BinaryTreeDemoFactory.createSingleNodeDemoBinarySearchTree());
        preorderTreeWalkTest(List.of(6, 5, 4, 3, 2, 1), BinaryTreeDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        preorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

    void postorderTreeWalkTest(List<Integer> expectedOrder, BinaryTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.postorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void postorderTreeWalkShouldWorkProperly() {
        postorderTreeWalkTest(List.of(2, 5, 5, 8, 7, 6), BinaryTreeDemoFactory.createDemoBinarySearchTree());
        postorderTreeWalkTest(List.of(), BinaryTreeDemoFactory.createEmptyNodeDemoBinarySearchTree());
        postorderTreeWalkTest(List.of(1), BinaryTreeDemoFactory.createSingleNodeDemoBinarySearchTree());
        postorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        postorderTreeWalkTest(List.of(6, 5, 4, 3, 2, 1), BinaryTreeDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

    void stackBasedInorderTreeWalkTest(List<Integer> expectedOrder, BinaryTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.stackBasedInorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void stackBasedInorderTreeWalkShouldWorkProperly() {
        stackBasedInorderTreeWalkTest(List.of(2, 5, 5, 6, 7, 8), BinaryTreeDemoFactory.createDemoBinarySearchTree());
        stackBasedInorderTreeWalkTest(List.of(), BinaryTreeDemoFactory.createEmptyNodeDemoBinarySearchTree());
        stackBasedInorderTreeWalkTest(List.of(1), BinaryTreeDemoFactory.createSingleNodeDemoBinarySearchTree());
        stackBasedInorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        stackBasedInorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

    void iterationBasedInorderTreeWalkTest(List<Integer> expectedOrder, BinaryTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.iterationBasedInorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void iterationBasedInorderTreeWalkShouldWorkProperly() {
        iterationBasedInorderTreeWalkTest(List.of(2, 5, 5, 6, 7, 8), BinaryTreeDemoFactory.createDemoBinarySearchTree());
        iterationBasedInorderTreeWalkTest(List.of(), BinaryTreeDemoFactory.createEmptyNodeDemoBinarySearchTree());
        iterationBasedInorderTreeWalkTest(List.of(1), BinaryTreeDemoFactory.createSingleNodeDemoBinarySearchTree());
        iterationBasedInorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        iterationBasedInorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

}