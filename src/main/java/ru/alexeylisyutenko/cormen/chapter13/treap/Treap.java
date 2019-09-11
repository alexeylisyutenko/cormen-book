package ru.alexeylisyutenko.cormen.chapter13.treap;

import org.apache.commons.lang3.RandomUtils;
import ru.alexeylisyutenko.cormen.chapter12.base.AbstractBinarySearchTree;
import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTree;

import java.util.Objects;

public class Treap<K extends Comparable<K>> extends AbstractBinarySearchTree<K, TreapNode<K>> implements BinarySearchTree<K> {
    @Override
    public void insert(K key) {
        // Regular insertion.
        Objects.requireNonNull(key, "key cannot be null");

        TreapNode<K> previous = null;
        TreapNode<K> current = root;
        while (current != null) {
            previous = current;
            if (key.compareTo(current.getKey()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        DefaultTreapNode<K> nodeToInsert = new DefaultTreapNode<>();
        nodeToInsert.setKey(key);
        nodeToInsert.setLeft(null);
        nodeToInsert.setRight(null);
        nodeToInsert.setPriority(RandomUtils.nextInt());

        if (previous == null) {
            nodeToInsert.setParent(null);
            root = nodeToInsert;
        } else if (key.compareTo(previous.getKey()) < 0) {
            nodeToInsert.setParent(previous);
            previous.setLeft(nodeToInsert);
        } else {
            nodeToInsert.setParent(previous);
            previous.setRight(nodeToInsert);
        }

        // Fixup.
        treapFixup(nodeToInsert);
    }

    private void treapFixup(DefaultTreapNode<K> insertedNode) {
        TreapNode<K> current = insertedNode;
        TreapNode<K> parent = current.getParent();
        while (parent != null && current.getPriority() <= parent.getPriority()) {
            if (parent.getLeft() == current) {
                rotateRight(parent);
            } else {
                rotateLeft(parent);
            }
            parent = current.getParent();
        }
    }

    @Override
    public void delete(K key) {
        throw new IllegalStateException("Not implemented yet");
    }

    @Override
    protected TreapNode<K> getNil() {
        return null;
    }
}
