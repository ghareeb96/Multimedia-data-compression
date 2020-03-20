package Package;

import javax.swing.JFrame;

public class Main {

    public static void main(String args[]) {
        GUI gui = new GUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(400, 100);
        gui.setVisible(true);
    }
}

