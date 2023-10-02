package org.mletkin.samples.generics.inheritance;
public class Rechner {

    public <T> double foo(T dings) {
        return Integer.valueOf(1);
    }

    void beispielAufruf() {
        foo(Integer.valueOf(1));
        foo("");
    }

    double gewicht(Object dings) {
        if (dings instanceof Behaelter && dings instanceof Inhalt) {
            return ((Behaelter) dings).volumen() * ((Inhalt) dings).dichte();
        }
        throw new IllegalArgumentException();
    }

    public <T extends Behaelter> double wasDrinIst(T dings) {
        return dings.volumen();
    }

    public double wasDrinIstOhnegenerics(Behaelter dings) {
        return dings.volumen();
    }

    public <T extends Inhalt & Behaelter> double gewicht(T dings) {
        return dings.volumen() * dings.dichte();
    }

    public <T extends Object & Behaelter & Inhalt> double gewichtMitObject(T dings) {
        return dings.volumen() * dings.dichte();
    }
}
