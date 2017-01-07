/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wi16.gh;

import java.util.ArrayList;

/**
 *
 * @author keschke
 */
public class ArtikelListe {
    
    private ArrayList<Artikel> artikelliste = new ArrayList<>();
    
    public void addArtikel(Artikel a){
        this.artikelliste.add(a);
    }
    
    public float getSumme(){
        float summe = 0.0f;
        for(Artikel a : this.artikelliste){
            summe = summe + a.getPreis();
        }
        return summe;
    }
    
}
