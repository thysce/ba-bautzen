package org.wi16.errcatch;

@SuppressWarnings("serial")
public class DivideByZeroException extends ArithmeticException {

	public DivideByZeroException() {
		super("division by zero");
	}
}
