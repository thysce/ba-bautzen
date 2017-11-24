package org.wi16.errcatch;

import javax.swing.JOptionPane;

public class Ausnahmen {

	public static void main(final String... args) {
		int a;
		a = 1;
		final String input = JOptionPane.showInputDialog("Eingabe:");

		// 24 LOC for 1 Operation,....... hm..... not that good...
		int c = Integer.MIN_VALUE;
		try {
			int b = Integer.parseInt(input);
			c = a / b;
			System.out.println(String.format("%d / %d = %d", a, b, c));
		} catch (final ArithmeticException e) {
			System.err.println("Es ist ein interner Fehler aufgetreten");
			System.err.print("Details: ");
			e.printStackTrace(System.err);
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
