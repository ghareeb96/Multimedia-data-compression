package Package;

import java.io.*;
import java.util.*;
import javax.swing.JFileChooser;

public class Decompress {

    Scanner inputfile;
    private static int Height;
    private static int Width;
    private static int matrix[][];
    private static ReadWriteImage read;
    private static ArrayList<Integer> input = new ArrayList<>();
    private static ArrayList<Integer> Averages = new ArrayList<>();
    JFileChooser filechooser = new JFileChooser();

    private void ReadFile()
    {
        if(filechooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            java.io.File file = filechooser.getSelectedFile();
            try
            {
                inputfile = new Scanner(file);
            }
            catch(Exception e)
            {
                System.out.println("Error while reading!");
            }

            //reading 
            String dummy;
            while(inputfile.hasNextInt())
            {
            	inputfile.useDelimiter(" ");
            	input.add(inputfile.nextInt());
            }
            dummy = inputfile.next();
            Height = inputfile.nextInt();
            inputfile.useDelimiter(" ");
            Width = inputfile.nextInt();
            dummy = inputfile.next();
            while(inputfile.hasNextInt())
            {
            	inputfile.useDelimiter(" ");
            	Averages.add(inputfile.nextInt());
            }
            //end of reading            
        }

        inputfile.close();
    }
    private void SavePhoto() throws IOException
    {
        read = new ReadWriteImage();
        read.writeImage(matrix, "C:\\Users\\omar wael\\Desktop\\Decompress.jpg");
    }
    public void decompress() throws IOException
    {
        ReadFile();
        //Decompression
        matrix = new int[Height][Width];
        for(int i=0;i<Height;i++)
        {
        	for(int j=0;j<Width;j++)
        	{
        		for(int k=0;k<Averages.size();k++)
        		{
        			if(input.get(j+(i*Height))==k)
        			{
        				matrix[i][j]=Averages.get(k);
        				break;
        			}
        		}
        	}
        }
        //end of Decompression
        SavePhoto();

    }


}


