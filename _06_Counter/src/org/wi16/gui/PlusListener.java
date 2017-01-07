package org.wi16.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.wi16.counter.Counter;

public class PlusListener implements ActionListener
{

	private final Counter counter;
	
	public PlusListener(final Counter c){
		this.counter = c;
	}
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		counter.plusEins();
	}

}
