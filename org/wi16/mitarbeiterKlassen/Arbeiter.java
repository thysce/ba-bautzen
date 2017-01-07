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
public class Arbeiter extends Mitarbeiter {

    private double arbeitszeitH;
    private double sollH;
    private double Hlohn, ueberHlohn;

    private long schichtbeginn;

    public Arbeiter(final Name name) {
        super(name);
        this.arbeitszeitH = 0;
        this.sollH = 8 * 30;
        this.Hlohn = 8.81;
        this.ueberHlohn = 9.81;
        this.schichtbeginn = 0;
    }

    public void beginneSchicht() {
        if (this.schichtbeginn == 0) {
            this.schichtbeginn = System.currentTimeMillis();
        }
    }

    public void beendeSchicht() {
        if (this.schichtbeginn > 0) {
            this.arbeitszeitH += (System.currentTimeMillis() - this.schichtbeginn) / (1000 * 60 * 60); 
                // jaja, arbeitszeit auf die Millisekunde abzurechnen ist unsozial, aber hey- programmer is boss
            this.schichtbeginn = 0;
        }
    }
    
    @Override
    protected double getGehalt(){
        final double ueberStunden = this.arbeitszeitH - this.sollH;
        if(ueberStunden < 0){
            return this.Hlohn * this.arbeitszeitH;
        }else{
            return this.Hlohn * this.sollH + this.ueberHlohn * ueberStunden;
        }
    }
    
    public double getSollstunden() {
        return sollH;
    }

    public void setSollstunden(final double sollH) {
        this.sollH = sollH;
    }

    public double getStundenlohn() {
        return Hlohn;
    }

    public void setStundenlohn(final double Hlohn) {
        this.Hlohn = Hlohn;
    }

    public double getUeberStundenlohn() {
        return ueberHlohn;
    }

    public void setUeberStundenlohn(final double ueberHlohn) {
        this.ueberHlohn = ueberHlohn;
    }
    
    /**
     * diese Methode dient lediglich der Lösung der Aufgabe, in einem Echtzeit-Mitarbeitermanagementsystem wäre diese Methode ein Sicherheitsrisiko
     * @param arbeitszeit die Arbeitszeit des Arbeites
     */
    public void setArbeitsZeitStunden(final double arbeitszeit){
        this.arbeitszeitH = arbeitszeit;
    }

    @Override
    public double monatsEnde() {
        final double gehalt = getGehalt();
        this.arbeitszeitH = 0;
        return gehalt;
    }

    @Override
    public String toString() {
        return getPersonalNummer() + " Arbeiter{" + "sollH=" + sollH + ", Hlohn=" + Hlohn + ", ueberHlohn=" + ueberHlohn + '}';
    }
}
