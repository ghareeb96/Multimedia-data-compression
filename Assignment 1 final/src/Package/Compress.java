package Package;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class Compress {
	
	private Scanner input ;
	private FileWriter outfile;
	private PrintWriter printwriter;
	private String in = new String();
	private String temp = new String();
	private HashMap<String, Integer> dictionary = new HashMap<>();
	JFileChooser filechooser = new JFileChooser();
	
	private void ReadFile() throws Exception
	{
		if(filechooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			java.io.File file = filechooser.getSelectedFile();
			try
			{
				input = new Scanner(file);
			}
			catch(Exception e )
			{
				System.out.println("Error in Opening the file!");
			}
			
			while(input.hasNextLine())
			{
				in += input.nextLine();
				in += "\n";
			}
			
			input.close();
		}
		else
		{
			System.out.println("no selected file!");
		}
	}
	
	private void CreateFile() throws FileNotFoundException
	{
		try
		{
			outfile = new FileWriter("C:\\Users\\omar wael\\Desktop\\compressed.txt");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	private void WriteFile(int n , char s)
	{
		printwriter = new PrintWriter(outfile);
		printwriter.printf("%d %s ",n,s);
	}
	
	public void compress()
	{
		try 
		{
			ReadFile();
		} 
		catch 
		(Exception e2)
		{
			e2.printStackTrace();
			System.out.println("Error while reading!");
		}
		try 
		{
			CreateFile();
		} 
		catch (FileNotFoundException e1) 
		{
			e1.printStackTrace();
			System.out.println("file already exists!");
		}
		int dum = 0;
		for(int i=0;i<in.length()-1;i++)
		{
			temp += in.charAt(i);
			if(dictionary.get(temp)==null)
			{
				dictionary.put(temp, dictionary.size()+1);
				WriteFile(dum,in.charAt(i));
//				System.out.println(dum + " " + in.charAt(i));
				temp="";
				dum = 0;
			}
			else 
			{
				dum=dictionary.get(temp);
				if(i==in.length()-2)
				{
					WriteFile(dum,'0');
//					System.out.println(dum + " " + '0');
				}
			}
		}
		try 
		{
			outfile.close();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("Error closing the file!");
		}
	}

}

