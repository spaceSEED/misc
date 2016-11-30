import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Calculator
{
    ArrayStackStr in = new ArrayStackStr();//stack used
    ArrayStackStr other=new ArrayStackStr();//I don't know why I have this here
    String sign = "+-/%*x^";//operation signs
    String not = "abcdefghijklmnopqrstuvwyz!@#$&()_={[}]|'\"\\:;<,>.?`~";//ignore if inputted
    String input="";
    int out=0;//I don't know why this is here either

    /**
     *Uses JOptionPane to ask for inputs, display outputs, and let the user "try again"
     */
    public Calculator()
    {
        while(true){
            input = JOptionPane.showInputDialog("Input fomrula in Reverse Polish Notation:\n[(1+2)*3==12+3* and 1+2*3==123*+]\n(You can input +-*/%^ operations)");
            if(input==null){
                break;
            }
            input();
            int q= JOptionPane.showConfirmDialog(null, in.pop()+"\n"+"Try Again?");
            if(q!=0){
                break;
            }
        }
    }
    
    public static void main(String[] args){
        Calculator calc =new Calculator();
    }

    /**
     * Takes inputs, puts them into the stack, and identifies if they are a number or operation
     */
    public void input(){
        for(int a=0; a<input.length();a++){
            if(signOrInt(input.substring(a,a+1))){//checks then calculates for operation
                in.push(calc(input.substring(a,a+1)));
            }
            else if(not.indexOf(input.substring(a,a+1))>-1){//ignores superfluous info
                ;
            }
            else{//pushes ints to stack
                in.push(input.substring(a,a+1));
            }
            if(in.peekTop().equals("fail")){//exits if the input is invalid
                break;
            }
        }
    }

    /**
     * calculates the top two ints in the stack based upon the given operation
     */
    public String calc(String a){
        if(moreTwo(in)){
            if(a.equals("+")){//addition
                return ""+( toInt(in.pop())+toInt(in.pop()));
            }
            else if(a.equals("-")){//subtraction
                return ""+( (-toInt(in.pop()))+toInt(in.pop()) );
            }else if(a.equals("%")){//mods
                int b=toInt(in.pop());
                return ""+ ( toInt(in.pop())%b );
            }
            else if(a.equals("^")){//exponents
                int n=0;
                int q=toInt(in.pop());
                int e =toInt(in.pop());
                if(q==0){
                   n=1; 
                }else if (q==1){
                    n=e;
                }
                for(int b=1;b<q;b++){
                   n+= e*e;
                }
                return ""+ n;
            }
            else if(a.equals("/")){//division
                int b=toInt(in.pop());
                return ""+ ( toInt(in.pop())/b );
            }
            else if(a.equals("*")||a.equals("x")){//multiplication
                return ""+ ( toInt(in.pop())*toInt(in.pop()) );
            }
        }
        return "fail";
    }

    /**
     * checks if operation sign
     */
    public boolean signOrInt(String a){
        if(sign.indexOf(a)>-1){
            return true;
        }
        return false;
    }

    /**
     * Integer.parseInt(); simplified
     */
    public int toInt(String a){
        return Integer.parseInt(a);
    }
    
    /**
     * makes sure there are at least 2 ints in the stack
     */
    public boolean moreTwo(ArrayStackStr a){
        if(!a.isEmpty()){
            String qw=a.pop();
            if(!a.isEmpty()){
                a.push(qw);
                return true;
            }
        }
        return false;
    }
}
