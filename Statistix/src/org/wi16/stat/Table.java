package org.wi16.stat;

/**
 * a class to hold a table of values
 * <p>
 * first dimension is height or Y, second width or X<br>
 * double[][] vals = new double[][]{{1,2},{3,4}}; means:<br>
 * <table border="1">
 * <tr>
 * <td>1
 * <td>2
 * <tr>
 * <td>3
 * <td>4
 * </table>
 * 
 * @author Tim Trense
 */
public class Table
{
	private final double[][] vals;

	public Table(final double[]... x)
	{
		this.vals = x;
	}

	public double[][] getVals()
	{
		return vals;
	}

	public double rowSum(final int row)
	{
		double s = 0;
		for (int i = 0; i < vals[row].length; i++)
			s += vals[row][i];
		return s;
	}

	public double colSum(final int col)
	{
		double s = 0;
		for (int i = 0; i < vals.length; i++)
			s += vals[i][col];
		return s;
	}

	public double sum()
	{
		double s = 0;
		for (int r = 0; r < vals.length; r++)
			for (int c = 0; c < vals[r].length; c++)
				s += vals[r][c];
		return s;
	}

	public double chiSqr()
	{
		final double n = sum();
		double s = 0;
		for (int r = 0; r < vals.length; r++)
		{
			final double rs = rowSum(r);
			for (int c = 0; c < vals[r].length; c++)
			{
				final double m = rs * colSum(c) / n;
				s += Math.pow(vals[r][c] - m, 2) / m;
			}

		}
		return s;
	}

	public double contingenceCoefficient()
	{
		final double c2 = chiSqr();
		final double n = sum();
		final double minJK = Math.min(vals[0].length, vals.length);
		return Math.sqrt(c2 / (c2 + n) * minJK / (minJK - 1));
	}
}