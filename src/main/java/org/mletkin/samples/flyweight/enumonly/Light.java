package org.mletkin.samples.flyweight.enumonly;

/**
 * Enum for traffic light instances.
 * <p>
 * invalid instances are not possible
 */
public enum Light {

    RED(1),
    YELLOW(2),
    GREEN(3)

    ;

    private int code;

    private Light(int code) {
        this.code = code;
    }

    public int code() {
        return this.code;
    }
}
