/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16.gui;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Tim Trense
 */
@SuppressWarnings("serial")
public class Hauptfenster extends JFrame{
    
    public Hauptfenster(){
        this.setTitle("Counter");
        this.setSize(new Dimension(700,300));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
