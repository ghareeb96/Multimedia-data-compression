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
    private JTextField Levels;
    private JTextField Height;
    private JTextField Width;

    public GUI()
    {
        super("Non-Uniform Quantizer");
        setLayout(new FlowLayout());

        Height = new JTextField("Enter the Height of the block");
        add(Height);
        
        Width = new JTextField("Enter the Width of the block");
        add(Width);

        Levels = new JTextField("Enter Number of Levels");
        add(Levels);
        
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
                String input = Levels.getText();
                int levels = Integer.parseInt(input);
                input = Height.getText();
                int height = Integer.parseInt(input);
                input = Width.getText();
                int width = Integer.parseInt(input);
                Compress comp = new Compress(levels,height,width);
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


