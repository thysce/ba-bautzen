/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16;

/**
 *
 * @author Tim Trense
 */
public abstract class Mitarbeiter {

    private static long naechstePersNR = 0;
    
    private final long persNR;
    private final Name name;
    
    public Mitarbeiter(final Name name){
        this.persNR = naechstePersNR++;
        this.name = name;
    }
    
    public Mitarbeiter(final String vname, final String nname){
        this(new Name(vname, nname));
    }

    public long getPersonalNummer() {
        return persNR;
    }

    public Name getName() {
        return name;
    }
    
    /**
     * Berechnet das Gehalt zum (aktuellen Zeitpunkt) für diesen Monat
     * @return Monatsgehalt
     */
    protected abstract double getGehalt();
    
    /**
     * @return das schlussendliche Gehalt des Monats
     */
    public abstract double monatsEnde();
    
    /**
     * Ich zwinge Subklassen zur Implementation dieser Methode
     * @return Eine String-Darstellung der Mitarbeiterdaten
     */
    public abstract String toString();
}
