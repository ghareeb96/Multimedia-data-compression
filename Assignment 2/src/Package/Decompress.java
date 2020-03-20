package Package;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class Decompress {
	
	private Scanner input ;
	private FileWriter outfile;
	private PrintWriter printwriter;
	private String out = new String();
	private Vector<String> dictionary;
	JFileChooser filechooser = new JFileChooser();
	
	public Decompress()
	{
		dictionary = new Vector<String>();
		char c;
		for(int i=0;i<127;i++)
		{
			c = (char)i;
			dictionary.addElement(Character.toString(c));
		}
	}
	
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
			int n;
			String CurrentStep = new String("");
			String PreviousStep = new String("");
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
				n = input.nextInt();
				if(n<dictionary.size())
				{
					out+=dictionary.get(n);
					PreviousStep = String.valueOf(CurrentStep);
					CurrentStep = dictionary.get(n);
					if(PreviousStep!="")
					{
						dictionary.add(PreviousStep+CurrentStep.charAt(0));
					}
				}
				else
				{
					PreviousStep = String.valueOf(CurrentStep);
					CurrentStep = PreviousStep + PreviousStep.charAt(0);
					dictionary.add(CurrentStep);
					out+=CurrentStep;
				}
			}
			input.close();
		}
		WriteFile();
	}


}
