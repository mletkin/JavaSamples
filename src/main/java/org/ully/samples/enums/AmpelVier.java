package org.ully.samples.enums;

public enum AmpelVier {

    ROT,
    GELB,
    GRUEN,
    ;

    public String farbe() {
        switch (this) {
        case ROT:   return "rot";
        case GELB:  return "gelb";
        case GRUEN: return "gr�n";
        default: throw new IllegalStateException();
        }
    };
}
