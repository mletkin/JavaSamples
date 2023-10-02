package org.mletkin.samples.generics.inheritance;
public class Zuckersack implements Behaelter, Inhalt {

    @Override
    public double volumen() {
        return 100;
    }

    @Override
    public double dichte() {
        return 1.6;
    }

}
