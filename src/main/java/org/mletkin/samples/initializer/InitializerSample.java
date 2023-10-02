package org.mletkin.samples.initializer;

public class InitializerSample {

    class Maschine {
        double verarbeite(Rechner rechner, double value) {
            return rechner.calc(value);
        }
    }

    public double berechneMitDefaultSteuer(double value) {
        return new Maschine().verarbeite(new Rechner() {
            @Override
            public double postCalc(double wert) {
                return wert + 10;
            }
        }, value);
    }

    public double berechneMitCustomSteuer(double value) {
        return new Maschine().verarbeite(new Rechner() {
            {
                steuer = 0.1;
            }

            @Override
            public double postCalc(double wert) {
                return wert + 10;
            }
        }, value);
    }

    public double berechneMitParametrierterSteuer(double customSteuer, double value) {
        return new Maschine().verarbeite(new Rechner() {
            {
                this.steuer = customSteuer;
            }

            @Override
            public double postCalc(double wert) {
                return wert + 10;
            }
        }, value);
    }

}
