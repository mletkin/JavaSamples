package org.mletkin.samples.initializer;

public abstract class Rechner {

    protected double steuer = 0.05;

    abstract public double postCalc(double wert);

    public double calc(double wert) {
        return postCalc((1 + steuer) * wert);
    }

}
