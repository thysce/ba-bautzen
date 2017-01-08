package org.wi16.yatzy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AuswertungFenster extends JFrame
{
	private JTable table;
	private JButton btnOk;

	public AuswertungFenster(final Spiel spiel)
	{
		this.setTitle("Yatzy");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable(15, spiel.getSpieler().size() + 1);
		panel_1.add(table, BorderLayout.NORTH);
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		Spieltabelle.fülleTabellenÜberschrift(table, 1);

		JLabel lblGewonnenHat = new JLabel(spiel.berechneGewinner().getName() + " hat gewonnen!!!");
		lblGewonnenHat.setHorizontalAlignment(SwingConstants.CENTER);
		lblGewonnenHat.setForeground(Color.BLUE);
		lblGewonnenHat.setFont(new Font("Verdana", Font.ITALIC, 28));
		panel_1.add(lblGewonnenHat, BorderLayout.CENTER);
		table.setValueAt("Ergebnis", 14, 0);

		JLabel lblYatzySpielauswertung = new JLabel("Yatzy - Spielauswertung");
		lblYatzySpielauswertung.setForeground(Color.BLUE);
		lblYatzySpielauswertung.setHorizontalAlignment(SwingConstants.CENTER);
		lblYatzySpielauswertung.setFont(new Font("Verdana", Font.BOLD, 37));
		getContentPane().add(lblYatzySpielauswertung, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(500, 70));
		getContentPane().add(panel, BorderLayout.SOUTH);

		btnOk = new JButton("OK");
		btnOk.setPreferredSize(new Dimension(100, 60));
		btnOk.setFont(new Font("Verdana", Font.BOLD, 17));
		btnOk.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				final MenuFenster mf = new MenuFenster();
				mf.setVisible(true);
				dispose();
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 5));
		panel.add(btnOk);

		{// Fülle Tabelle mit Spielerdaten

			int idx = 0;
			for (Spieler s : spiel.getSpieler())
			{
				if (s != null)
					table.setValueAt(s.getName() + "", 0, idx + 1);
				Spieltabelle.zeigeSpielerdatenAn(table, s, 1, idx);
				table.setValueAt(s.getScore(), 14, idx + 1);
				idx++;
			}
		}
	}

}
