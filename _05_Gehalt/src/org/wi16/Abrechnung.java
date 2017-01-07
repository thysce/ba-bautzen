/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16;

import java.util.Set;
import org.wi16.mitarbeiterKlassen.Manager;

/**
 * Eine Abrechnung bezieht sich auf die Verwaltung der gegebenen Mitarbeiter in
 * einem Monat.
 *
 * @author Tim Trense
 */
public class Abrechnung {

    private final Set<Mitarbeiter> mitarbeiter;
    private double umsatz;
    private double gewinn;
    private double kosten;
    private double mitarbeiterKosten;
    private double kostenAnteil;
    private int monat = 0;

    /**
     * Die Abrechnung berücksichtigt alle Umsätze der Manager, die
     * Produktionskosten und die Mitarbeiterkosten.
     *
     * @param mitarbeiter die Menge aller angestellten Mitarbeiter
     * @param prodKostAntlPC der Produktionskostenanteil in Prozent
     */
    public Abrechnung(final Set<Mitarbeiter> mitarbeiter, final double prodKostAntlPC) {
        this.mitarbeiter = mitarbeiter;
        this.setProduktionsKostenAnteilPercent(prodKostAntlPC);
    }

    public double getProduktionsKostenAnteilPercent() {
        return kostenAnteil * 100d;
    }

    public void setProduktionsKostenAnteilPercent(final double kostenAnteil) {
        this.kostenAnteil = kostenAnteil / 100d;
    }

    public Set<Mitarbeiter> getMitarbeiter() {
        return mitarbeiter;
    }

    public double getUmsatz() {
        return umsatz;
    }

    /**
     * Berechnet die Monatsbilanz.
     */
    public void beendeMonat() {
        this.umsatz = 0;
        for (Mitarbeiter m : mitarbeiter) {
            if (m instanceof Manager) {
                this.umsatz += ((Manager) m).getUmsatz();
            }
        }

        this.kosten = this.umsatz * this.kostenAnteil;
        this.mitarbeiterKosten = 0;
        for (Mitarbeiter m : mitarbeiter) {
            this.mitarbeiterKosten += m.monatsEnde();
        }
        this.gewinn = (umsatz - kosten) - mitarbeiterKosten;
        monat++;
    }

    /**
     * Gibt den aktuellen Berechnungsmonat zurück. Beginnend mit 1 NICHT Monat
     * des Jahres
     *
     * @return der Monat, für den der nächste Monatsabschluss gemacht wird
     * @see getMonatDesJahres()
     */
    public int getMonat() {
        return monat + 1;
    }

    /**
     * Gibt des aktuellen Monat des Jahres an.
     *
     * @return 1..12
     */
    public int getMonatDesJahres() {
        final int m = getMonat() - (getJahr() - 1) * 12;
        if (m == 0) {
            return 12;
        } else {
            return m;
        }
    }

    /**
     * Gibt das aktuelle Rechnungsjahr zurück. Beginnend mit 1
     *
     * @return das aktuelle Rechnungsjahr
     */
    public int getJahr() {
        return getMonat() / 12 + 1;
    }

    /**
     * Der Monatsbericht bezieht sich auf den zuletzt abgeschlossenen Monat.
     *
     * @see beendeMonat()
     */
    public void gebeMonatsberichtAus() {
        System.out.println("MONATS-ÜBERSICHT (Monat/Jahr: " + getMonatDesJahres() + " / " + getJahr() + ")");
        System.out.println("Umsatz = \t\t" + umsatz);
        System.out.println("ProduktionsKosten = \t" + kosten);
        System.out.println("MitarbeiterKosten = \t" + mitarbeiterKosten);
        System.out.println("Gewinn/-Verlust = \t" + gewinn);
        System.out.println();
    }
}
