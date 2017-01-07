package org.wi16.stift;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tim Trense
 */
public class Federmappe {

    /**
     * Eine Federmappe hat in ihrem gesamten Leben nur EINEN Inhalt, also final
     * Ein Set ist wie eine Liste, die automatisch prüft, dass eine Schreibware
     * nicht mehrfach eingefügt wird
     */
    private final Set<Schreibware> geräte;
    /**
     * Wir wollen die Federmappe nach der Herstellung nicht nochmal
     * umschneidern, also ändert sich die maximale Füllmenge nicht
     */
    private final int maxCount;

    /**
     * Bauen einer fabrikneuen Federmappe
     *
     * @param maxCount maximale Anzahl der Schreibware, die diese Federmappe
     * aufnehmen kann
     */
    public Federmappe(final int maxCount) {
        this.maxCount = maxCount;
        geräte = new HashSet<>();
    }

    /**
     * Wenn die Schreibware reinpasst und noch nicht drin ist, dann einfügen.
     *
     * @param s die einzufügende Schreibware
     * @return true, wenn erfolgreich eingefügt wurde. sonst false
     */
    public boolean einfügen(final Schreibware s) {
        if (istVoll()) {
            System.out.println("Die Mappe ist voll!");
            return false;
        }

        final boolean wurdeEingelegt = geräte.add(s);
        if (wurdeEingelegt) {
            System.out.println(s + " eingelegt");
            return true;
        } else {
            System.out.println(s + " schon vorhanden");
            return false;
        }
    }

    /**
     * Rückgabe eines Stiftes dieser Farbe, falls soeiner drin ist
     *
     * @param color die Farbe des gewünschten Stiftes
     * @return null falls kein solcher gefunden wurde, ansonsten der gefundene
     * Stift
     */
    public Stift stiftEntnehmen(final Color color) {
        Stift n = null;
        for (Schreibware s : geräte) {
            if (s instanceof Stift) {
                if (((Stift) s).getColor().equals(color)) {
                    n = (Stift) s;
                    break;
                }
            }
        }
        if (n != null) {
            geräte.remove(n);
        }
        return n;
    }

    /**
     * @return null falls die Mappe leer ist, ansonsten irgendein Stift
     */
    public Stift erstenStiftEntnehmen() {
        if (istLeer()) {
            return null;
        }
        for (Schreibware z : geräte) {
            if (z instanceof Stift) {
                return (Stift) z;
            }
        }
        return null;

    }

    public boolean istVoll() {
        return count() >= maxCount;
    }

    public boolean istLeer() {
        return count() <= 0;
    }

    /**
     * @param stifte zum Einfügen mehrerer Stifte auf einmal
     */
    public void mehrereStifteEinlegen(final Stift... stifte) {
        for (Stift s : stifte) {
            einfügen(s);
        }
    }

    public double getFuellmengeProzent() {
        return (count() / (double) maxCount) * 100d;
    }

    public int getMaxFuellmenge() {
        return maxCount;
    }

    public int count() {
        return geräte.size();
    }

    @Override
    public String toString() {
        return "Federmappe{" + "inhalt=" + geräte + ", maxCount=" + maxCount + '}';
    }

}
