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
public class Manager extends Mitarbeiter {

    private double grundgehalt;
    private double provision;
    private double umsatz;
    public Manager(final Name name) {
        super(name);
        this.umsatz = 0;
        this.grundgehalt = 1500;
        this.provision = 0.05;
    }

    @Override
    protected double getGehalt() {
        return grundgehalt + provision * umsatz;
    }

    public void setGrundgehalt(final double grundgehalt) {
        this.grundgehalt = grundgehalt;
    }

    public double getGrundgehalt() {
        return grundgehalt;
    }

    public void setProvisionPercent(final double provPC){
        this.provision = provPC /100d;
    }
    
    public double getProvisionPercent(){
        return this.provision * 100d;
    }
    
    public void macheUmsatz(final double neuerUmsatz){
        this.umsatz += neuerUmsatz;
    }

    public double getUmsatz() {
        return umsatz;
    }

    @Override
    public double monatsEnde() {
        final double gehalt = getGehalt();
        this.umsatz = 0;
        return gehalt;
    }

    @Override
    public String toString() {
        return getPersonalNummer() + " Manager{" + "grundgehalt=" + grundgehalt + ", provision=" + provision + '}';
    }
    
}
