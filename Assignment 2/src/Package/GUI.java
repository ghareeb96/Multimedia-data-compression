package Package;


import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame 
{
	
	private JButton Compress;
	private JButton Decompress;
	
	public GUI() 
	{
		super("LZ78rar");
		setLayout(new FlowLayout());
		
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
				Compress comp = new Compress();
				comp.compress();
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
