package org.wi16.test;

import org.wi16.stat.Table;

public class Test
{

	public static void main(String[] args)
	{
//		final Row r = new Row(16, 16.5, 17.5, 18, 17, 17, 16, 14, 17, 17, 17.5, 16.5, 17, 16, 14, 17.5, 18, 17, 16,
//				17.5);
//		System.out.println(r.average());
//		System.out.println(r.geomAverage());
//		System.out.println(r.standardDeviation());
//		System.out.println(r.modal());
//		System.out.println(r.median());
//		System.out.println(r.span());
//		System.out.println(r.iqr());
//		System.out.println(r.variationCoefficient());
		
		final Table t = new Table(new double[][]{{240,120,70},{160,90,90},{30,30,30},{37,7,6},{40,32,18}});
		System.out.println(t.chiSqr());
		System.out.println(t.contingenceCoefficient());
	}

}
