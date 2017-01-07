/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.wi16.uebung6;

import java.util.Date;

/**
 *
 * @author keschke
 */
public class AlkoholfreiesGetraenk extends Getraenk{
    
    
    
    public String toString(){
        return "alk. freies Getränk";
    }

    @Override
    public boolean isGesungheitsschaedlich() {
        return false;
    }

    @Override
    public Date getMHD() {
        
        return new Date();
    }
    
}
