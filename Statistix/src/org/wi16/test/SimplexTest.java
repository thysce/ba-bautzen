package org.wi16.test;

import java.util.Arrays;
import java.util.List;

import org.wi16.stat.Simplex;

public class SimplexTest
{

	public static void main(String[] args)
	{
		// final double[] c = new double[] { 1, 5 };
		// final double[][] nb = new double[][] { { 5, 10, 1, 0, 0, 50 }, { 9,
		// 5, 0, 1, 0, 45 }, { 0, 10, 0, 0, 1, 40 } };
		// final double[] c = new double[] { 1, 2 };
		// final double[][] nb = new double[][] { { 1, 1, 1, 0, 0, 100 }, { 6,
		// 9, 0, 1, 0, 720 }, { 0, 1, 0, 0, 1, 60 } };
		// final double[] c = new double[] { 5, 4, -32, -24 };
		// final double[][] nb = new double[][] { { 1, 3, -7, -5, 1, 0, 0, 5 },
		// { -1, 1, 6, 5, 0, 1, 0, 3 } };
		final double[] c = new double[] { 6, 10, 8, 12 };
		final double[][] nb = new double[][] { { 3, 2, -1, 2, 1, 0, 0, 0, 0, 60 }, { 1, 1, 2, 1, 0, 1, 0, 0, 0, 45 },
				{ 2, -4, -3, 4, 0, 0, 1, 0, 0, 90 }, { 4, -6, 4, 3, 0, 0, 0, 1, 0, 120 },
				{ 1, 2, -2, 2, 0, 0, 0, 0, 1, 30 } };
		final Simplex s = new Simplex(c, nb);
		final List<double[][]> log = s.simplexLogged();

		for (double[][] l : log)
		{
			System.out.println(Simplex.toString(l, 2));
		}

		System.out.println(s.getOptimum());
		System.out.println(Arrays.toString(s.getOptimalValues()));
	}

}
