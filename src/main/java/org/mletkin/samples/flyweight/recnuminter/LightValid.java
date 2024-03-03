package org.mletkin.samples.flyweight.recnuminter;

import java.util.stream.Stream;

/**
 * Enum for the valid instances.
 *
 * Only visible for the factor method.
 */
enum LightValid implements Light {

    RED(1),
    YELLOW(2),
    GREEN(3)

    ;

    private int code;

    private LightValid(int code) {
        this.code = code;
    }

    @Override
    public int code() {
        return this.code;
    }

    /**
     * Returns an instance for a valid code.
     */
    static Light of(int code) {
        return Stream.of(values()).filter(v -> v.code == code).findFirst().orElse(null);
    }

}
