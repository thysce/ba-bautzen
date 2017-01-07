package org.wi16.yatzy;

public class Becher
{

	private final Würfel[] würfel;
	public static final int MAX_COUNT = 5;

	public Becher()
	{
		würfel = new Würfel[MAX_COUNT];
		for (int i = 0; i < würfel.length; i++)
		{
			würfel[i] = new Würfel();
		}
	}

	public void wurf()
	{
		for (Würfel w : würfel)
		{
			w.wurf();
		}
	}

	public Würfel[] getWürfel()
	{
		return würfel;
	}

	public boolean hasZahl(final int z)
	{
		return countZahl(z) > 0;
	}

	public int countZahl(final int z)
	{
		if (z <= 0 || z > 6)
		{
			return 0;
		} else
		{
			int sum = 0;
			for (Würfel w : this.würfel)
			{
				if (w.getAugenzahl() == z)
				{
					sum++;
				}
			}
			return sum;
		}
	}
}