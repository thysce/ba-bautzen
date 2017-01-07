package org.wi16.yatzy;

import java.util.Map;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class Spieltabelle extends JTable
{

	public Spieltabelle()
	{
		super(13, 2);
		this.setValueAt(YatzyAuswertung.EINER, 0, 0);
		this.setValueAt(YatzyAuswertung.ZWEIER, 1, 0);
		this.setValueAt(YatzyAuswertung.DREIER, 2, 0);
		this.setValueAt(YatzyAuswertung.VIERER, 3, 0);
		this.setValueAt(YatzyAuswertung.FUNFER, 4, 0);
		this.setValueAt(YatzyAuswertung.SECHSER, 5, 0);
		
		this.setValueAt(YatzyAuswertung.PASCH3, 6, 0);
		this.setValueAt(YatzyAuswertung.PASCH4, 7, 0);
		this.setValueAt(YatzyAuswertung.STRASSE_KL, 8, 0);
		this.setValueAt(YatzyAuswertung.STRASSE_GR, 9, 0);
		this.setValueAt(YatzyAuswertung.FULLHOUSE, 10, 0);
		this.setValueAt(YatzyAuswertung.YATZEE, 11, 0);
		this.setValueAt(YatzyAuswertung.CHANCE, 12, 0);
	}

	public void zeigeSpielerdatenAn(final Spieler s)
	{
		final Map<String, Integer> e = s.getErgebnisse();
		this.setValueAt(e.containsKey(YatzyAuswertung.EINER)?e.get(YatzyAuswertung.EINER):"", 0, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.ZWEIER)?e.get(YatzyAuswertung.ZWEIER):"", 1, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.DREIER)?e.get(YatzyAuswertung.DREIER):"", 2, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.VIERER)?e.get(YatzyAuswertung.VIERER):"", 3, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.FUNFER)?e.get(YatzyAuswertung.FUNFER):"", 4, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.SECHSER)?e.get(YatzyAuswertung.SECHSER):"", 5, 1);
		
		this.setValueAt(e.containsKey(YatzyAuswertung.PASCH3)?e.get(YatzyAuswertung.PASCH3):"", 6, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.PASCH4)?e.get(YatzyAuswertung.PASCH4):"", 7, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.STRASSE_KL)?e.get(YatzyAuswertung.STRASSE_KL):"", 8, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.STRASSE_GR)?e.get(YatzyAuswertung.STRASSE_GR):"", 9, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.FULLHOUSE)?e.get(YatzyAuswertung.FULLHOUSE):"", 10, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.YATZEE)?e.get(YatzyAuswertung.YATZEE):"", 11, 1);
		this.setValueAt(e.containsKey(YatzyAuswertung.CHANCE)?e.get(YatzyAuswertung.CHANCE):"", 12, 1);
	}
}
