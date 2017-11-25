package org.ully.samples.initializer;

import org.junit.Test;

public class UnterklasseTest {

    @Test
    public void construkturMitExplizitemSuperkonstruktur() {
        new Unterklasse(mkParameter("parameter"));
    }

    @Test
    public void construkturMitImplizitemSuperconstructor() {
        new Unterklasse();
    }

    @Test
    public void construkturMitexplizitemConstruktorInDerSelbenKlasse() {
        new Unterklasse(mkStringParameter("parameter"));
    }

    @Test
    public void testStatisch() {
        String unterFoo = Unterklasse.sUnterFoo1;
        String oberFoo = Oberklasse.sOberFoo1;
        String unterFoo2 = Unterklasse.sUnterFoo1;
    }

    public int mkParameter(String text) {
        System.out.println(text);
        return 0;
    }

    public String mkStringParameter(String text) {
        System.out.println(text);
        return "";
    }
}
