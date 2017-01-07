package org.wi16.yatzy;

public class Becher
{

	private final W�rfel[] w�rfel;
	public static final int MAX_COUNT = 5;

	public Becher()
	{
		w�rfel = new W�rfel[MAX_COUNT];
		for (int i = 0; i < w�rfel.length; i++)
		{
			w�rfel[i] = new W�rfel();
		}
	}

	public void wurf()
	{
		for (W�rfel w : w�rfel)
		{
			w.wurf();
		}
	}

	public W�rfel[] getW�rfel()
	{
		return w�rfel;
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
			for (W�rfel w : this.w�rfel)
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