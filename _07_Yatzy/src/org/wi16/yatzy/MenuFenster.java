package org.wi16.yatzy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MenuFenster extends JFrame
{
	private final DefaultListModel<String> player;

	public MenuFenster()
	{
		this.setTitle("Yatzy");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);

		this.player = new DefaultListModel<String>();

		getContentPane().setLayout(new BorderLayout(10, 10));

		final JList<String> list = new JList<String>(player);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("Verdana", Font.PLAIN, 23));
		getContentPane().add(list, BorderLayout.CENTER);

		JPanel playerManagementPanel = new JPanel();
		playerManagementPanel.setPreferredSize(new Dimension(200, 500));
		getContentPane().add(playerManagementPanel, BorderLayout.EAST);
		playerManagementPanel.setLayout(new GridLayout(3, 1, 10, 10));

		JButton btnNeuerSpieler = new JButton("Neuer Spieler");
		btnNeuerSpieler.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String name = "Neuer Spieler";
				boolean exists = false;
				int id = 1;
				do
				{
					exists = false;
					for (Object s : MenuFenster.this.player.toArray())
					{
						if (s.equals(name))
						{
							exists = true;
						}
					}
					if (exists)
					{
						id++;
						name = "Neuer Spieler " + id;
					}
				} while (exists);
				name = JOptionPane.showInputDialog(MenuFenster.this, "Wie hei�t der Spieler?", name);
				if (name != null)
				{
					player.addElement(name);
				}
			}
		});
		playerManagementPanel.add(btnNeuerSpieler);

		JButton btnSpielerEntfernen = new JButton("Spieler entfernen");
		btnSpielerEntfernen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				player.removeElementAt(list.getSelectedIndex());
			}
		});
		playerManagementPanel.add(btnSpielerEntfernen);

		JLabel lblYatzyByBabautzenwi = new JLabel("Yatzy by BABautzen_WI16");
		lblYatzyByBabautzenwi.setForeground(Color.BLUE);
		lblYatzyByBabautzenwi.setHorizontalAlignment(SwingConstants.CENTER);
		lblYatzyByBabautzenwi.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblYatzyByBabautzenwi.setFont(new Font("Verdana", Font.BOLD, 33));
		getContentPane().add(lblYatzyByBabautzenwi, BorderLayout.NORTH);

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setPreferredSize(new Dimension(500, 100));
		getContentPane().add(panel, BorderLayout.SOUTH);

		JButton btnSpielBeginnen = new JButton("Spiel beginnen");
		btnSpielBeginnen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				final Spieler[] spieler = new Spieler[player.size()];
				if (spieler.length == 0)
				{
					JOptionPane.showMessageDialog(MenuFenster.this, "Bitte wenigstens einen Spieler angeben");
					return;
				}
				for (int i = 0; i < spieler.length; i++)
				{
					spieler[i] = new Spieler(player.getElementAt(i));
				}
				final Spiel sb = new Spiel(spieler);
				final SpielFenster f = new SpielFenster(sb);
				f.setVisible(true);
				sb.nächsterSpieler(null, false);
				dispose();
			}
		});
		btnSpielBeginnen.setPreferredSize(new Dimension(300, 90));
		panel.add(btnSpielBeginnen);
	}
}
