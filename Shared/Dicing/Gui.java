package Shared.Dicing;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;

public class Gui extends JInternalFrame {
	
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui frame = new Gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Gui() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.X_AXIS));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel_1);

		JCheckBox checkBox = new JCheckBox("Logs");
		buttonGroup.add(checkBox);

		JCheckBox checkBox_1 = new JCheckBox("Oak Logs");
		buttonGroup.add(checkBox_1);

		JCheckBox checkBox_2 = new JCheckBox("Teak Logs");
		buttonGroup.add(checkBox_2);

		JCheckBox checkBox_3 = new JCheckBox("Mahogany Logs");
		buttonGroup.add(checkBox_3);

		JButton button = new JButton("Start");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1
				.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 434, Short.MAX_VALUE)
				.addGroup(
						gl_panel_1
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										gl_panel_1
												.createParallelGroup(
														Alignment.LEADING)
												.addComponent(checkBox)
												.addComponent(checkBox_1)
												.addComponent(checkBox_2)
												.addComponent(checkBox_3))
								.addContainerGap(317, Short.MAX_VALUE))
				.addGroup(
						gl_panel_1.createSequentialGroup()
								.addContainerGap(357, Short.MAX_VALUE)
								.addComponent(button).addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1
				.createParallelGroup(Alignment.LEADING)
				.addGap(0, 262, Short.MAX_VALUE)
				.addGroup(
						gl_panel_1
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(checkBox)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(checkBox_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(checkBox_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(checkBox_3)
								.addPreferredGap(ComponentPlacement.RELATED,
										119, Short.MAX_VALUE)
								.addComponent(button).addContainerGap()));
		panel_1.setLayout(gl_panel_1);

	}

}
