package org.wi16.errcatch;

import javax.swing.JOptionPane;

public class Ausnahmen {

	public static int div(final int a, final int b) throws DivideByZeroException, IntegerDivisionHasRestException {
		int result = -1;
		try {
			result = a / b;
		} catch (final ArithmeticException e) {
			throw new DivideByZeroException();
		}
		int rest = a % b;
		if (rest != 0)
			throw new IntegerDivisionHasRestException(result, rest);
		return result;
	}

	public static void main(final String... args) {
		final String inputa = JOptionPane.showInputDialog("Eingabe Dividend");
		final String inputb = JOptionPane.showInputDialog("Eingabe Divisor");

		// 24 LOC for 1 Operation,....... hm..... not that good...
		int c = Integer.MIN_VALUE;
		try {
			final int a = Integer.parseInt(inputa);
			final int b = Integer.parseInt(inputb);
			c = div(a, b);
			System.out.println(String.format("%d / %d = %d", a, b, c));
		} catch (final DivideByZeroException e) {
			System.err.println("Es ist ein interner Fehler aufgetreten");
			System.err.print("Details: ");
			e.printStackTrace(System.err);
		} catch (final IntegerDivisionHasRestException e) {
			System.out.println("Hinweis: Die Division war nicht ganzzahlig, aber das Ergebnis ist:");
			System.out.println(String.format("%s / %s = %d, Rest %d", inputa, inputb, e.getResult(), e.getRest()));
		} catch (final NumberFormatException e) {
			System.err.println("Die Eingabe war fehlerhaft");
			System.err.print("Details: ");
			e.printStackTrace(System.err);
		} catch (final Exception e) {
			System.err.println("Es ist ein unbekannter leichter Fehler aufgetreten");
			System.err.print("Details: ");
			e.printStackTrace(System.err);
		} catch (final Throwable e) {
			System.err.println("Es ist ein unbekannter schwerer Fehler aufgetreten");
			System.err.print("Details: ");
			e.printStackTrace(System.err);
		} finally {
			System.out.println("Programm beendet");
		}
		System.out.println("Programmletzte Zeile");
	}

}
