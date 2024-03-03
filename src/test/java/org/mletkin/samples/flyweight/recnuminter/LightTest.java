package org.mletkin.samples.flyweight.recnuminter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mletkin.samples.flyweight.recnuminter.Light;

class LightTest {

    @ParameterizedTest
    @EnumSource(value = LightValid.class)
    void enumObjekteWerdenErzeugt(LightValid value) {
        assertThat(Light.of(value.code())) //
                .isOfAnyClassIn(LightValid.class) //
                .isSameAs(value);
    }

    @ParameterizedTest
    @EnumSource(value = LightValid.class)
    void enumObjekteSindValid(LightValid value) {
        assertThat(value.isValid()).isTrue();
    }

    @ParameterizedTest
    @ValueSource(ints = { -1, 0, 4, 10, 1500 })
    void istNotEnum(int value) {
        assertThat(Light.of(value)) //
                .isNotOfAnyClassIn(LightValid.class) //
                .matches(v -> v.code() == value) //
                .matches(v -> !v.isValid());

    }

}
