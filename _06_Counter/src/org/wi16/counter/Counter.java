/*
 * This code is free of charge and may be edited, distributed and executed in any form but it must be marked as being developed by BABautzen_WI16.
 * This code is developed to not do any harm but the author is not responsible for any action done to the executing system.
 */
package org.wi16.counter;

/**
 * Eine Klasse um eine Zahl im Intervall zu halten.
 *
 * @author Tim Trense
 */
public class Counter
{

	private final int obergrenze;
	private int zahl, warnung;

	/**
	 * Wir benutzen ein Interface um GUI-Komponenten über Änderungen in unserem
	 * Datenmodell zu benachrichtigen
	 */
	private ValueChangeListener listener;

	/**
	 * Erzeugt einen neuen Counter, der Zahlen im Intervall [0;og[ zulässt
	 *
	 * @param og
	 *            die Obergrenze
	 */
	public Counter(final int og)
	{
		this.obergrenze = og;
		this.warnung = (int) (this.obergrenze * .9d);
		this.zahl = 0;
		this.setListener(null);
	}

	/**
	 * Erhöht den Zähler um 1 wenn möglich
	 *
	 * @return true, wenn der Zähler erhöht wurde
	 */
	public boolean plusEins()
	{
		if (this.zahl < this.obergrenze - 1)
		{
			this.zahl++;
			if(this.listener != null){
				this.listener.valueChanged(this);
			}
			return true;
		}
		return false;
	}

	/**
	 * Verringert den Zähler um 1 wenn möglich
	 *
	 * @return true, wenn der Zähler verringert wurde
	 */
	public boolean minusEins()
	{
		if (this.zahl > 0)
		{
			this.zahl--;
			if(this.listener != null){
				this.listener.valueChanged(this);
			}
			return true;
		}
		return false;
	}

	/**
	 * @return der aktuelle Zählerstand
	 */
	public int getZahl()
	{
		return this.zahl;
	}

	/**
	 * @return das obere exklusive Ende des Intervalls
	 */
	public int getObergrenze()
	{
		return obergrenze;
	}

	/**
	 * Prüft ob der Zähler im kritischen Bereich liegt
	 *
	 * @return true, falls Zahl >= Warnungsgrenze
	 */
	public boolean istKritischHoch()
	{
		return this.zahl >= this.warnung;
	}

	/**
	 * Gibt die Warnungsgrenze zurück. Diese liegt zwischen 0 und der Obergrenze
	 *
	 * @return die Warnungsgrenze
	 */
	public int getWarnung()
	{
		return warnung;
	}

	/**
	 * Legt die Warnungsgrenze fest
	 *
	 * @param warnung
	 *            eine Zahl zwischen ]0;obergrenze[
	 */
	public void setWarnung(final int warnung)
	{
		if (warnung > 0 && warnung < this.obergrenze)
		{
			this.warnung = warnung;
		}
	}

	public ValueChangeListener getListener()
	{
		return listener;
	}

	public void setListener(final ValueChangeListener listener)
	{
		this.listener = listener;
	}

}
