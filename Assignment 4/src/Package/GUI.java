package Package;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GUI extends JFrame
{

    private static final long serialVersionUID = 1L;
    private JButton Compress;
    private JButton Decompress;
    private JTextField jt;

    public GUI()
    {
        super("Non-Uniform Quantizer");
        setLayout(new FlowLayout());

        jt = new JTextField("Enter Number of Levels");
        add(jt);

        Compress = new JButton("Compress");
        add(Compress);

        Decompress = new JButton("Decompress");
        add(Decompress);

        EventHandler handler = new EventHandler();
        Compress.addActionListener(handler);
        Decompress.addActionListener(handler);
    }

    private class EventHandler implements ActionListener
    {

        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource()==Compress)
            {
                String input = jt.getText();
                int levels = Integer.parseInt(input);
                Compress comp = new Compress(levels);
                try
                {
                    comp.compress();
                }
                catch (IOException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,String.format("Done!",event.getActionCommand()));
            }
            else if(event.getSource()==Decompress)
            {
                Decompress decomp = new Decompress();
                try
                {
                    decomp.decompress();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                JOptionPane.showMessageDialog(null,String.format("Done!",event.getActionCommand()));
            }
        }
    }


}


