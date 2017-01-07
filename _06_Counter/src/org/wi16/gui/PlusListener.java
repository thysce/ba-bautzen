package org.wi16.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.wi16.counter.Counter;

/**
 * Ein GUI-Listener der auf unseren Plus-Button hört.
 * 
 * @author Tim Trense
 */
public class PlusListener implements ActionListener
{

	private final Counter counter;

	public PlusListener(final Counter c)
	{
		this.counter = c;
	}

	/**
	 * Wird aufgerufen, wenn der Plus-Knopf gedrückt wird.
	 * Das Argument enthält nähere Informationen zu dem GUI-Ereignis, das passiert ist.
	 * Uns interessiert hier aber nur, DASS der Knopf gedrückt wurde, nicht wie.
	 */
	@Override
	public void actionPerformed(final ActionEvent event)
	{
		counter.plusEins();
	}

}
