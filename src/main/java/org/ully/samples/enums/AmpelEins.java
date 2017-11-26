package org.ully.samples.enums;

public enum AmpelEins {

    ROT("rot"),
    GELB("gelb"),
    GRUEN("grün"),
    ;

    String farbe;

    private AmpelEins(String farbe) {
        this.farbe = farbe;
    }

    public String farbe() {
        return this.farbe;
    }
}
