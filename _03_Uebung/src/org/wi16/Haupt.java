/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16;

/**
 *
 * @author Tim Trense
 */
public class Haupt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Haupt h = new Haupt();
        int a = 2;
        int b = 4;
        int erg = h.add(a, b);
        System.out.println(a + "+" + b + "=" + erg);

        Zahl za = new Zahl(2);
        Zahl zb = new Zahl(4);
        int ergz = h.add(za, zb);
        System.out.println(za + "+" + zb + "=" + ergz);
    }

    public int add(int a, final int b) {
        a = 4;
        return a + b;
    }

    public int add(final Zahl a, final Zahl b) {
        a.setZahl(4);
        return a.getZahl() + b.getZahl();
    }
}
