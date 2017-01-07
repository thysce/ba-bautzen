/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wi16.gh;

import org.wi16.uebung6.AlkoholfreiesGetraenk;
import org.wi16.uebung6.AlkoholischesGetraenk;

/**
 *
 * @author keschke
 */
public class Haupt {
    
    public static void main (String[] args){
        ArtikelListe al = new ArtikelListe();

        AlkoholfreiesGetraenk l = new AlkoholfreiesGetraenk();
        l.setName("Fanta");
        l.setPreis(0.60f);
        
        AlkoholischesGetraenk b = new AlkoholischesGetraenk();
        b.setName("Bier");
        b.setPreis(0.50f);
        
        al.addArtikel(b);
        al.addArtikel(l);
        
        System.out.println("Summe: "+al.getSumme());
        
        
    }
    
}
