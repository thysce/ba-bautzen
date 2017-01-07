/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16;

import java.util.HashSet;
import java.util.Set;
import org.wi16.mitarbeiterKlassen.Angestellter;
import org.wi16.mitarbeiterKlassen.Arbeiter;
import org.wi16.mitarbeiterKlassen.Manager;

/**
 *
 * @author Tim Trense
 */
public class Haupt {

    public static void main(final String... args) {
        final Set<Mitarbeiter> mitarbeiter = new HashSet<Mitarbeiter>();

        final double umsatz = 400_000;
        erzeugeMitarbeiter(mitarbeiter, umsatz);

        final Abrechnung abr = new Abrechnung(mitarbeiter, 90);
        abr.gebeMonatsberichtAus();
        abr.beendeMonat();
        abr.gebeMonatsberichtAus();
        abr.beendeMonat();
        abr.gebeMonatsberichtAus();

        System.out.println("\n");

        System.out.println("Mitarbeiterliste:");
        for (Mitarbeiter m : mitarbeiter) {
            System.out.println(m);
        }
    }

    public static void erzeugeMitarbeiter(final Set<Mitarbeiter> mitarbeiter, final double umsatz) {
        {
            final Arbeiter a1 = new Arbeiter(new Name("Max", "Mustermann"));
            a1.setStundenlohn(8);
            a1.setSollstunden(176);
            a1.setArbeitsZeitStunden(176 - 0);
            a1.setUeberStundenlohn(2);
            mitarbeiter.add(a1);
        }
        {
            final Arbeiter a2 = new Arbeiter(new Name("Max", "Müller"));
            a2.setStundenlohn(8);
            a2.setSollstunden(176);
            a2.setArbeitsZeitStunden(176 + 5);
            a2.setUeberStundenlohn(2);
            mitarbeiter.add(a2);
        }
        {
            final Arbeiter a3 = new Arbeiter(new Name("Micha", "Mustermann"));
            a3.setStundenlohn(8);
            a3.setSollstunden(176);
            a3.setArbeitsZeitStunden(176 - 0);
            a3.setUeberStundenlohn(2);
            mitarbeiter.add(a3);
        }
        {
            final Arbeiter a4 = new Arbeiter(new Name("Klaus", "Peter"));
            a4.setStundenlohn(8);
            a4.setSollstunden(176);
            a4.setArbeitsZeitStunden(176 + 8);
            a4.setUeberStundenlohn(2);
            mitarbeiter.add(a4);
        }
        {
            final Angestellter an1 = new Angestellter(new Name("Franz", "König"));
            an1.setGrundgehalt(3500);
            an1.setZulage(200);
            mitarbeiter.add(an1);
        }
        {
            final Angestellter an2 = new Angestellter(new Name("Lissi", "König"));
            an2.setGrundgehalt(3300);
            an2.setZulage(150);
            mitarbeiter.add(an2);
        }
        {
            final Manager m1 = new Manager(new Name("Richie", "Rich"));
            m1.setGrundgehalt(1500);
            m1.macheUmsatz(umsatz);
            m1.setProvisionPercent(5);
            mitarbeiter.add(m1);
        }
    }
}
