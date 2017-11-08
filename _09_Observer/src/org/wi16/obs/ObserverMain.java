package org.wi16.obs;

import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Tim Trense
 */
public class ObserverMain {

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		final Zahl stand = new Zahl();

		final JFrame frameMain = new JFrame("Test");
		frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final ZahlView view = new ZahlView(stand);
		final JButton btnInc = new JButton("Increment");
		final JButton btnVer = new JButton("Reset");
		frameMain.setSize(500, 200);
		frameMain.setLayout(new FlowLayout());
		frameMain.add(view);
		frameMain.add(btnInc);
		frameMain.add(btnVer);
		btnInc.addActionListener(event -> stand.increment());
		btnVer.addActionListener(event -> stand.reset());
		frameMain.setVisible(true);

		final Observer obs = new Observer() {
			@Override
			public void update(Observable o, Object arg) {
				assert (o instanceof Zahl);
				assert (arg instanceof Boolean);
				final Zahl z = (Zahl) o;
				System.out.println("Zahl: " + z.get() + " \t isResetted: " + arg);
			}
		};
		stand.addObserver(obs);
		stand.addObserver(obs); // will not be added, because already present
		stand.addObserver((o, a) -> System.out.println("Zahl---" + ((Zahl) o).get()));
	}

}
