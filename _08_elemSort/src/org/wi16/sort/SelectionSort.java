package org.wi16.sort;

public class SelectionSort<T extends Comparable<T>> implements Sort<T>
{

	@SuppressWarnings("unchecked")
	@Override
	public void sort(final T... elem)
	{
		int smallest;
		for (int i = 0; i < elem.length; i++)
		{
			smallest = i;
			for (int j = i + 1; j < elem.length; j++)
			{
				if (elem[j].compareTo(elem[smallest]) < 0)
				{
					smallest = j;
				}
			}
			if (i != smallest)
			{
				swap(i, smallest, elem);
			}
		}
	}

}
