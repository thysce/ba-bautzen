package org.wi16.sort;

public class InsertionSort<T extends Comparable<T>> implements Sort<T>
{

	@SuppressWarnings("unchecked")
	@Override
	public void sort(final T... elem)
	{
		int swapIndex;
		for (int i = 0; i < elem.length; i++)
		{
			swapIndex = i;
			for (int j = i - 1; j >= 0; j--)
			{
				if (elem[i].compareTo(elem[j]) < 0)
					swapIndex = j;
				else
					break;
			}
			if (i != swapIndex)
			{
				final T tmp = elem[i];
				transit(elem, swapIndex, i - swapIndex);
				elem[swapIndex] = tmp;
			}
		}
	}

	private void transit(final T[] elem, final int from, final int length)
	{
		for (int i = from + length - 1; i >= from; i--)
		{
			elem[i + 1] = elem[i];
		}
	}

}
