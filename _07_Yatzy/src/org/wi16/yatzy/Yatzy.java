package org.wi16.yatzy;

public class Yatzy
{

	public static void main(String[] args)
	{

		final Spieler a = new Spieler("Paul");
		final Spieler b = new Spieler("Richard");
		final Spieler c = new Spieler("Franzi");

		final Spiel sb = new Spiel(new Spieler[] { a, b, c });
		final Fenster f = new Fenster(sb);
		f.setVisible(true);
		sb.nächsterSpieler(null, false);
	}

}
