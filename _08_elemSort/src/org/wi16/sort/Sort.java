package org.wi16.sort;

/**
 * basic interface to define classes containing sorting algorithms
 * 
 * @author Tim Trense
 *
 * @param <T>
 *            the arguments for which the class can sort
 */
public interface Sort<T extends Comparable<T>>
{
	@SuppressWarnings("unchecked")
	public void sort(final T... elem);

	@SuppressWarnings("unchecked")
	public default void swap(final int a, final int b, final T... elem)
	{
		final T tmp = elem[b];
		elem[b] = elem[a];
		elem[a] = tmp;
	}
}