package Package;

import javax.swing.JFrame;

public class main {
	
	public static void main(String args[]) {
		GUI gui = new GUI();
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.setSize(350, 80);
		gui.setVisible(true);
	}
}
