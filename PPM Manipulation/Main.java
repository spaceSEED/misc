import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.List;
import java.util.Scanner;
public class Main
{
    
    public static void main(String[] args) throws IOException
    {
        JOptionPane j =new JOptionPane();
        String input = j.showInputDialog("What would you like to do?:\n(1)Negative (2)Flatten (3)Flip\n(4)Grey Scale (5)? (6)Average\n(7)Checker (8)Noise (9)Extreme Contrast\n(10)Make Random.ppm (11)Make 90's");
        int in=Integer.parseInt(input);
        if(in==10){
            input=j.showInputDialog("Pick a width:");
            int x=Integer.parseInt(input);
            input=j.showInputDialog("Pick a height:");
            int y=Integer.parseInt(input);
            input=j.showInputDialog("Pick a Maximum Color:");
            int maxColor=Integer.parseInt(input);
          Edit a= new Edit( x,  y,  maxColor);
        }else{
            Edit a=new Edit();
            if(in==1){
                a.neg();
            }else if(in==2){
                a.flat();
            }else if(in==3){
                a.flip();
            }else if(in==4){
                a.grey();
            }else if(in==5){
                a.strange();
            }else if(in==6){
                a.average();
            }else if(in==7){
                a.checkers();
            }else if(in==8){
                a.noise();
            }else if(in==9){
                a.cont();
            }else if(in==11){
                a.cool();
            }
            
            
        }
    }
}
