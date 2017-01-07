package org.wi16.stift;

import java.awt.Color;

/**
 * THIS CODE IS CREATED USING NETBEANS
 * @author Tim Trense
 */
public class Haupt {

    // Auto-Format via Alt + Shift + F
    // Run via F6
    public static final void main(final String... args) {
        // "sout" + Ctrl + Space
        System.out.println("Hello World");

        final Stift[] stifte = new Stift[13];
        for (int i = 0; i < stifte.length; i++) {
            final int red = (int) (Math.random() * 256);           // rgb-Werte für die Stiftfarbe zufällig erzeugen
            final int green = (int) (Math.random() * 256);
            final int blue = (int) (Math.random() * 256);
            stifte[i] = new Stift(new Color(red, green, blue));
        }
        final Federmappe maeppchen = new Federmappe(10);
        maeppchen.mehrereStifteEinlegen(stifte);

        System.out.println(maeppchen);
        System.out.println("Füllmenge = " + maeppchen.getFuellmengeProzent() + "%");
    }

}
