package org.wi16.yatzy;

import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

@SuppressWarnings("serial")
public class SpielFenster extends JFrame implements NächsterSpielerListener
{

	private final Spieltabelle st;
	private final Spiel spiel;
	private final JButton wurf;
	private final JLabel spieler, score;

	public SpielFenster(final Spiel sb)
	{
		this.spiel = sb;
		this.spiel.setNächsterSpielerListener(this);
		this.setTitle("Yatzy");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);

		final Container c = this.getContentPane();
		{
			final JPanel p = new JPanel();
			p.setLayout(new GridLayout(1, Becher.MAX_COUNT));
			for (JToggleButton btn : sb.getWürfelButtons())
			{
				p.add(btn);
			}
			p.setBounds(10, 0, getWidth() - 20, getHeight() / 3);
			c.add(p);
		}

		spieler = new JLabel("Spieler: ");
		spieler.setFont(new Font("Arial", Font.PLAIN, 50));
		spieler.setBounds(10, getHeight() / 3 + getHeight() / 5 * 2, getWidth() - 20, getHeight() / 5);
		c.add(spieler);

		st = new Spieltabelle();
		st.setBounds(10, getHeight() / 3 + 10, getWidth() / 2 - 20, getHeight() / 5 * 2 + 10);
		c.add(st);

		score = new JLabel("Score: ");
		score.setFont(new Font("Arial", Font.PLAIN, 60));
		score.setBounds(getWidth() / 2, getHeight() / 3 + getHeight() / 5 + 20, getWidth() / 2 - 10, getHeight() / 5);
		c.add(score);

		wurf = new JButton("Würfeln");
		wurf.setFont(new Font("Arial", Font.BOLD, 50));
		wurf.setBounds(getWidth() / 2, getHeight() / 3 + 10, getWidth() / 2 - 10, getHeight() / 5);
		c.add(wurf);
		wurf.addActionListener(event -> {
			final boolean nächsterSpieler = sb.wurf();
			if (nächsterSpieler)
			{
				final String[] möglicheErgs = sb.berechneMöglicheErgebnisse();
				final String input;
				final boolean spielVorbei;
				if (möglicheErgs != null && möglicheErgs.length > 0)
				{
					input = (String) JOptionPane.showInputDialog(this, "Bitte Ergebnis wählen", "Spielzug vorbei",
							JOptionPane.QUESTION_MESSAGE, null, möglicheErgs, möglicheErgs[0]);

					spielVorbei = sb.nächsterSpieler(input, false);
				} else
				{
					final String[] ergs = sb.berechneStreichung();
					input = (String) JOptionPane.showInputDialog(this,
							"Leider einmal nichts! Bitte ein Ergebnis streichen", "Spielzug vorbei",
							JOptionPane.QUESTION_MESSAGE, null, ergs, ergs[0]);
					spielVorbei = sb.nächsterSpieler(input, true);
				}
				if (spielVorbei)
				{
					final AuswertungFenster aF = new AuswertungFenster(spiel);
					aF.setVisible(true);
					setVisible(false);
					dispose();
				}
			}
		});
	}

	@Override
	public void rundeVorbei(final Spieler nächster)
	{
		spieler.setText("Spieler: " + nächster.getName());
		st.zeigeSpielerdatenAn(nächster);
		score.setText("Score: " + nächster.getScore());
	}
}
