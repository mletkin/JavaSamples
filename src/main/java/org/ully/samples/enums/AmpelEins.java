package org.ully.samples.enums;

public enum AmpelEins {

    ROT("rot"),
    GELB("gelb"),
    GRUEN("gr�n"),
    ;

    String farbe;

    private AmpelEins(String farbe) {
        this.farbe = farbe;
    }

    public String farbe() {
        return this.farbe;
    }
}
