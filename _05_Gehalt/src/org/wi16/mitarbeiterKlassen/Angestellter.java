/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16.mitarbeiterKlassen;

import org.wi16.Mitarbeiter;
import org.wi16.Name;

/**
 *
 * @author Tim Trense
 */
public class Angestellter extends Mitarbeiter {

    private double grundgehalt, zulage;
    public Angestellter(final Name name) {
        super(name);
        this.grundgehalt = 3000;
        this.zulage = 0;
    }
    
    @Override
    protected double getGehalt() {
        return grundgehalt + zulage;
    }

    public double getGrundgehalt() {
        return grundgehalt;
    }

    public void setGrundgehalt(final double grundgehalt) {
        this.grundgehalt = grundgehalt;
    }

    public double getZulage() {
        return zulage;
    }

    public void setZulage(final double zuschlaege) {
        this.zulage = zuschlaege;
    }

    @Override
    public double monatsEnde() {
        return getGehalt();
    }

    @Override
    public String toString() {
        return getPersonalNummer() + " Angestellter{" + "grundgehalt=" + grundgehalt + ", zulage=" + zulage + '}';
    }

}
