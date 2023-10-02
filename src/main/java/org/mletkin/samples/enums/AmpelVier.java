package org.mletkin.samples.enums;

public enum AmpelVier {

    ROT,
    GELB,
    GRUEN,
    ;

    public String farbe() {
        switch (this) {
        case ROT:   return "rot";
        case GELB:  return "gelb";
        case GRUEN: return "grün";
        default: throw new IllegalStateException();
        }
    };
}
