package org.mletkin.samples.tree;

import static java.util.Optional.ofNullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementierung eines n-adischen Suchbaums.
 * <ul>
 * <li>Jede Edge ist mit einem Identifier vom Typ {@code E} bezeichnet
 * <li>Jeder Node kann einen Wert haben vom Typ {@code V}
 * </ul>
 *
 * @param <E>
 *                Typ des edge identifier
 * @param <V>
 *                Typ des node value
 */
public class TreeImpl<E, V> implements Tree<E, V> {

    private Node<E, V> root = new Node<>();

    private static class Node<E, V> {
        private Map<E, Node<E, V>> children = new HashMap<>();
        private V value;

        public Node<E, V> find(E edge) {
            return children.get(edge);
        }

        public Node<E, V> findOrCreate(E edge) {
            return ofNullable(children.get(edge)).orElseGet(() -> add(edge, new Node<>()));
        }

        private Node<E, V> add(E path, Node<E, V> child) {
            children.put(path, child);
            return child;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public V value() {
            return value;
        }
    }

    private Node<E, V> findOrCreateNode(List<E> path) {
        var it = root;
        for (E edge : path) {
            it = it.findOrCreate(edge);
        }
        return it;
    }

    @Override
    public void add(List<E> path, V value) {
        findOrCreateNode(path).setValue(value);
    }

    private Optional<Node<E, V>> findNode(List<E> path) {
        var it = root;
        for (E edge : path) {
            it = it.find(edge);
            if (it == null) {
                return Optional.empty();
            }
        }
        return Optional.of(it);
    }

    @Override
    public V find(List<E> path) {
        return findNode(path).map(Node::value).orElse(null);
    }

}
