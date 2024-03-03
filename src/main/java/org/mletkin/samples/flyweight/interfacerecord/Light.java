package org.mletkin.samples.flyweight.interfacerecord;

/**
 * Interface for traffic light instances from record.
 */
public interface Light {

    // Defines the valid instances
    Light RED = new LightImpl(1);
    Light YELLOW = new LightImpl(2);
    Light GREEN = new LightImpl(3);

    int code();

    public static Light of(int code) {
        switch (code) {
        case 1:
            return RED;
        case 2:
            return YELLOW;
        case 3:
            return GREEN;
        default:
            return new LightImpl(code);
        }
    }

}