package org.mletkin.samples.initializer;

public class Oberklasse {

    static {
        System.out.println("Oberklasse  S-Block    eins");
    }

    String oberFoo1 = mkFoo("Oberklasse  Variable   eins");
    static String sOberFoo1 = mkFoo("Oberklasse  s-Variable eins");

    {
        System.out.println("Oberklasse  Block      eins");
    }

    static String sOberFoo2 = mkFoo("Oberklasse  s-Variable zwei");
    String oberFoo2 = mkFoo("Oberklasse  Variable   zwei");

    static {
        System.out.println("Oberklasse  S-Block    zwei");
    }

    public Oberklasse() {
        System.out.println("Oberklasse  C()");
    }

    public Oberklasse(int n) {
        this();
        System.out.println("Oberklasse  C(n)");
    }

    {
        System.out.println("Oberklasse  Block      zwei");
    }

    static private String mkFoo(String text) {
        System.out.println(text);
        return null;
    }

}
