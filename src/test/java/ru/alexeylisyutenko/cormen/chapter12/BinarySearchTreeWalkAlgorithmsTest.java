package ru.alexeylisyutenko.cormen.chapter12;

import org.junit.jupiter.api.Test;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeWalkAlgorithms;
import ru.alexeylisyutenko.cormen.chapter12.simplebinarytree.BinaryTreeNodesDemoFactory;
import ru.alexeylisyutenko.cormen.chapter12.simplebinarytree.SimpleBinarySearchTreeNode;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinarySearchTreeWalkAlgorithmsTest {

    @Test
    void treeWalkDemo() {
        SimpleBinarySearchTreeNode<Integer> root = BinaryTreeNodesDemoFactory.createDemoBinarySearchTree();
        BinarySearchTreeWalkAlgorithms.postorderTreeWalk(root, System.out::println);
    }

    void inorderTreeWalkTest(List<Integer> expectedOrder, SimpleBinarySearchTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.inorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void inorderTreeWalkShouldWorkProperly() {
        inorderTreeWalkTest(List.of(2, 5, 5, 6, 7, 8), BinaryTreeNodesDemoFactory.createDemoBinarySearchTree());
        inorderTreeWalkTest(List.of(), BinaryTreeNodesDemoFactory.createEmptyNodeDemoBinarySearchTree());
        inorderTreeWalkTest(List.of(1), BinaryTreeNodesDemoFactory.createSingleNodeDemoBinarySearchTree());
        inorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeNodesDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        inorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeNodesDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

    void preorderTreeWalkTest(List<Integer> expectedOrder, SimpleBinarySearchTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.preorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void preorderTreeWalkShouldWorkProperly() {
        preorderTreeWalkTest(List.of(6, 5, 2, 5, 7, 8), BinaryTreeNodesDemoFactory.createDemoBinarySearchTree());
        preorderTreeWalkTest(List.of(), BinaryTreeNodesDemoFactory.createEmptyNodeDemoBinarySearchTree());
        preorderTreeWalkTest(List.of(1), BinaryTreeNodesDemoFactory.createSingleNodeDemoBinarySearchTree());
        preorderTreeWalkTest(List.of(6, 5, 4, 3, 2, 1), BinaryTreeNodesDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        preorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeNodesDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

    void postorderTreeWalkTest(List<Integer> expectedOrder, SimpleBinarySearchTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.postorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void postorderTreeWalkShouldWorkProperly() {
        postorderTreeWalkTest(List.of(2, 5, 5, 8, 7, 6), BinaryTreeNodesDemoFactory.createDemoBinarySearchTree());
        postorderTreeWalkTest(List.of(), BinaryTreeNodesDemoFactory.createEmptyNodeDemoBinarySearchTree());
        postorderTreeWalkTest(List.of(1), BinaryTreeNodesDemoFactory.createSingleNodeDemoBinarySearchTree());
        postorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeNodesDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        postorderTreeWalkTest(List.of(6, 5, 4, 3, 2, 1), BinaryTreeNodesDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

    void stackBasedInorderTreeWalkTest(List<Integer> expectedOrder, SimpleBinarySearchTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.stackBasedInorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void stackBasedInorderTreeWalkShouldWorkProperly() {
        stackBasedInorderTreeWalkTest(List.of(2, 5, 5, 6, 7, 8), BinaryTreeNodesDemoFactory.createDemoBinarySearchTree());
        stackBasedInorderTreeWalkTest(List.of(), BinaryTreeNodesDemoFactory.createEmptyNodeDemoBinarySearchTree());
        stackBasedInorderTreeWalkTest(List.of(1), BinaryTreeNodesDemoFactory.createSingleNodeDemoBinarySearchTree());
        stackBasedInorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeNodesDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        stackBasedInorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeNodesDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

    void iterationBasedInorderTreeWalkTest(List<Integer> expectedOrder, SimpleBinarySearchTreeNode<Integer> treeRoot) {
        List<Integer> walkResult = new ArrayList<>();
        BinarySearchTreeWalkAlgorithms.iterationBasedInorderTreeWalk(treeRoot, walkResult::add);
        assertArrayEquals(expectedOrder.toArray(), walkResult.toArray());
    }

    @Test
    void iterationBasedInorderTreeWalkShouldWorkProperly() {
        iterationBasedInorderTreeWalkTest(List.of(2, 5, 5, 6, 7, 8), BinaryTreeNodesDemoFactory.createDemoBinarySearchTree());
        iterationBasedInorderTreeWalkTest(List.of(), BinaryTreeNodesDemoFactory.createEmptyNodeDemoBinarySearchTree());
        iterationBasedInorderTreeWalkTest(List.of(1), BinaryTreeNodesDemoFactory.createSingleNodeDemoBinarySearchTree());
        iterationBasedInorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeNodesDemoFactory.createLeftDegenerateDemoBinarySearchTree());
        iterationBasedInorderTreeWalkTest(List.of(1, 2, 3, 4, 5, 6), BinaryTreeNodesDemoFactory.createRightDegenerateDemoBinarySearchTree());
    }

}