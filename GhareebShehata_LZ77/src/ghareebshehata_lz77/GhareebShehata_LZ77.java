package ghareebshehata_lz77;

import java.util.Scanner;


/**
 *
 * @author Ghareeb
 */
public class GhareebShehata_LZ77 {
        String stringLine;
        int pointer, length, index;
        char nextChar;
    
    GhareebShehata_LZ77() {
        stringLine = "";
        pointer = 0;
        length = 0;
        nextChar = ' ';

    }
        void SetStringLine(String stringLine) {
        this.stringLine = stringLine;
    }
        void Decompress(){
             stringLine = "";
            int n;
            Scanner in = new Scanner(System.in);
            System.out.println("Enter number of tags that you want to decompress");
            n = in.nextInt();

        for (int i = 0; i < n; i++) {

            System.out.println("enter tag " + (i + 1));

            pointer = in.nextInt();
            length = in.nextInt();
            nextChar = in.next().charAt(0);

            if (length == 0) {
                stringLine += nextChar;
            } else {
                stringLine = stringLine + stringLine.substring(stringLine.length() - pointer, stringLine.length() - pointer + length);
                stringLine = stringLine + nextChar;
            }

        }

        System.out.println("The String line is : " + stringLine);

    }


    public static void main(String[] args) {
        GhareebShehata_LZ77 newString = new GhareebShehata_LZ77();
        newString.Decompress();

       
    }
}
