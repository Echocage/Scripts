package Planker;

import javax.swing.JFrame;

public class GUI {

	public static void main(String[] args) {

		JFrame frame = new JFrame("Hello GUI World!");
		frame.setSize(100, 50);
		frame.setLocationRelativeTo(null); // Centers the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
