package org.ully.samples.enums;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AmpelTest {

    @Test
    public void ampelEinsTest() {
        assertEquals("rot", AmpelEins.ROT.farbe());
        assertEquals("gelb", AmpelEins.GELB.farbe());
        assertEquals("gr�n", AmpelEins.GRUEN.farbe());
    }

    @Test
    public void ampelZweiTest() {
        assertEquals("rot", AmpelZwei.ROT.farbe());
        assertEquals("gelb", AmpelZwei.GELB.farbe());
        assertEquals("gr�n", AmpelZwei.GRUEN.farbe());
    }

    @Test
    public void ampelDreiTest() {
        assertEquals("rot", AmpelDrei.ROT.farbe());
        assertEquals("gelb", AmpelDrei.GELB.farbe());
        assertEquals("gr�n", AmpelDrei.GRUEN.farbe());
    }

    @Test
    public void ampelVierTest() {
        assertEquals("rot", AmpelVier.ROT.farbe());
        assertEquals("gelb", AmpelVier.GELB.farbe());
        assertEquals("gr�n", AmpelVier.GRUEN.farbe());
    }


}
