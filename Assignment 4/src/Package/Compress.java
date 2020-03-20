package Package;

import java.io.*;
import java.util.*;
import javax.swing.*;

import Package.Compress.Node;

public class Compress {

    int Levels;
    private static String path;
    private static Vector<Integer> Averages = new Vector<>();
    private static Vector<Float> Ranges = new Vector<>();
	private static FileWriter outfile;
    private static int matrix[][];
    private static int Width;
    private static int Height;
    private static ReadWriteImage readwrite = new ReadWriteImage();
    private static Node Root;
	public static  class Node
	{
		int Average;
		ArrayList<Integer> assotiates = new ArrayList<>();
		Node right;
		Node left;
		Node()
		{
			left=right=null;
			Average=0;
		}
	}
    public Compress(int n)
    {
        Levels = (int)(Math.log(n) / Math.log(2));
    }


    public static void LoadPhoto() throws IOException
    {
        JFileChooser chooseImage = new JFileChooser();
        chooseImage.showOpenDialog(null);
        File choosedImage = chooseImage.getSelectedFile();
        path = choosedImage.getAbsolutePath();
        matrix= readwrite.readImage(path);
        Height=matrix.length;
        Width=matrix[0].length;
    }

    private static void CreateFile() throws FileNotFoundException
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


    private static void WriteFile() throws IOException
    {
        try
        {
            CreateFile();
        }
        catch (FileNotFoundException e1)
        {
            e1.printStackTrace();
            System.out.println("file already exists!");
        }
        FileWriter outfile = new FileWriter("C:\\Users\\omar wael\\Desktop\\compressed.txt");
        PrintWriter printwriter = new PrintWriter(outfile);
        //test
        System.out.println(Ranges);
        System.out.println(Averages);
        //test end
        //writing
        for(int i=0;i<Height;i++)
        {
            for(int j=0;j<Width;j++)
            {
                for(int k=0;k<(Ranges.size()-1);k++)
                {
                    if(matrix[i][j]>=Ranges.get(k) && matrix[i][j]<Ranges.get(k+1))
                    {
                        printwriter.printf("%d ", k);
                    }
                }
            }
        }
        printwriter.printf("d ");
        printwriter.printf("%d ",Height);
        printwriter.printf("%d ", Width);
        printwriter.printf("d ");
        for(int i=0;i<Averages.size();i++)
        {
            printwriter.printf("%d ", Averages.get(i));
        }
        //end of writing
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


    private static void BuildTree(int Levels , Node node)
	{
		if(Levels==0) return;
		else
		{
			int l = node.Average - 1;
			int r = node.Average + 1;
			
			node.right = new Node();
			node.left = new Node();
			
			for(int i=0;i<node.assotiates.size();i++)
			{
				int dist1 = Math.abs(l - node.assotiates.get(i));
				int dist2 = Math.abs(r - node.assotiates.get(i));
				
				if(dist2>dist1)
				{
					node.left.assotiates.add(node.assotiates.get(i));
				}
				else
				{
					node.right.assotiates.add(node.assotiates.get(i));
				}
			}
			
			int leftsum = 0;
			for(int i=0;i<node.left.assotiates.size();i++)
			{
				leftsum+=node.left.assotiates.get(i);
			}
			node.left.Average=(leftsum/node.left.assotiates.size());
			
			int rightsum = 0;
			for(int i=0;i<node.right.assotiates.size();i++)
			{
				rightsum+=node.right.assotiates.get(i);
			}
			node.right.Average=(rightsum/node.right.assotiates.size());
			
			Levels--;
			BuildTree(Levels,node.left);
			BuildTree(Levels,node.right);
		}
	}
	
	private void GetAVG(Node root)
	{
		if(root.left == null && root.right == null)
		{
			Averages.add(root.Average);
			return;
		}
		GetAVG(root.left);
		GetAVG(root.right);
	}

    public void compress() throws IOException
    {
        try
        {
            LoadPhoto();
        }
        catch
        (Exception e2)
        {
            e2.printStackTrace();
            System.out.println("Error while reading!");
        }
        //Compression
        Root = new Node();
		for(int i=0;i<Height;i++)
		{
			for(int j=0;j<Width;j++)
			{
				Root.assotiates.add(matrix[i][j]);
			}
			
		}
		
		int sum=0;
		for(int i=0;i<Root.assotiates.size();i++)
		{
			sum+=Root.assotiates.get(i);
		}
		
		Root.Average=(sum/Root.assotiates.size());
		BuildTree(Levels,Root);
		GetAVG(Root);
        Ranges.add((float) 0);
        for(int i=0;i<Averages.size()-1;i++)
        {
            Ranges.add((float) ((Averages.get(i)+Averages.get(i+1))/2));
        }
        Ranges.add((float) 255);
        //end of compression
        WriteFile();
    }

}


