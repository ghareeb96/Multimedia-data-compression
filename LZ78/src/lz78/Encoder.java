package lz78;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

/**
 *
 * @author Ghareeb
 */
public class Encoder {
    public static void main(String[] args){
        ArrayList<String> dictionary = new ArrayList<String>();
        String[] input = JOptionPane.showInputDialog("Enter Message to encode").split("");
        HashMap<Integer, String> codeWord = new HashMap<Integer, String>();
        int index = 0;
        String current ="";
        
        int key = 1;
        dictionary.add(null);
        while(index < input.length-1){
            current = input[index];
            if(dictionary.contains(current)){
                String previous="";
                current = add(current, dictionary, input, index);
                int j = index;
                for(int i=0; i<current.length()-1;i++){
                    previous += input[j];
                    j+=1;
                }
                codeWord.put(key++, (dictionary.indexOf(previous)+":"+append(current)));
                index = index+current.length();
                dictionary.add(current);
            }
            else {
                codeWord.put(key++, "0:"+current);
                dictionary.add(current);
                index++;
            }
            System.out.println(codeWord.entrySet()+" "+dictionary);
        }
        JOptionPane.showMessageDialog(null,"The Encoded Tags is \n" +  codeWord);
        JOptionPane.showMessageDialog(null,"The Original String size = " +input.length * 8);
        JOptionPane.showMessageDialog(null,"The Compressed Tags size = " + (--key) * 12);

        
    }
    
    public static String add(String current, ArrayList<String> dictionary, String[] in, int index){
        for(int i=index; i<in.length;i++){
            if(dictionary.contains(current)){
                current = current+in[i+1];
            }
        }
        return current;
    }
    
    public static String append(String current){
         String [] name = current.split("");
         return name[name.length - 1];
    }
}
