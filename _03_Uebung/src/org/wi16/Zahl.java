/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16;

/**
 *
 * @author Tim Trense
 */
public class Zahl {
    
    private int z;
    
    public Zahl(final int z){
        setZahl(z);
    }
    
    public int getZahl(){
        return z;
    }
    
    public void setZahl(final int z){
        this.z = z;
    }

    @Override
    public String toString() {
        return "Zahl{" + "z=" + z + '}';
    }
    
}
