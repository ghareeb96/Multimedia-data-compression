package Package;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class Compress {
	
	private Scanner input ;
	private FileWriter outfile;
	private PrintWriter printwriter;
	private String in = new String();
	private HashMap<String, Integer> dictionary ;
	JFileChooser filechooser = new JFileChooser();
	
	public Compress()
	{
		dictionary = new HashMap<>();
		char c;
		String s="";
		for(int i=0;i<127;i++)
		{
			c = (char)i;
			s += c;
			dictionary.put(s,i);
			s = "";
		}
	}
	
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
	
	
	private void WriteFile(int n)
	{
		printwriter = new PrintWriter(outfile);
		printwriter.printf("%d ",n);
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
		String t = new String("");
		String s = new String("");
		int n;
		t=Character.toString(in.charAt(0));
		for(int i=1;i<in.length();i++)
		{
			n=dictionary.get(t);
			s=Character.toString(in.charAt(i));
			if(dictionary.get(t+s)==null)
			{
				WriteFile(n);
				dictionary.put(t+s, dictionary.size()+1);
				t=s;
			}
			else
			{
				t+=s;
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

