package org.ully.samples.generics.inheritance;

import java.util.ArrayList;
import java.util.List;

public class Ladung <T extends Behaelter & Inhalt> {

    List<T> fracht = new ArrayList<>();

    double gewicht() {
        return fracht.stream() //
                .mapToDouble(f -> f.dichte() * f.volumen()) //
                .sum();
    }
}
