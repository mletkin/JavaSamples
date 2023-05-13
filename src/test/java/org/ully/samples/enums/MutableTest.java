package org.ully.samples.enums;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MutableTest {

    @Test
    void changeValue() {
        Mutable.ONE.value = 12;

        assertThat(Mutable.ONE.value).isEqualTo(12);
    }

}
