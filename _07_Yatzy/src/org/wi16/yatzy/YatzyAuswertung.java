package org.wi16.yatzy;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class YatzyAuswertung
{

	public static final String EINER = "Einser", ZWEIER = "Zweier", DREIER = "Dreier", VIERER = "Vierer",
			FUNFER = "Fünfer", SECHSER = "Sechser";

	public static final String PASCH3 = "Dreierpasch", PASCH4 = "Viererpasch", FULLHOUSE = "Full House",
			STRASSE_KL = "kleine Straße", STRASSE_GR = "große Straße", YATZEE = "Yatzee!!", CHANCE = "Chance";

	public static Collection<String> berechneMöglicheErgebnisse(final Spieler spieler, final Becher becher)
	{
		final List<String> e = new LinkedList<String>();
		final Map<String, Integer> sergs = spieler.getErgebnisse();

		if (becher == null)
		{
			if (!sergs.containsKey(CHANCE))
				e.add(CHANCE);
			if (!sergs.containsKey(EINER))
				e.add(EINER);
			if (!sergs.containsKey(ZWEIER))
				e.add(ZWEIER);
			if (!sergs.containsKey(DREIER))
				e.add(DREIER);
			if (!sergs.containsKey(VIERER))
				e.add(VIERER);
			if (!sergs.containsKey(FUNFER))
				e.add(FUNFER);
			if (!sergs.containsKey(SECHSER))
				e.add(SECHSER);
			if (!sergs.containsKey(PASCH3))
				e.add(PASCH3);
			if (!sergs.containsKey(PASCH4))
				e.add(PASCH4);
			if (!sergs.containsKey(YATZEE))
				e.add(YATZEE);
			if (!sergs.containsKey(FULLHOUSE))
				e.add(FULLHOUSE);
			if (!sergs.containsKey(STRASSE_GR))
				e.add(STRASSE_GR);
			if (!sergs.containsKey(STRASSE_KL))
				e.add(STRASSE_KL);
		} else
		{
			if (!sergs.containsKey(CHANCE))
			{
				e.add(CHANCE);
			}
			if (!sergs.containsKey(EINER) && becher.countZahl(1) >= 3)
			{
				e.add(EINER);
			}
			if (!sergs.containsKey(ZWEIER) && becher.countZahl(2) >= 3)
			{
				e.add(ZWEIER);
			}
			if (!sergs.containsKey(DREIER) && becher.countZahl(3) >= 3)
			{
				e.add(DREIER);
			}
			if (!sergs.containsKey(VIERER) && becher.countZahl(4) >= 3)
			{
				e.add(VIERER);
			}
			if (!sergs.containsKey(FUNFER) && becher.countZahl(5) >= 3)
			{
				e.add(FUNFER);
			}
			if (!sergs.containsKey(SECHSER) && becher.countZahl(6) >= 3)
			{
				e.add(SECHSER);
			}
			if (!sergs.containsKey(PASCH3))
			{
				for (int i = 1; i < 7; i++)
					if (becher.countZahl(i) == 3)
					{
						e.add(PASCH3);
						break;
					}
			}
			if (!sergs.containsKey(PASCH4))
			{
				for (int i = 1; i < 7; i++)
					if (becher.countZahl(i) == 4)
					{
						e.add(PASCH4);
						break;
					}
			}
			if (!sergs.containsKey(YATZEE))
			{
				for (int i = 1; i < 7; i++)
					if (becher.countZahl(i) == 5)
					{
						e.add(YATZEE);
						break;
					}
			}
			if (!sergs.containsKey(FULLHOUSE))
			{
				boolean has3 = false, has2 = false;
				for (int i = 1; i < 7; i++)
					if (becher.countZahl(i) == 3)
					{
						has3 = true;
						break;
					}
				if (has3)
					for (int i = 1; i < 7; i++)
						if (becher.countZahl(i) == 2)
						{
							has2 = true;
							break;
						}
				if (has3 && has2)
					e.add(FULLHOUSE);
			}
			if (!sergs.containsKey(STRASSE_KL))
			{
				if ((becher.hasZahl(1) && becher.hasZahl(2) && becher.hasZahl(3) && becher.hasZahl(4))
						|| (becher.hasZahl(2) && becher.hasZahl(3) && becher.hasZahl(4) && becher.hasZahl(5))
						|| (becher.hasZahl(3) && becher.hasZahl(4) && becher.hasZahl(5) && becher.hasZahl(6)))
				{
					e.add(STRASSE_KL);
				}
			}
			if (!sergs.containsKey(STRASSE_GR))
			{
				if ((becher.hasZahl(1) && becher.hasZahl(2) && becher.hasZahl(3) && becher.hasZahl(4)
						&& becher.hasZahl(5))
						|| (becher.hasZahl(2) && becher.hasZahl(3) && becher.hasZahl(4) && becher.hasZahl(5)
								&& becher.hasZahl(6)))
				{
					e.add(STRASSE_GR);
				}
			}
		}
		return e;
	}

	public static int berechnePunkte(final Becher becher, final String entscheidung)
	{
		switch (entscheidung)
		{
		case CHANCE:
			return chance(becher);
		case EINER:
			return zahl(becher, 1);
		case ZWEIER:
			return zahl(becher, 2);
		case DREIER:
			return zahl(becher, 3);
		case VIERER:
			return zahl(becher, 4);
		case FUNFER:
			return zahl(becher, 5);
		case SECHSER:
			return zahl(becher, 6);
		case PASCH3:
			return pasch(becher, 3);
		case PASCH4:
			return pasch(becher, 4);
		case YATZEE:
			return pasch(becher, 5);
		case STRASSE_KL:
			return strKL(becher);
		case STRASSE_GR:
			return strGR(becher);
		case FULLHOUSE:
			return fullhouse(becher);
		default:
			return 0;
		}
	}

	private static int fullhouse(Becher becher)
	{
		boolean has3 = false, has2 = false;
		for (int i = 1; i < 7; i++)
			if (becher.countZahl(i) == 3)
			{
				has3 = true;
				break;
			}
		if (has3)
			for (int i = 1; i < 7; i++)
				if (becher.countZahl(i) == 2)
				{
					has2 = true;
					break;
				}
		if (has3 && has2)
			return 25;
		else
			return 0;
	}

	private static int strKL(Becher becher)
	{
		if ((becher.hasZahl(1) && becher.hasZahl(2) && becher.hasZahl(3) && becher.hasZahl(4))
				|| (becher.hasZahl(2) && becher.hasZahl(3) && becher.hasZahl(4) && becher.hasZahl(5))
				|| (becher.hasZahl(3) && becher.hasZahl(4) && becher.hasZahl(5) && becher.hasZahl(6)))
			return 30;
		else
			return 0;
	}

	private static int strGR(Becher becher)
	{
		if ((becher.hasZahl(1) && becher.hasZahl(2) && becher.hasZahl(3) && becher.hasZahl(4) && becher.hasZahl(5))
				|| (becher.hasZahl(2) && becher.hasZahl(3) && becher.hasZahl(4) && becher.hasZahl(5)
						&& becher.hasZahl(6)))
			return 40;
		else
			return 0;
	}

	private static int pasch(final Becher becher, final int z)
	{
		for (int i = 1; i < 7; i++)
			if (becher.countZahl(i) == z)
			{
				if (z < 5)
					return chance(becher);
				else
					return 50; // YATZEE
			}
		return 0;
	}

	private static int zahl(final Becher becher, final int i)
	{
		return becher.countZahl(i) * i;
	}

	private static int chance(final Becher becher)
	{
		int sum = 0;
		for (Würfel w : becher.getWürfel())
		{
			sum += w.getAugenzahl();
		}
		return sum;
	}
}
