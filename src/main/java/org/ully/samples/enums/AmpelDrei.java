package org.ully.samples.enums;

public enum AmpelDrei {

    ROT     {@Override public String farbe() { return "rot"; }} ,
    GELB    {@Override public String farbe() { return "gelb"; }} ,
    GRUEN   {@Override public String farbe() { return "gr�n"; }} ;

    public abstract String farbe();
}
