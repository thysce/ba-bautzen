package org.wi16.test;

public class StreamStatistix
{

	private final Stream in;
	private final StreamValue[] vals;
	private StreamUpdater update;

	public StreamStatistix(final Stream in)
	{
		this.in = in;
		this.vals = new StreamValue[] { new Average(), new Average2() };
	}

	public void setUpdate(StreamUpdater update)
	{
		this.update = update;
	}

	public StreamUpdater getUpdate()
	{
		return update;
	}

	public void evaluate()
	{
		double current;
		while (Double.isFinite(current = in.next()))
		{
			for (StreamValue v : vals)
				v.adaptValue(current);
			if (update != null)
				update.onUpdate(this);
		}
	}

	public StreamValue get(final Class<?> type)
	{
		for (StreamValue v : vals)
			if (v.getClass().equals(type))
				return v;
		return null;
	}
}