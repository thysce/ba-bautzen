package org.wi16.stat;

import java.util.Hashtable;
import java.util.Map;

/**
 * A class to hold a set of values of a characteristic and evaluate it. We mark
 * n as being the amount of the values stored internally.
 * @see #n()
 * 
 * @author Tim Trense
 **/
public class Row
{
	private final double[] data;

	/**
	 * saves the parameter internally
	 * 
	 * @param data
	 *            the values for the characteristic
	 */
	public Row(final double... data)
	{
		this.data = data;
	}

	/**
	 * @return a reference to the internal storage
	 */
	public double[] getData()
	{
		return this.data;
	}

	/**
	 * @return the amount of values in this row as an integer
	 */
	public int n(){
		return data.length;
	}
	
	/**
	 * sums up all values
	 * 
	 * @return E(x, 0, n)
	 * @see #sum(int, int)
	 */
	public double sum()
	{
		return sum(0, n());
	}

	/**
	 * sums up all value from offset to offset+length-1
	 * 
	 * @param offset
	 *            the first value to sum
	 * @param length
	 *            the amount of values inclusive offset to sum
	 * @return E(x, offset, offset+length-1)
	 */
	public double sum(final int offset, final int length)
	{
		double sum = 0;
		for (int i = 0; i < length; i++)
			sum += data[offset + i];
		return sum;
	}

	/**
	 * multiplies up all values
	 * 
	 * @return P(x, 0, n)
	 */
	public double product()
	{
		return product(0, n());
	}

	/**
	 * multiplies up all value from offset to offset+length-1
	 * 
	 * @param offset
	 *            the first value to sum
	 * @param length
	 *            the amount of values inclusive offset to sum
	 * @return P(x, offset, offset+length-1)
	 */
	public double product(final int offset, final int length)
	{
		double prod = 1;
		for (int i = 0; i < length; i++)
			prod *= data[offset + i];
		return prod;
	}

	/**
	 * computes the grouped commonness of each unique value in the row
	 * 
	 * @return map unique value to int commonness
	 * @see #relativeCommonness()
	 */
	public Map<Double, Double> absoluteCommonness()
	{
		final Hashtable<Double, Double> cmn = new Hashtable<>();
		for (int i = 0; i < n(); i++)
		{
			if (cmn.containsKey(data[i]))
				cmn.put(data[i], cmn.get(data[i]) + 1);
			else
				cmn.put(data[i], 1d);
		}
		return cmn;
	}

	/**
	 * computes the grouped commonness of each unique value in the row divied by
	 * n
	 * 
	 * @return map unique value to double commonness [0d;1d]
	 * @see #absoluteCommonness()
	 */
	public Map<Double, Double> relativeCommonness()
	{
		final Map<Double, Double> cmn = absoluteCommonness();
		for (Double d : cmn.keySet())
		{
			cmn.put(d, cmn.get(d) / n());
		}
		return cmn;
	}

	/**
	 * the average value of all values
	 * 
	 * @return 1/n * E(x, 0, n)
	 */
	public double average()
	{
		return 1d / n() * sum();
	}

	/**
	 * the geometric average value of all values
	 * 
	 * @return root(P(x, 0, n), n)
	 */
	public double geomAverage()
	{
		return Math.pow(product(), 1d / n());
	}

	/**
	 * the average value of all values multiplied by their absolute commonness
	 * 
	 * @return 1/n * E(x*n(x), 0, n)
	 */
	public double averageAbsolute()
	{
		final Map<Double, Double> absCmn = absoluteCommonness();
		double sum = 0;
		for (int i = 0; i < n(); i++)

			sum += data[i] * absCmn.get(data[i]);
		return 1d / n() * sum;
	}

	/**
	 * the average value of all values multiplied by their relative commonness
	 * 
	 * @return 1/n * E(x*h(x), 0, n)
	 */
	public double averageRelative()
	{
		final Map<Double, Double> relCmn = relativeCommonness();
		double sum = 0;
		for (int i = 0; i < n(); i++)

			sum += data[i] * relCmn.get(data[i]);
		return 1d / n() * sum;
	}

	/**
	 * the value that is maximally common
	 * 
	 * @return x such that n(x) is max
	 */
	public double modal()
	{
		double m = data[0];
		final Map<Double, Double> absCmn = absoluteCommonness();
		double cmn = absCmn.get(m);
		double currCmn = cmn;
		for (int i = 1; i < n(); i++)
			if ((currCmn = absCmn.get(data[i])) > cmn)
			{
				m = data[i];
				cmn = currCmn;
			}
		return m;
	}

	/**
	 * @return quantil(0.5)
	 * @see #quantil(double)
	 */
	public double median()
	{
		return quantil(0.5);
	}

	/**
	 * @param q
	 *            the quantil-spot [0d;1d]
	 * @return x(i) such that E(h(x(ii)), 0, i) = q
	 */
	public double quantil(final double q)
	{
		if (q < 0 || q > 1)
			return Double.NaN;
		double w = 0;
		final Map<Double, Double> cmn = relativeCommonness();
		for (int i = 0; i < n(); i++)
		{
			w += cmn.get(data[i]);
			if (w >= q)
				return data[i];
		}
		return Double.NaN;
	}

	/**
	 * @return the square-root of the variance
	 * @see #variance()
	 * @see #dispersion(double)
	 */
	public double standardDeviation()
	{
		return Math.sqrt(variance());
	}

	/**
	 * @return the dispersion around the average
	 * @see #average()
	 * @see #dispersion(double)
	 */
	public double variance()
	{
		return dispersion(average());
	}

	/**
	 * the mean dispersion of all values around v
	 * 
	 * @param v
	 *            the spot to evaluate
	 * @return 1/n * E(x-v, 0, n)
	 */
	public double dispersion(final double v)
	{
		double sum = 0;
		for (int i = 0; i < n(); i++)
			sum += Math.pow(data[i] - v, 2);
		return 1d / n() * sum;
	}

	/**
	 * @return max - min
	 * @see #max()
	 * @see #min()
	 */
	public double span()
	{
		return max() - min();
	}

	/**
	 * the greatest value in the row
	 * 
	 * @return x(i) such that x(i) > x(ii) where i != ii
	 * @see #min()
	 */
	public double max()
	{
		double m = data[0];
		for (int i = 1; i < n(); i++)
			if (data[i] > m)
				m = data[i];
		return m;
	}

	/**
	 * the smallest value in the row
	 * 
	 * @return x(i) such that x(i) < x(ii) where i != ii
	 * @see #max()
	 */
	public double min()
	{
		double m = data[0];
		for (int i = 1; i < n(); i++)
			if (data[i] < m)
				m = data[i];
		return m;
	}

	/**
	 * inter-quarter-range
	 * 
	 * @return .75-quantil - .25-quantil
	 */
	public double iqr()
	{
		return quantil(0.75) - quantil(0.25);
	}

	/**
	 * @return average / standardDeviation
	 * @see #average()
	 * @see #standardDeviation()
	 */
	public double variationCoefficient()
	{
		return average() / standardDeviation();
	}

	/**
	 * the gini-coefficient for this row
	 * 
	 * @return 1/(2average*n*n) * EE(|xi-xj|, 0, n)
	 */
	public double gini()
	{
		double gini = 0;
		for (int i = 0; i < n(); i++)
			for (int j = 0; j < n(); j++)
				gini += Math.abs(data[i] - data[j]);
		return 1d / (2 * average() * Math.pow(n(), 2)) * gini;
	}

	@Override
	public String toString()
	{
		final StringBuilder b = new StringBuilder();
		b.append("[" + data[0]);
		for (int i = 1; i < n(); i++)
			b.append(", " + data[i]);
		b.append("]");
		return b.toString();
	}

	@Override
	public boolean equals(final Object obj)
	{
		if (this == obj)
			return true;
		if (!(obj instanceof Row))
			return false;
		final Row r = (Row) obj;
		if (this.n() != r.n())
			return false;
		for (int i = 0; i < this.n(); i++)
			if (this.data[i] != r.data[i])
				return false;
		return true;
	}

}