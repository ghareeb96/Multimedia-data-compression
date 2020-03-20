package package3;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;

public class Compress {
	
	class Node
	{
		String name;
		int freq;
		String code;
		Node right;
		Node left;
		public Node()
		{
			right=left=null;
			code="";
			freq=0;
		}
	}
	private Scanner input ;
	private FileWriter outfile;
	private PrintWriter printwriter;
//	private String in = new String();
	JFileChooser filechooser = new JFileChooser();
	private static String text = new String("");
	private Vector<Node> nodes = new Vector<Node>();
	private HashMap<String,String>table = new HashMap<>();
	
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
				text += input.nextLine();
//				text += "\n";
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
	
	
	private void WriteFile()
	{
		printwriter = new PrintWriter(outfile);
//		System.out.println(table);
		String out = new String("");
		for(int i=0;i<text.length();i++)
		{
			out+=table.get(text.charAt(i)+"");
		}
		printwriter.printf("%s ",out);
//		System.out.println(out+"\n");
		Set<String> set = table.keySet();
        for(String i:set)
        {
        	printwriter.printf("%s %s ", i , table.get(i));
//        	System.out.print(i + " ");
//        	System.out.print(table.get(i) + " ");
        }
	}
	
	public void AssignCode(Node n , String s)
	{
		if(n==null)
			return;
		n.code+=s;
		if(n.right==null&&n.left==null)
		{
			table.put(n.name, n.code);
		}
		AssignCode(n.left,s+"0");
		AssignCode(n.right,s+"1");
		
	}
	
	public void getfreq()
	{
		int freqarr[] = new int[128];
		for(int i=0;i<text.length();i++)
		{
			freqarr[text.charAt(i)]++;
		}
		for(int i=0;i<freqarr.length;i++)
		{
			if(freqarr[i] != 0)
			{
				Node temp = new Node();
				temp.name = (char) i+"";
				temp.freq= freqarr[i];
				nodes.addElement(temp);
			}
		}
	}
	
	public void Sort()
	{
		for (int i = 0; i < nodes.size() - 1; i++)
			for (int j = nodes.size() - 1; j > i; j--)
				if (nodes.elementAt(j).freq > nodes.elementAt(j - 1).freq)
				{
					Node tmp = nodes.elementAt(j);
					nodes.setElementAt(nodes.elementAt(j - 1), j);
					nodes.setElementAt(tmp, j - 1);
				}
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
		getfreq();
		while(nodes.size()>1)
		{
			Node temp = new Node();
			temp.left = nodes.elementAt(nodes.size()-1);
			temp.right = nodes.elementAt(nodes.size()-2);
			temp.freq = nodes.elementAt(nodes.size()-1).freq + nodes.elementAt(nodes.size()-2).freq;
			temp.name = nodes.elementAt(nodes.size()-1).name + nodes.elementAt(nodes.size()-2).name;
			nodes.remove(nodes.size()-1);
			nodes.remove(nodes.size()-1);
			nodes.addElement(temp);
			Sort();
		}
		AssignCode(nodes.elementAt(0), "");
		WriteFile();
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

