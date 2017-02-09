package org.wi16.stat;

import java.util.Arrays;

public class Simplex
{
	private final double[][] tableau;
	private final int[] res;
	private boolean done;

	public Simplex(final double[] c, final double[][] nb)
	{
		done = false;
		res = new int[c.length];
		tableau = new double[nb.length + 1][nb[0].length + 1];
		for (int i = 0; i < c.length; i++)
			tableau[tableau.length - 1][i] = c[i];
		for (int i = 0; i < nb.length; i++)
			for (int j = 0; j < nb[i].length; j++)
			{
				tableau[i][j] = nb[i][j];
			}
	}

	public void simplex()
	{
		if (done)
			return;
		else
			done = true;
		final int zfz = tableau.length - 1;
		final int q = tableau[0].length - 1;
		final int b = q - 1;
		boolean best;
		simplex: do
		{
			best = true;
			besttest: for (int i = 0; i < b; i++)
			{
				if (tableau[zfz][i] > 0)
				{
					best = false;
					break besttest;
				}
			}
			if (best)
				break simplex;

			int pivotCol = 0;
			for (int i = 0; i < b; i++)
			{
				if (tableau[zfz][i] > tableau[zfz][pivotCol])
					pivotCol = i;
			}

			for (int i = 0; i < tableau.length; i++)
			{
				tableau[i][q] = tableau[i][b] / tableau[i][pivotCol];
			}

			int pivotRow = 0;
			for (int i = 0; i < zfz; i++)
			{
				if (tableau[i][q] < tableau[pivotRow][q])
					pivotRow = i;
			}
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
			res[pivotCol] = pivotRow;
		} while (!best);
	}

	public double getOptimum()
	{
		return -tableau[tableau.length - 1][tableau[tableau.length - 1].length - 2];
	}
	
	public double[] getOptimalValues(){
		final double[] ov = new double[res.length];
		for(int i = 0; i < ov.length;i++){
			ov[i] = tableau[res[i]][tableau[0].length-2];
		}
		return ov;
	}

	@Override
	public String toString()
	{
		final StringBuilder b = new StringBuilder();
		for (int i = 0; i < tableau.length; i++)
		{
			b.append(Arrays.toString(tableau[i]) + "\n");
		}
		return b.toString();
	}
}