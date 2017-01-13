package org.wi16.yatzy;

import java.awt.Font;
import java.util.Random;

import javax.swing.JToggleButton;

public class Würfel
{

	private static final Random zufall;

	static
	{
		zufall = new Random();
	}

	private int augenzahl;
	private final JToggleButton btn;

	public Würfel()
	{
		btn = new JToggleButton(getAugenzahl() + "");
		btn.setSelected(false);
		btn.setFont(new Font("Arial", Font.BOLD, 90));
		wurf(); // zuf�llige Zahl anzeigen
	}

	public JToggleButton getAnzeige()
	{
		return this.btn;
	}

	public void wurf()
	{
		if (!btn.isSelected())
		{
			this.augenzahl = zufall.nextInt(6) + 1;
			btn.setText(String.valueOf(this.augenzahl));
		}
	}

	public int getAugenzahl()
	{
		return this.augenzahl;
	}

	@Override
	public String toString()
	{
		return "[" + this.augenzahl + "]";
	}

	public void setAktiv(final boolean aktiv)
	{
		btn.setSelected(!aktiv);
	}
}
