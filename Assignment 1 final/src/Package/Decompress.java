package Package;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class Decompress {
	
	private Scanner input ;
	private FileWriter outfile;
	private PrintWriter printwriter;
	private String out = new String();
	private String temp = new String();
	private Vector<String> dictionary = new Vector<String>();
	JFileChooser filechooser = new JFileChooser();
	
	private void CreateFile() throws FileNotFoundException
	{
		try 
		{
			outfile = new FileWriter("C:\\Users\\omar wael\\Desktop\\decompressed.txt");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	private void WriteFile()
	{
		try 
		{
			CreateFile();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		printwriter = new PrintWriter(outfile);
		printwriter.print(out);
		printwriter.close();
	}
	
	public void decompress() throws Exception
	{
		if(filechooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			java.io.File file = filechooser.getSelectedFile();
			int position;
			String next;
			try 
			{
				input = new Scanner(file);
			}
			catch(Exception e)
			{
				System.out.println("Error while reading!");
			}
			
			while(input.hasNext())
			{
				input.useDelimiter(" ");
				position = input.nextInt();
				next = input.next();
				if(position==0)
				{
					out += next;
					dictionary.add(next);
				}
				else
				{
					if(next != "0")
					{
						temp += dictionary.get(position-1);
						temp += next;
						out += temp;
						dictionary.add(temp);
						temp="";
					}
					else
					{
						out += dictionary.get(position-1); 
					}
				}
			}
			input.close();
		}
		WriteFile();
	}


}
