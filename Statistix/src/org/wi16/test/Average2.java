package org.wi16.test;

public class Average2 implements StreamValue
{
	private double sum = Double.NaN;
	private double count = 0;

	@Override
	public void adaptValue(final double value)
	{
		if (Double.isFinite(sum))
			sum += value;
		else
			sum = value;

		count++;
	}

	public double get()
	{
		return sum / count;
	}
}
