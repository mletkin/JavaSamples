package org.ully.samples.tree;

import java.util.List;

interface Tree<P, V> {

    void add(List<P> path, V value);

    V find(List<P> path);

}
