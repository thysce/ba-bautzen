package org.wi16.errcatch;

@SuppressWarnings("serial")
public class IntegerDivisionHasRestException extends ArithmeticException {

	private final int result;
	private final int rest;

	public IntegerDivisionHasRestException(final int result, final int rest) {
		super("integer division has rest");
		this.rest = rest;
		this.result = result;
	}
	
	public int getResult() {
		return result;
	}
	
	public int getRest() {
		return rest;
	}
}
