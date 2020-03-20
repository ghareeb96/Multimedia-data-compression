package huffmanpackage;

import java.util.*;

public class huffman {
	
	
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
	private static String text = new String("");
	private Vector<Node> nodes = new Vector<Node>();
	private HashMap<String,String>table = new HashMap<>();
	public static void main(String args[])
	{
		huffman huff = new huffman();
		Scanner scanner = new Scanner(System.in);
		int choice;
		while(true)
		{
			System.out.println("1-Compress\n2-Decompress\n3-Exit\n");
			choice=scanner.nextInt();
			if(choice==1)
			{
				scanner.nextLine();
				text = scanner.nextLine();
				huff.Compress();
			}
			else if(choice==2)
			{
				
			}
			else if(choice ==3)
			{
				break;
			}
			else 
			{
				System.out.println("Invalide choice\n");
			}
		}
	}
	
	public void Compress()
	{
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
		WriteCode();
		
	}
	
	public void Decompress()
	{
		
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
	
	public void WriteCode()
	{
		Set<String> set = table.keySet();
        for(String i:set)
        {
        	System.out.print(i + " ");
        	System.out.print(table.get(i) + " ");
        }
        System.out.println();
		String out = new String("");
		for(int i=0;i<text.length();i++)
		{
			out+=table.get(text.charAt(i)+"");
		}
		System.out.println(out+"\n");
	}
	
	public void ReadCode()
	{
		
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
}
