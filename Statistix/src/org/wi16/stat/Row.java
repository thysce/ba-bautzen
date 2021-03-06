package org.wi16.stat;

import java.util.Hashtable;
import java.util.Map;

/**
 * A class to hold a set of values of a characteristic and evaluate it. We mark
 * n as being the amount of the values stored internally.
 * 
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
	public int n()
	{
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

	/**
	 * calculates the linear regression between x and y where y is dependent to
	 * x<br>
	 * x.n() must equal y.n()
	 * 
	 * @param x
	 *            the abscissa
	 * @param y
	 *            the ordinate
	 * @return [a,b] where the regression-line is y=a+bx or null if x.n() !=
	 *         y.n()
	 */
	public static double[] linearRegress(final Row x, final Row y)
	{
		if (x.n() != y.n())
			return null;
		final double[] res = new double[2];
		res[1] = (coVariance(x, y)) / (x.variance());
		res[0] = y.average() - res[1] * x.average();
		return res;
	}

	/**
	 * calculates the linear regression between x and y where y is dependent to
	 * x<br>
	 * x.n() must equal y.n()
	 * <p>
	 * this is equal to but faster than linearRegress(Row, Row)<br>
	 * 
	 * @see #linearRegress(Row, Row)
	 * @param x
	 *            the abscissa
	 * @param y
	 *            the ordinate
	 * @return [a,b] where the regression-line is y=a+bx or null if x.n() !=
	 *         y.n()
	 */
	public static double[] linearRegress2(final Row x, final Row y)
	{
		if (x.n() != y.n())
			return null;
		final double n = x.n();
		double s = 0;
		for (int i = 0; i < x.data.length; i++)
			s += x.data[i] * y.data[i];
		double d = 0;
		for (int i = 0; i < x.data.length; i++)
			d += x.data[i] * x.data[i];
		final double[] res = new double[2];
		final double xa = x.average();
		final double ya = y.average();
		res[1] = (s - n * xa * ya) / (d - n * xa * xa);
		res[0] = ya - res[1] * xa;
		return res;
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

	/**
	 * implies positive linear correlation between parameters if result is
	 * highly positive and vice versa
	 * 
	 * @param a
	 *            parameter a
	 * @param b
	 *            parameter b
	 * @return 1/n * E((y-averageY) * (x-averageX), 0, n)
	 */
	public static double coVariance(final Row a, final Row b)
	{
		if (a.n() != b.n())
			return Double.NaN;
		double v = 0;
		final double aA = a.average();
		final double aB = b.average();
		for (int i = 0; i < a.n(); i++)
			v += (a.data[i] - aA) * (b.data[i] - aB);
		return 1d / a.n() * v;
	}

	/**
	 * determines how strongly related the both rows are, adapted from Pearson
	 * 
	 * @param a
	 *            parameter a
	 * @param b
	 *            parameter b with b.n() == a.n(), otherwise result will be
	 *            Double.NaN
	 * @return [-1d;1d] where 0 means no correlation, -1 strongly negative- and
	 *         1 strongly positive correlated
	 */
	public static double correlationCoefficient(final Row a, final Row b)
	{
		final double cv = coVariance(a, b);
		if (Double.isNaN(cv))
			return Double.NaN;
		return cv / (a.standardDeviation() * b.standardDeviation());
	}

}