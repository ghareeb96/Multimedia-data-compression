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
        
        //end of Decompression
        SavePhoto();

    }


}


