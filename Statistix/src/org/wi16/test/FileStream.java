package org.wi16.test;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileStream implements Stream
{

	private final DataInputStream in;

	public FileStream(final File f) throws FileNotFoundException
	{
		in = new DataInputStream(new FileInputStream(f));
	}

	@Override
	public double next()
	{
		try
		{
			return in.readDouble();
		} catch (final IOException e)
		{
			return Double.NaN;
		}
	}
}
