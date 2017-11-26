package org.ully.samples.enums;

public enum AmpelZwei {

    ROT   {{farbe = "rot";}} ,
    GELB  {{farbe = "gelb";}} ,
    GRUEN {{farbe = "grün";}};

    String farbe;

    public String farbe() {
        return this.farbe;
    }
}
