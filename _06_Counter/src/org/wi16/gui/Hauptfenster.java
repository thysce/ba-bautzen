/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import org.wi16.counter.Counter;
import org.wi16.counter.ValueChangeListener;

/**
 *
 * @author Tim Trense
 */
@SuppressWarnings("serial")
public class Hauptfenster extends JFrame implements ValueChangeListener{
    
	private final JTextArea anzahl;
	private final JButton plus, minus;
	
    public Hauptfenster(final Counter c){
    	c.setListener(this);
        this.setTitle("Counter");
        this.setSize(new Dimension(500,200));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        final Container content = this.getContentPane();
        content.setLayout(null);	// disable the automatic layout manager
        							// we'll set the bounds for each component
        
        anzahl = new JTextArea();
        anzahl.setBounds(30,20,300,100);
        anzahl.setFont(new Font("Arial", Font.BOLD, 80));
        anzahl.setEditable(false);
        content.add(anzahl);
        
        plus = new JButton("+");
        plus.setBounds(350, 20, 100, 50);
        content.add(plus);
        
        minus = new JButton("-");
        minus.setBounds(350, 70, 100, 50);
        content.add(minus);
        
        updateAnzahlAnzeige(c);
        
        final PlusListener pl = new PlusListener(c);		// Herkömmliches Listener-Prinzip zur Ereignisbehandlung von GUI-Komponenten
        plus.addActionListener(pl);							// Wir nutzen das Listener-Prinzip auch um Updates des Datenmodells an die GUI zu übertragen
        
        minus.addActionListener(event->c.minusEins());	// Lambda-Expressions wurden mit Java 8 eingeführt
        														// Das ist was für Fortgeschrittene
    }

	private void updateAnzahlAnzeige(final Counter counter)
	{
		anzahl.setText(String.valueOf(counter.getZahl()));
		if(counter.istKritischHoch()){
			anzahl.setBackground(Color.RED);
		}else{
			anzahl.setBackground(Color.WHITE);
		}
	}

	@Override
	public void valueChanged(final Counter c)
	{
		updateAnzahlAnzeige(c);
	}
}
