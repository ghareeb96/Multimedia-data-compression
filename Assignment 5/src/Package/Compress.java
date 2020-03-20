package Package;

import java.io.*;
import java.util.*;
import javax.swing.*;


public class Compress {

    int Levels;
    private static int BlockHeight;
    private static int BlockWidth;
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
		int Average[][];
		ArrayList<int[][]> assotiates = new ArrayList<>();
		Node right;
		Node left;
		Node()
		{
			left=right=null;
			Average = new int[BlockHeight][BlockWidth];
		}
	}
    public Compress(int l,int h, int w)
    {
        BlockHeight=h;
        BlockWidth=w;
        Levels=(int)(Math.log(l) / Math.log(2));
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
       //Writing
        
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
			
	}
	
	private void GetAVG(Node root)
	{
		
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
        
        //end of compression
        WriteFile();
    }

}


