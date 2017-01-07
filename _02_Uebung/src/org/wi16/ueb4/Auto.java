/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wi16.ueb4;

/**
 *
 * @author Tim Trense
 */
public class Auto extends Fahrzeug {

    private int version;

    Auto() {
        setModel("Fabia", 1);
    }

    @Override
    public void setModel(final String model) {
        if (model != null) {
            super.setModel(model); //To change body of generated methods, choose Tools | Templates.
        }
    }

    public void setModel(final String model, final int version) {
        setModel(model);
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(final int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Auto{" + "model=" + model + ";version=" + version + '}';
    }

}
