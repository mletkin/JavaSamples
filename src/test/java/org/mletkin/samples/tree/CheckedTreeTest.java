package org.mletkin.samples.tree;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mletkin.samples.tree.CheckedTreeImpl;
import org.mletkin.samples.tree.CollisionException;
import org.mletkin.samples.tree.Tree;
import org.mletkin.samples.tree.TreeException;

class CheckedTreeTest {

    private Tree<Integer, String> tree = new CheckedTreeImpl<>();

    @Test
    void nullPathThrowsNullPointerExeption() {
        assertThatExceptionOfType(TreeException.class).isThrownBy(() -> tree.add(null, "regular"));
    }

    @ParameterizedTest
    @MethodSource("values")
    void collisionThrowsException(List<Integer> path) {
        tree.add(path, "regular");
        assertThatExceptionOfType(CollisionException.class) //
                .isThrownBy(() -> tree.add(path, "collision")) //
                .matches(e -> ((CollisionException) e).level() == path.size());
    }

    @ParameterizedTest
    @MethodSource("values")
    void firstLevelCanBeSetValueIsNull(List<Integer> path) {
        tree.add(path, null);
        tree.add(path, "eins");
        assertThat(tree.find(path)).isEqualTo("eins");
    }

    @ParameterizedTest
    @MethodSource("values")
    void firstLevelCanBeSetWhenValueIsTheSame(List<Integer> path) {
        tree.add(path, "regular");
        tree.add(path, "regular");
        assertThat(tree.find(path)).isEqualTo("regular");
    }

    static Stream<Arguments> values() {
        return Stream.of( //
                Arguments.of(List.of(1)), //
                Arguments.of(List.of(1, 2)), //
                Arguments.of(List.of(1, 2, 3)), //
                Arguments.of(List.of(1, 2, 3, 4)) //
        );
    }
}
