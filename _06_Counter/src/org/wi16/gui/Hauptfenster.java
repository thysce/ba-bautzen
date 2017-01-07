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
import javax.swing.JTextField;

import org.wi16.counter.Counter;
import org.wi16.counter.ValueChangeListener;

/**
 *
 * @author Tim Trense
 */
@SuppressWarnings("serial")
public class Hauptfenster extends JFrame implements ValueChangeListener{
    
	private final JTextField anzahl;
	private final JButton plus, minus;
	
    public Hauptfenster(final Counter c){
    	c.setListener(this);
        this.setTitle("Counter by BABautzen_WI16");
        this.setSize(new Dimension(500,200));
        this.setResizable(false);				// Weil wir die Komponenten selbst anordnen, können wir nicht (so einfach) auf Größenänderungen des Fensters reagieren.
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);	// Ein Fenster schließt das Programm nicht automatisch, wenn der X-Knopf gedrückt wird, das stellen wir hier ein
        												// Tun wir das hier nicht, so läuft das Programm weiter, ohne dass der User damit interagieren kann. WORST CASE EVER!!!
        
        final Container content = this.getContentPane();
        content.setLayout(null);	// Ausschalten der Automatik zum Anordnen der GUI-Komponenten
        							// Wir sagen mit setBounds selbst, wo die Komponenten hingehören
        
        anzahl = new JTextField();
        			//	x	y	b	h		// x/y-Position mit Breite und Höhe
        anzahl.setBounds(30,20,300,100);		// Positionieren der Komponente in Abhängigkeit der OBEREN LINKEN Ecke der übergeordneten Komponente
        										// y-Koordinaten gelten NACH UNTEN
        										// x-Koordinaten gelten NACH RECHTS
        anzahl.setFont(new Font("Arial", Font.BOLD, 80));	// Schriftart, Stil und Pixel-größe angeben
        anzahl.setEditable(false);							// der User soll nichts eingeben können
        content.add(anzahl);								// Hinzufügen der Kindkomponente anzahl zur übergeordneten ElternKomponente content
        
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
        												// Functional Interfaces sind was für Fortgeschrittene
    }

    // Das ist unsere eigene Hilfsmethode um die Anzeige neu einzustellen
	private void updateAnzahlAnzeige(final Counter counter)
	{
		// Zurücksetzen der Anzeige
		anzahl.setText(String.valueOf(counter.getZahl()));
		plus.setEnabled(true);
		minus.setEnabled(true);
		
		if(counter.istKritischHoch()){
			anzahl.setBackground(Color.YELLOW);
			if(counter.istMaximal()){ // wenn nötig Zugriff einschränken
				anzahl.setBackground(Color.RED);
				plus.setEnabled(false);
				anzahl.requestFocus(); // verhindern, dass der Fokus automatisch auf den anderen Knopf springt
			}
		}else{
			anzahl.setBackground(Color.WHITE);
			if(counter.istMinimal()){
				minus.setEnabled(false);
				anzahl.requestFocus();
			}
		}
	}
	
	// Wir implementieren unseren eigenen Listener, um auf Veränderungen unseres Datenmodells zu reagieren.
	// Man kann das in so einem einfachen Beispiel auch einfacher machen, aber so hier ist es der beste Weg, vor allem, wenn man mit anderen Leuten an großen Projekten arbeitet.
	@Override
	public void valueChanged(final Counter c)
	{
		updateAnzahlAnzeige(c);
	}
}
