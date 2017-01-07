package org.wi16.yatzy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.JToggleButton;

public class Spiel
{

	private final Becher becher;
	private Spieler aktiverSpieler;
	private final Queue<Spieler> spieler;

	private int wurfNr, rundeNr, spielerNr;
	private NächsterSpielerListener listener;

	public Spiel(final Spieler... spieler)
	{
		becher = new Becher();
		listener = null;
		this.spieler = new LinkedBlockingQueue<>();
		for (Spieler s : spieler)
		{
			this.spieler.add(s);
		}
		this.aktiverSpieler = this.spieler.poll();
		this.rundeNr = 0;
		this.spielerNr = 0;
	}

	public boolean nächsterSpieler(final String erg, final boolean streichung)
	{
		auswerten(erg, streichung);

		for (Würfel w : becher.getWürfel())
		{
			w.setAktiv(true);
		}
		becher.wurf();
		if (this.aktiverSpieler != null)
		{
			this.spieler.offer(this.aktiverSpieler);
		}
		this.aktiverSpieler = this.spieler.poll();
		wurfNr = 0;
		if (listener != null)
		{
			listener.rundeVorbei(this.aktiverSpieler);
		}
		spielerNr++;
		if (spielerNr >= this.spieler.size() - 1)
		{
			spielerNr = 0;
			rundeNr++;
		}
		return rundeNr >= 13;	// Spiel vorbei
	}

	private void auswerten(final String erg, final boolean streichung)
	{
		if (erg == null)
		{
			return;
		}
		if (!streichung)
			this.aktiverSpieler.increaseScore(erg, YatzyAuswertung.berechnePunkte(becher, erg));
		else
			this.aktiverSpieler.increaseScore(erg, 0);
	}

	public boolean wurf()
	{
		if (wurfNr < 2)
		{
			becher.wurf();
			wurfNr++;
			return false;
		} else
		{
			return true;
		}
	}

	public Collection<JToggleButton> getWürfelButtons()
	{
		final List<JToggleButton> btns = new ArrayList<JToggleButton>(Becher.MAX_COUNT);
		for (Würfel w : becher.getWürfel())
		{
			btns.add(w.getAnzeige());
		}
		return btns;
	}

	public void setNächsterSpielerListener(final NächsterSpielerListener l)
	{
		this.listener = l;
	}

	public String[] berechneMöglicheErgebnisse()
	{
		final Collection<String> strg = YatzyAuswertung.berechneMöglicheErgebnisse(aktiverSpieler, becher);
		final String[] ergs = new String[strg.size()];
		strg.toArray(ergs);
		return ergs;
	}

	public String[] berechneStreichung()
	{
		final Collection<String> strg = YatzyAuswertung.berechneMöglicheErgebnisse(aktiverSpieler, null);
		final String[] ergs = new String[strg.size()];
		strg.toArray(ergs);
		return ergs;
	}

	public Spieler berechneGewinner()
	{
		int max = this.aktiverSpieler.getScore();
		Spieler gewinner = this.aktiverSpieler;
		int score;
		for(Spieler s:this.spieler){
			if((score = s.getScore()) > max){
				max = score;
				gewinner = s;
			}
		}
		return gewinner;
	}

}