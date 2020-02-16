package ru.alexeylisyutenko.cormen.chapter14.base;

import ru.alexeylisyutenko.cormen.chapter12.base.BinarySearchTreeNode;
import ru.alexeylisyutenko.cormen.chapter13.RedBlackTreeNodeColor;

/**
 * Base node for all red-black tree based nodes.
 */
public interface RedBlackBasedSearchTreeNode<K, N extends RedBlackBasedSearchTreeNode<K, N>> extends BinarySearchTreeNode<K, N> {
    RedBlackTreeNodeColor getColor();

    void setColor(RedBlackTreeNodeColor color);
}
