package Shared.Planking;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.powerbot.game.api.util.Time;

public class iTPlankerGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox plankSelection;
	public static int plankSet = Planker.plankSwitch;

	public iTPlankerGUI() {
		super("iTPlanker");
		initComponents();
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(getParent());
	}

	private void initComponents() {

		final JLabel label = new JLabel("Select log type:");
		final JButton startButton = new JButton("Start");

		plankSelection = new JComboBox(
				new String[] { "Normal logs -> Normal planks",
						"Oak logs -> Oak planks", "Teak logs -> Teak planks",
						"Mahogany logs -> Mahogany planks" });

		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		final GroupLayout layout = new GroupLayout(getContentPane());
		layout.setHorizontalGroup(layout
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												Alignment.LEADING, false)
												.addComponent(label)
												.addComponent(
														plankSelection,
														GroupLayout.PREFERRED_SIZE,
														173,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(
														startButton,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(label)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(plankSelection,
										GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(37)
								.addComponent(startButton)
								.addContainerGap(GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		getContentPane().setLayout(layout);

		pack();
	}

	private void plankSwitchWait() {

		int logID = Planker.logID;
		int plankID = Planker.plankID;
		int plankPrice = Planker.plankPrice;
		switch (plankSet) {
		case 0:
			while (plankSet == 0) {
				Time.sleep(100);
			}
		case 1:
			logID = 1511;
			plankID = 960;
			plankPrice = 100;
			break;
		case 2:
			logID = 1521;
			plankID = 8778;
			plankPrice = 250;
			break;
		case 3:
			logID = 6333;
			plankID = 8780;
			plankPrice = 500;
			break;
		case 4:
			logID = 6332;
			plankID = 8782;
			plankPrice = 1500;
			break;
		}
		Planker.logID = logID;
		Planker.plankID = plankID;
		Planker.plankPrice = plankPrice;
		Planker.plankSwitch = plankSet;
	}

	private void jButton1ActionPerformed(final ActionEvent evt) {
		Planker bob = new Planker();
		setLogForName(plankSelection.getSelectedItem().toString());
		bob.guiDone = true;
		plankSwitchWait();
		dispose();
	}

	public static void setLogForName(final String str) {
		if (str.equalsIgnoreCase("Normal logs -> Normal planks")) {
			plankSet = 1;
		} else if (str.equalsIgnoreCase("Oak logs -> Oak planks")) {
			plankSet = 2;
		} else if (str.equalsIgnoreCase("Teak logs -> Teak planks")) {
			plankSet = 3;
		} else if (str.equalsIgnoreCase("Mahogany logs -> Mahogany planks")) {
			plankSet = 4;
		}
	}
}