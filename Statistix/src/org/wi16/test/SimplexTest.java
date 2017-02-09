package org.wi16.test;

import java.util.Arrays;

import org.wi16.stat.Simplex;

public class SimplexTest
{

	public static void main(String[] args)
	{
		// final double[] c = new double[]{1, 5};
		// final double[][] nb = new double[][]{
		// {5,10,1,0,0,50},
		// {9,5,0,1,0, 45},
		// {0,10,0,0,1,40}
		// };

		final double[] c = new double[] { 1, 2 };
		final double[][] nb = new double[][] { { 1, 1, 1, 0, 0, 100 }, { 6, 9, 0, 1, 0, 720 }, { 0, 1, 0, 0, 1, 60 } };
		final Simplex s = new Simplex(c, nb);
		s.simplex();
		System.out.println(s.getOptimum());
		System.out.println(Arrays.toString(s.getOptimalValues()));
	}

}
