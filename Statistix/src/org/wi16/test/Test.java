package org.wi16.test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.wi16.stat.Row;
import org.wi16.stat.Table;

public class Test
{

	public static void maint(String[] args)
	{
		final Row r = new Row(16, 16.5, 17.5, 18, 17, 17, 16, 14, 17, 17, 17.5, 16.5, 17, 16, 14, 17.5, 18, 17, 16,
				17.5);
		System.out.println(r.average());
		System.out.println(r.geomAverage());
		System.out.println(r.standardDeviation());
		System.out.println(r.modal());
		System.out.println(r.median());
		System.out.println(r.span());
		System.out.println(r.iqr());
		System.out.println(r.variationCoefficient());

		final Row r1 = new Row(0, 0, 1, 3, 16);
		final Row r2 = new Row(16, 16, 17, 19, 32);
		System.out.println(r1.gini());
		System.out.println(r2.gini());

		final Table t = new Table(
				new double[][] { { 240, 120, 70 }, { 160, 90, 90 }, { 30, 30, 30 }, { 37, 7, 6 }, { 40, 32, 18 } });
		System.out.println(t.chiSqr());
		System.out.println(t.contingenceCoefficient());
	}

	public static void maint2(final String... args) throws IOException
	{
		final File file = new File("D:\\randomData");
		// final DataOutputStream out = new DataOutputStream(new
		// FileOutputStream(file));
		// final int c = 100000;
		// // out.writeInt(c);
		// for (int i = 0; i < c; i++)
		// out.writeDouble(Math.random());
		// out.close();
		System.out.println("written");
		final StreamStatistix stat = new StreamStatistix(new FileStream(file));
		stat.setUpdate(s -> {
			final double a = stat.get(Average.class).get();
			final double b = stat.get(Average2.class).get();
			System.out.println(a + "\t\t" + b + "\t\t" + (a - b));
		});
		stat.evaluate();
	}

	public static void main(final String... args) throws IOException
	{
		final Row x = new Row(10, 21, 39, 45, 54, 66, 87, 99, 109);
		final Row y = new Row(2, 5, 13, 17, 21, 22, 38, 40, 42);
		final double[] ab = Row.linearRegress(x, y);
		final double[] ab2 = Row.linearRegress2(x, y);
		System.out.println(Arrays.toString(ab));
		System.out.println(Arrays.toString(ab2));
		System.out.println(Row.correlationCoefficient(x, y));
	}
}
