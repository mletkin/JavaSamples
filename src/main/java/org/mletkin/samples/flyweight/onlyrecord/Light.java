package org.mletkin.samples.flyweight.onlyrecord;

/**
 * Record as enum with invalid instances.
 */
public record Light(int code) {

    public static final Light RED = new Light(1);
    public static final Light YELLOW = new Light(2);
    public static final Light GREEN = new Light(3);

    /**
     * Factory method for new instances.
     */
    public static Light of(int code) {
        switch (code) {
        case 1:
            return RED;
        case 2:
            return YELLOW;
        case 3:
            return GREEN;
        default:
            return new Light(code);
        }
    }

    /**
     * Checks the validity of the object.
     */
    public boolean isValid() {
        return code == 1 || code == 2 || code == 3;
    }

}
