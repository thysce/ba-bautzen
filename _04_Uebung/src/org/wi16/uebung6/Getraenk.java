/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wi16.uebung6;

import org.wi16.gh.Artikel;

/**
 *
 * @author keschke
 */
public abstract class Getraenk implements Artikel{
    
    private String farbe = "";
    private String name = "";
    private float preis = 0.0f;

    public String getFarbe() {
        return farbe;
    }

    public void setFarbe(String farbe) {
        this.farbe = farbe;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getPreis() {
        return preis;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPreis(float preis) {
        this.preis = preis;
    }
    
    
    
    
    public abstract boolean isGesungheitsschaedlich();
    
    
    public String toString(){
        return "Getränk";
    }
    
    
}
