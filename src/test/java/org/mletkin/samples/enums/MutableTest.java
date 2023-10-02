package org.mletkin.samples.enums;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mletkin.samples.enums.Mutable;

class MutableTest {

    @Test
    void changeValue() {
        Mutable.ONE.value = 12;

        assertThat(Mutable.ONE.value).isEqualTo(12);
    }

}
