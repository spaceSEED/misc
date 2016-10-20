import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.io.FileInputStream;
import javax.swing.JOptionPane;

public class Run
{

    public Run(){
        JOptionPane jp=new JOptionPane();
        String a= jp.showInputDialog("Would you like to compress or decompress a .txt file?");
        if(a!=null){
            a=a.toLowerCase();
            if(a.equals("compress")||a.equals("comp")||a.equals("c")||a.equals("compres")||a.equals("com")||a.equals("1")){
                try{
                    Encode en = new Encode();
                }catch(IOException i){

                }
            }else if(a.equals("decompress")||a.equals("decompres")||a.equals("decomp")||a.equals("2")||a.equals("decom")||a.equals("d")||a.equals("de")){
                try{
                    Decode de = new Decode();
                }catch(IOException i){

                }
            }else{

            }
        }

    }
}
