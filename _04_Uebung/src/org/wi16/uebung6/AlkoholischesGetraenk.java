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
public class AlkoholischesGetraenk extends Getraenk{
    
    private float prozent = 0.0f;

    public float getProzent() {
        return prozent;
    }

    public void setProzent(float prozent) {
        this.prozent = prozent;
    }
    
    public String toString(){
        return "alk. Getränk";
    }

    @Override
    public boolean isGesungheitsschaedlich() {
        if(this.getProzent()>0.5){
            return true;
        }
        return false;
    }

    @Override
    public Date getMHD() {
        if(this.getProzent()>35.0){
            return null;
        }else{
            return new Date();
        }
    }
    
    
    
}
