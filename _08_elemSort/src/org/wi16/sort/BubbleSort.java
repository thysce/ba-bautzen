package org.wi16.sort;

public class BubbleSort<T extends Comparable<T>> implements Sort<T>
{

	@SuppressWarnings("unchecked")
	@Override
	public void sort(final T... elem)
	{
		boolean swapDone;
		do
		{
			swapDone = false;
			for (int i = 1; i < elem.length; i++)
			{
				if (elem[i].compareTo(elem[i - 1]) < 0)
				{
					swap(i, i - 1, elem);
					swapDone = true;
				}
			}
		} while (swapDone);
	}

}
