package package3;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class Decompress {
	
	private Scanner input ;
	private FileWriter outfile;
	private PrintWriter printwriter;
	private String out = new String();
	private String in = new String();
	private HashMap<String,String> table = new HashMap<>();
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
			String character;
			String code;
			try 
			{
				input = new Scanner(file);
			}
			catch(Exception e)
			{
				System.out.println("Error while reading!");
			}
			if(input.hasNext())
				in=input.next();
			while(input.hasNext())
			{
				input.useDelimiter(" ");
				character = input.next();
				code = input.next();
				table.put(code, character);
			}
			String temp = new String("");
			for(int i=0;i<in.length();i++)
			{
				if(table.get(temp+in.charAt(i))!=null)
				{
					out+=table.get(temp+in.charAt(i));
					temp="";
				}
				else
				{
					temp+=in.charAt(i);
				}
			}
		input.close();
		WriteFile();
		}
	}


}

