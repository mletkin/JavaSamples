package org.mletkin.samples.flyweight.recnum;

import java.util.stream.Stream;

/**
 * Enum for the valid instances.
 *
 * Only visible for the factor method.
 */
public enum Lights implements Light {

    RED(1),
    YELLOW(2),
    GREEN(3)

    ;

    private int code;

    private Lights(int code) {
        this.code = code;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int code() {
        return this.code;
    }

    /**
     * Returns an instance for a valid code.
     */
    public static Light of(int code) {
        var obj = Stream.of(values()).filter(v -> v.code == code).findAny().orElse(null);
        return obj != null ? obj : new LightRec(code);
    }

    private record LightRec(int code) implements Light {

        @Override
        public boolean isValid() {
            return false;
        }

    }

}
