package org.wi16.counter;

/**
 * Ein Interface um der GUI zu sagen, dass sich unser Datenmodell geändert hat
 * und nun der Zeitpunkt zum Aktualisieren der Anzeige gekommen ist.
 * 
 * @author Tim Trense
 */
public interface ValueChangeListener
{

	/**
	 * Wird aufgerufen, wenn auf dem übergebenen Counter plusEins() oder
	 * minusEins() aufgerufen wird.
	 * 
	 * @param c
	 *            das Datenmodell, das angezeigt werden soll
	 */
	public void valueChanged(final Counter c);
}
