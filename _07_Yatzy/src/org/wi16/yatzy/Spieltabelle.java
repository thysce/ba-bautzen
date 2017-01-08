package org.wi16.yatzy;

import java.util.Map;

import javax.swing.JTable;

@SuppressWarnings("serial")
public class Spieltabelle extends JTable
{

	public Spieltabelle()
	{
		super(13, 2);
		fülleTabellenÜberschrift(this, 0);
	}
	
	public static void fülleTabellenÜberschrift(final JTable table, final int offset){
		table.setValueAt(YatzyAuswertung.EINER, offset+0, 0);
		table.setValueAt(YatzyAuswertung.ZWEIER, offset+1, 0);
		table.setValueAt(YatzyAuswertung.DREIER, offset+2, 0);
		table.setValueAt(YatzyAuswertung.VIERER, offset+3, 0);
		table.setValueAt(YatzyAuswertung.FUNFER, offset+4, 0);
		table.setValueAt(YatzyAuswertung.SECHSER, offset+5, 0);
		
		table.setValueAt(YatzyAuswertung.PASCH3, offset+6, 0);
		table.setValueAt(YatzyAuswertung.PASCH4, offset+7, 0);
		table.setValueAt(YatzyAuswertung.STRASSE_KL, offset+8, 0);
		table.setValueAt(YatzyAuswertung.STRASSE_GR, offset+9, 0);
		table.setValueAt(YatzyAuswertung.FULLHOUSE, offset+10, 0);
		table.setValueAt(YatzyAuswertung.YATZEE, offset+11, 0);
		table.setValueAt(YatzyAuswertung.CHANCE, offset+12, 0);
	}

	public static void zeigeSpielerdatenAn(final JTable t,final Spieler s, final int offsetY, final int offsetX){
		final Map<String, Integer> e = s.getErgebnisse();
		t.setValueAt(e.containsKey(YatzyAuswertung.EINER)?e.get(YatzyAuswertung.EINER):"", offsetY+0, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.ZWEIER)?e.get(YatzyAuswertung.ZWEIER):"", offsetY+1, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.DREIER)?e.get(YatzyAuswertung.DREIER):"", offsetY+2, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.VIERER)?e.get(YatzyAuswertung.VIERER):"", offsetY+3, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.FUNFER)?e.get(YatzyAuswertung.FUNFER):"", offsetY+4, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.SECHSER)?e.get(YatzyAuswertung.SECHSER):"", offsetY+5, offsetX+1);
		
		t.setValueAt(e.containsKey(YatzyAuswertung.PASCH3)?e.get(YatzyAuswertung.PASCH3):"", offsetY+6, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.PASCH4)?e.get(YatzyAuswertung.PASCH4):"", offsetY+7, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.STRASSE_KL)?e.get(YatzyAuswertung.STRASSE_KL):"", offsetY+8, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.STRASSE_GR)?e.get(YatzyAuswertung.STRASSE_GR):"", offsetY+9, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.FULLHOUSE)?e.get(YatzyAuswertung.FULLHOUSE):"", offsetY+10, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.YATZEE)?e.get(YatzyAuswertung.YATZEE):"", offsetY+11, offsetX+1);
		t.setValueAt(e.containsKey(YatzyAuswertung.CHANCE)?e.get(YatzyAuswertung.CHANCE):"", offsetY+12, offsetX+1);
	}
	
	public void zeigeSpielerdatenAn(final Spieler s)
	{
		zeigeSpielerdatenAn(this, s, 0, 0);
	}
}
