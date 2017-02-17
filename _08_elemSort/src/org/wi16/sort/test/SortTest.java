package org.wi16.sort.test;

import java.util.Arrays;
import java.util.Random;

import org.wi16.sort.InsertionSort;
import org.wi16.sort.Sort;

public class SortTest
{

	public static void main(String[] args)
	{
		final Integer[] testArray = new Integer[10];
		final Random r = new Random();
		for (int i = 0; i < testArray.length; i++)
		{
			testArray[i] = Integer.valueOf(r.nextInt(10));
		}
		System.out.println("before sort: \t" + Arrays.toString(testArray));
		final Sort<Integer> sortingAlgorithm;
		sortingAlgorithm = new InsertionSort<Integer>();
		// sortingAlgorithm = new BubbleSort<Integer>();
		// sortingAlgorithm = new SelectionSort<Integer>();
		sortingAlgorithm.sort(testArray);
		System.out.println("after sort: \t" + Arrays.toString(testArray));
	}

}
