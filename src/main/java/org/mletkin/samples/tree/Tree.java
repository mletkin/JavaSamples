package org.mletkin.samples.tree;

import java.util.List;

/**
 * Interface to an n-adic search tree.
 * <ul>
 * <li>Every edge has an identifier of type {@code E}
 * <li>Every node can have a value of type {@code V}
 * </ul>
 *
 * @param <E>
 *                edge identifier type
 * @param <V>
 *                node value type
 */
interface Tree<E, V> {

    /**
     * Find or create the path in the tree and set the value of the final node.
     *
     * @param path
     *                  a list of edge identifiers, defining the path
     * @param value
     *                  the value to set on the final node
     */
    void add(List<E> path, V value);

    /**
     * Find the path in the tree and return the value of the final node.
     *
     * @param path
     *                 a list of edge identifiers, defining the path
     * @return the value of the final node if set
     */
    V find(List<E> path);

}
