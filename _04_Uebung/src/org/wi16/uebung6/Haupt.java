/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wi16.uebung6;

/**
 *
 * @author keschke
 */
public class Haupt {
    
    public static void main(String args[]){
        
        AlkoholfreiesGetraenk k = new AlkoholfreiesGetraenk();
        AlkoholischesGetraenk b = new AlkoholischesGetraenk();
        
        k.setFarbe("schwarz");
        b.setFarbe("goldgelb");
        b.setProzent(4.7f);
        
        System.out.println("Kaffe ist gs: "+k.isGesungheitsschaedlich());
        System.out.println("Bier ist gs: "+b.isGesungheitsschaedlich());
        
    }
    
}
