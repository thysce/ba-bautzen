package org.wi16.yatzy;

import java.util.HashMap;
import java.util.Map;

public class Spieler
{

	private final String name;
	private final Map<String, Integer> ergebnisse;

	public Spieler(final String name)
	{
		this.name = name;
		this.ergebnisse = new HashMap<String, Integer>();
		this.resetScore();
	}

	public Map<String, Integer> getErgebnisse()
	{
		return this.ergebnisse;
	}

	public String getName()
	{
		return this.name;
	}

	public int getScore()
	{
		int sum = 0;
		for (int i : this.ergebnisse.values())
		{
			sum += i;
		}
		return sum;
	}

	public void increaseScore(final String erg, final int val)
	{
		this.ergebnisse.put(erg, val);
	}

	public void resetScore()
	{
		this.ergebnisse.clear();
	}
}