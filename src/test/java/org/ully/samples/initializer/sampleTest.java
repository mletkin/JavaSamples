package org.ully.samples.initializer;

import org.junit.Test;

public class sampleTest {

    @Test
    public void testDefault() {
        System.out.println("default: " + new InitializerSample().berechneMitDefaultSteuer(10));
    }

    @Test
    public void testCustom() {
        System.out.println("Steuer 10%: " + new InitializerSample().berechneMitCustomSteuer(10));
    }

    @Test
    public void testParameter() {
        System.out.println("Steuer 100% " + new InitializerSample().berechneMitParametrierterSteuer(1, 10));
    }

}
