package org.mletkin.samples.initializer;

public class Unterklasse extends Oberklasse {

    String unterFoo1 = mkFoo("Unterklasse Variable   eins");
    static String sUnterFoo1 = mkFoo("Unterklasse S-Variable eins");

    {
        System.out.println("Unterklasse Block      eins");
    }

    static {
        System.out.println("Unterklasse S-Block    eins");
    }

    String unterFoo2 = mkFoo("Unterklasse Variable   zwei");
    static String sUnterFoo2 = mkFoo("Unterklasse S-Variable zwei");

    public Unterklasse() {
        System.out.println("Unterklasse C()");
    }

    public Unterklasse(int n) {
        super(n);
        System.out.println("Unterklasse C(n)");
    }

    public Unterklasse(String s) {
        this();
        System.out.println("C(String) Unterklasse");
    }

    {
        System.out.println("Unterklasse Block      zwei");
    }

    static {
        System.out.println("Unterklasse S-Block    zwei");
    }

    static private String mkFoo(String text) {
        System.out.println(text);
        return null;
    }

}
