package ru.alexeylisyutenko.cormen.chapter10.tree;

public class TreePrintConstantExtraSpace {

    public static void printTree(TreeNode root) {
        TreeNode previous = null;
        TreeNode current = root;
        while (current != null) {

            TreeNode next;
            if (previous == current.getParent()) {
                // Print node key.
                System.out.println(current.getKey());

                // We're coming from parent.
                if (current.getLeft() == null && current.getRight() == null) {
                    // If there are no children then we go back to the parent.
                    next = current.getParent();
                } else if (current.getLeft() == null) {
                    // If there is only right child then we go to the right.
                    next = current.getRight();
                } else {
                    // Otherwise we go to the left.
                    next = current.getLeft();
                }

            } else if (previous == current.getLeft()) {

                // We're coming from the left child.
                if (current.getRight() == null) {
                    // If there is no right child then we go to the parent.
                    next = current.getParent();
                } else {
                    // Otherwise we go to the right child.
                    next = current.getRight();
                }

            } else if (previous == current.getRight()) {

                // We're coming from the right child.
                next = current.getParent();

            } else {
                throw new IllegalStateException("Incorrect branch");
            }

            previous = current;
            current = next;
        }
    }

}
