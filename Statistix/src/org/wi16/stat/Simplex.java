package org.wi16.stat;

/**
 * implementing the simplex-algorithm for searching optimal values such that a
 * term becomes maximal under certain conditions
 * 
 * @see #Simplex(double[], double[][])
 * @see #getOptimum()
 * @see #getOptimalValues()
 * 
 * @author Tim Trense
 */
public class Simplex
{
	private final double[][] tableau;
	private final int[] res;
	private boolean done, solved;
	private final boolean log;

	// helping values
	final int zfz;
	final int q;
	final int b;
	final int maxSwap;

	/**
	 * constructs a simplex-task searching the optimal x1, x2, ..., xn for
	 * ax1+bx2+...+zxn to become maximal under the conditions:<br>
	 * a1x1+b1x2+...+z1xn + w1= MAX1 & a2x1+b2x2+...+z2xn +w2= MAX2 & ...<br>
	 * 
	 * @param c
	 *            the coefficients of the term ax1+bx2+...+zxn. [a,b,...,z]
	 * @param nb
	 *            the coefficient-matrix for the conditions
	 *            [[a1,b1,...,z1,w1,MAX1],[a2,b2,...,z2,MAX2],...]
	 * @param log
	 *            whether to sys-out the steps of the algorithm
	 */
	public Simplex(final double[] c, final double[][] nb, final boolean log)
	{
		this.log = log;
		done = false;
		solved = false;
		res = new int[c.length];
		for (int i = 0; i < res.length; i++)
			res[i] = -1;
		tableau = new double[nb.length + 1][nb[0].length + 1];
		for (int i = 0; i < c.length; i++)
			tableau[tableau.length - 1][i] = c[i];
		for (int i = 0; i < nb.length; i++)
			for (int j = 0; j < nb[i].length; j++)
			{
				tableau[i][j] = nb[i][j];
			}

		zfz = tableau.length - 1;
		q = tableau[0].length - 1;
		b = q - 1;
		maxSwap = c.length;
	}

	public boolean isSolved()
	{
		return solved;
	}

	/**
	 * executes the simplex-algorithm
	 * 
	 * @see #simplexLogged()
	 * @return whether the simplex-algorithm terminated successfully
	 */
	public boolean simplex()
	{
		if (done)
			return false;
		else
			done = true;

		int swapCount = 0;
		try
		{
			if (log)
				System.out.println(toString(snapshot(), 2));
			boolean best = simplexStep(true);
			swapCount++;
			while (!best && swapCount < maxSwap)
			{
				if (log)
					System.out.println(toString(snapshot(), 2));
				best = simplexStep(false);
				swapCount++;
			}
			if (log)
				System.out.println(toString(snapshot(), 2));
		} catch (final ArithmeticException e)
		{
			return false;
		}
		return solved = (swapCount <= maxSwap);
	}

	private double[][] snapshot()
	{
		final double[][] sh = new double[tableau.length][tableau[0].length];
		for (int i = 0; i < sh.length; i++)
		{
			for (int j = 0; j < sh[i].length; j++)
			{
				sh[i][j] = tableau[i][j];
			}
		}
		return sh;
	}

	private boolean simplexStep(final boolean firstCheck)
	{
		if (firstCheck && checkBest())
			return true;

		final int pivotCol = findPivotCol();
		calcQ(pivotCol);

		final int pivotRow = findPivotRow();
		reduceForm(pivotRow, pivotCol);

		if (pivotCol < res.length)
			res[pivotCol] = pivotRow;
		else
			throw new ArithmeticException("simplex-algorithm has no solution");
		return checkBest();
	}

	private boolean checkBest()
	{
		boolean best = true;
		besttest: for (int i = 0; i < b; i++)
		{
			if (tableau[zfz][i] > 0)
			{
				best = false;
				break besttest;
			}
		}
		return best;
	}

	private int findPivotCol()
	{
		int pivotCol = 0;
		for (int i = 0; i < b; i++)
		{
			if (tableau[zfz][i] > tableau[zfz][pivotCol])
				pivotCol = i;
		}
		return pivotCol;
	}

	private void calcQ(final int pivotCol)
	{
		for (int i = 0; i < tableau.length; i++)
		{
			if (Double.isFinite(tableau[i][q]))
				tableau[i][q] = tableau[i][b] / tableau[i][pivotCol];
		}
	}

	private int findPivotRow()
	{
		int pivotRow = 0;
		for (int i = 0; i < zfz; i++)
		{
			if (tableau[i][q] < tableau[pivotRow][q])
				pivotRow = i;
		}
		return pivotRow;
	}

	private void reduceForm(final int pivotRow, final int pivotCol)
	{
		final double pivotVal = tableau[pivotRow][pivotCol];
		for (int i = 0; i < tableau.length; i++)
		{
			if (i == pivotRow)
				continue;
			final double factor = tableau[i][pivotCol] / pivotVal;
			for (int j = 0; j < tableau[i].length; j++)
			{
				tableau[i][j] -= tableau[pivotRow][j] * factor;
			}
		}
		for (int i = 0; i <= q; i++)
		{
			tableau[pivotRow][i] /= pivotVal;
		}
		tableau[pivotRow][q] = Double.POSITIVE_INFINITY;
	}

	/**
	 * @return the maximal value of the term ax1+bx2+...+zxn under the given
	 *         conditions. returns NaN if the algorithm was not executed yet
	 */
	public double getOptimum()
	{
		if (done)
			return -tableau[tableau.length - 1][tableau[tableau.length - 1].length - 2];
		else
			return Double.NaN;
	}

	/**
	 * @return the vector of values for the [x1, x2, ..., xn] to get the
	 *         optimum. returns null if the algorithm was not executed yet
	 */
	public double[] getOptimalValues()
	{
		if (done)
		{
			final double[] ov = new double[res.length];
			for (int i = 0; i < ov.length; i++)
			{
				if (res[i] >= 0) // if
									// (Double.isInfinite(tableau[res[i]][tableau[res[i]].length
									// - 1]))
					ov[i] = tableau[res[i]][tableau[0].length - 2];
				else
					ov[i] = 0;
			}
			return ov;
		} else
			return null;
	}

	/**
	 * returns a string-representation of the simplex-tableau
	 */
	@Override
	public String toString()
	{
		return toString(tableau, 2);
	}

	/**
	 * @param values
	 *            a 2-dimensional array of values
	 * @param precision
	 *            the number of digits after the decimal-separator for each
	 *            value
	 * @return a string-representation of that array with the first dimension as
	 *         it's height
	 */
	public static String toString(final double[][] values, final int precision)
	{
		final StringBuilder b = new StringBuilder();
		for (int i = 0; i < values.length; i++)
		{
			for (int j = 0; j < values[i].length; j++)
			{
				b.append(String.format("%1$+." + precision + "f\t", values[i][j]));
			}
			b.append("\n");
		}
		return b.toString();
	}
}