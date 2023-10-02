package org.mletkin.samples.tree;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mletkin.samples.tree.Tree;
import org.mletkin.samples.tree.TreeImpl;

class TreeTest {

    private Tree<Integer, String> tree = new TreeImpl<>();
    {
        tree.add(List.of(1), "eins");
        tree.add(List.of(2), "zwei");
        tree.add(List.of(3), "drei");
        tree.add(List.of(1, 2), "eins-zwei");
        tree.add(List.of(1, 2, 3), "eins-zwei-drei");
        tree.add(List.of(1, 3), "eins-drei");
    }

    @Test
    void firstLevel() {
        assertThat(tree.find(List.of(1))).isEqualTo("eins");
        assertThat(tree.find(List.of(2))).isEqualTo("zwei");
        assertThat(tree.find(List.of(3))).isEqualTo("drei");
    }

    @Test
    void secondLevel() {
        assertThat(tree.find(List.of(1, 2))).isEqualTo("eins-zwei");
        assertThat(tree.find(List.of(1, 3))).isEqualTo("eins-drei");
    }

    @Test
    void thirdLevel() {
        assertThat(tree.find(List.of(1, 2, 3))).isEqualTo("eins-zwei-drei");
    }

    @Test
    void notFound() {
        assertThat(tree.find(List.of(4))).isNull();
        assertThat(tree.find(List.of(1, 2, 7, 9, 0))).isNull();
    }

    @Test
    void keinFehlerBeiLeeremBaum() {
        Tree<Integer, String> tree = new TreeImpl<>();
        assertThat(tree.find(List.of(1, 2, 7, 9, 0))).isNull();
    }
}
