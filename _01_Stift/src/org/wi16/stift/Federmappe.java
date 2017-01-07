package org.wi16.stift;

import java.awt.Color;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Tim Trense
 */
public class Federmappe {

    // Wir beabsichtigen nicht, stifte neu zu setzen nach dem Konstruktor. Also final
    // Ein Set ist wie eine Liste, die automatisch prüft, dass ein Stift nicht mehrfach eingefügt wird
    private final Set<Stift> stifte;
    // Wir wollen die Federmappe nach der Herstellung nicht nochmal umschneidern,
    // also ändert sich die maximale Füllmenge nicht
    private final int maxCount;

    /**
     * Bauen einer fabrikneuen Federmappe
     * @param maxCount maximale Anzahl der Stifte, die diese Federmappe aufnehmen kann
     */
    public Federmappe(final int maxCount) {
        this.maxCount = maxCount;
        stifte = new HashSet<>();
    }

    /**
     * Wenn der Stift reinpasst und noch nicht drin ist, dann einfügen.
     * @param s der einzfügende Stift
     * @return true, wenn der Stift erfolgreich eingefügt wurde. sonst false
     */
    public boolean stiftEinlegen(final Stift s) {
        if (stifte.size() >= maxCount) {
            System.out.println("Die Mappe ist voll!");
            return false;
        }
        
        final boolean wurdeEingelegt = stifte.add(s);
        if (wurdeEingelegt) {
            System.out.println("Stift " + s + " eingelegt");
            return true;
        } else {
            System.out.println("Stift " + s + " schon vorhanden");
            return false;
        }
    }

    /**
     * Rückgabe eines Stiftes dieser Farbe, falls soeiner drin ist
     * @param color die Farbe des gewünschten Stiftes
     * @return null falls kein solcher gefunden wurde, ansonsten der gefundene Stift
     */
    public Stift stiftEntnehmen(final Color color) {
        Stift n = null;
        for (Stift s : stifte) {
            if (s.getColor().equals(color)) {
                n = s;
                break;
            }
        }
        if (n != null) {
            stifte.remove(n);
        }
        return n;
    }

    /**
     * @return null falls die Mappe leer ist, ansonsten irgendein Stift
     */
    public Stift erstenStiftEntnehmen() {
        if (stifte.size() > 0) {
            final Stift s = stifte.iterator().next();
            stifte.remove(s);
            return s;
        } else {
            return null;
        }
    }
    
    public boolean istVoll(){
        return stifte.size() >= maxCount;
    }

    /**
     * @param stifte zum Einfügen mehrerer Stifte auf einmal
     */
    public void mehrereStifteEinlegen(final Stift... stifte) {
        for (Stift s : stifte) {
            stiftEinlegen(s);
        }
    }

    public double getFuellmengeProzent() {
        return (stifte.size() / (double) maxCount) * 100d;
    }

    public int getMaxFuellmenge() {
        return maxCount;
    }

    public int countStifte() {
        return stifte.size();
    }

    @Override
    public String toString() {
        return "Federmappe{" + "stifte=" + stifte + ", maxCount=" + maxCount + '}';
    }

}
