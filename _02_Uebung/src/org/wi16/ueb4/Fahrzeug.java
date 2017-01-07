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
public class Fahrzeug {
    
    String model;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Fahrzeug{" + "model=" + model + '}';
    }
    
}
