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
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        final Auto auto = new Auto();
        auto.setModel("Golf",2);
        auto.setModel(null);
        System.out.println(auto);
        
        final Fahrzeug fahrz = new Fahrzeug();
        fahrz.setModel("Golf");
        fahrz.setModel(null);
        System.out.println(fahrz);
    }
    
}
