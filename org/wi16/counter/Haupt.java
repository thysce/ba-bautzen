/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16.counter;

/**
 *
 * @author Tim Trense
 */
public class Haupt {

    public static void main(final String... args) {
        final Counter c = new Counter(100);
        for (int i = 0; i < 110; i++) {
            c.plusEins();
        }
        System.out.println(c.getZahl() + "   " + c.istKritischHoch());
        for (int i = 0; i < 110; i++) {
            c.minusEins();
        }
        System.out.println(c.getZahl() + "   " + c.istKritischHoch());
    }
}
