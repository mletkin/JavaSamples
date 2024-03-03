package org.mletkin.samples.flyweight.recnuminter;

/**
 * Record as enum with invalid instances.
 */
public sealed interface Light permits LightValid, LightRec {

    int code();

    /**
     * Factory method for new instances.
     */
    public static Light of(int code) {
        var result = LightValid.of(code);
        return result == null ? new LightRec(code) : result;
    }

    /**
     * Checks the validity of the object.
     */
    public default boolean isValid() {
        return this.getClass().equals(LightValid.class);
    }

}
