package org.wi16.test;

public class Average implements StreamValue
{
	private double factor = 1;
	private double sum = Double.NaN;
	private double count = 0;

	@Override
	public void adaptValue(final double value)
	{
		if (Double.isFinite(sum))
			sum += value / factor;
		else
			sum = value / factor;
		while (sum > 1)
		{
			sum *= factor;
			factor++;
			sum /= factor;
		}
		count++;
	}

	public double get()
	{
		return sum * factor / count;
	}
}
