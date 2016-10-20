import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.List;
import java.util.Scanner;
/**
 * A game of Twenty Questions where the player identifies a particular Manga series (not anime or light novels).
 * Players are able to input what they were looking for if they can't find it, so they can find it in alater play-through.
 */
public class Game
{
    TreeNode Q1=null;
    /**
     * Runs the game
     */
    public Game()throws IOException
    {
        int u=0;
        while(u<1){
            u = JOptionPane.showConfirmDialog(null,"Would you like to play\n20 Questions: Manga ?");//0=yes,1=no,2=cancel
            if(u==0){
                Q1=null;
                build();
                play();
            }
        }
    }

    /**
     * Builds a tree from reading in a preorder file (QA_English.txt)
     */
    public void build()throws IOException{//doesn't work
        Scanner in = new Scanner(new File("QA_English.txt"));
        // PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("QA_English.txt")));
        TreeNode temp=null;
        String str="";
        Stack tr=new ArrayStack();
        while(in.hasNextLine()){
            str=in.nextLine();
            if(Q1==null){//creates root
                Q1=new TreeNode(str,null,null);
                tr.push(Q1);
                temp=Q1;
            }else if(str.indexOf("A:")>=0){//checks for end case (answer)
                if(temp.getLeft()==null){
                    temp.setLeft(new TreeNode(str,null,null));
                }else if(temp.getRight()==null){
                    temp.setRight(new TreeNode(str,null,null));
                }
                while(temp.getLeft()!=null&&temp.getRight()!=null&&!tr.isEmpty()){
                    temp=(TreeNode)tr.pop();
                }
            }else{
                if(temp.getLeft()==null){
                    temp.setLeft(new TreeNode(str,null,null));
                    tr.push(temp);
                    temp=temp.getLeft();
                }else if(temp.getRight()==null){
                    temp.setRight(new TreeNode(str,null,null));
                    tr.push(temp);
                    temp=temp.getRight();
                }
            }
        }
        in.close();
    }

    /**
     * The method for a single playthrough  of the game
     *  -player can add a question and answer to the tree if they are unsatisfied with their results
     */
    public void play() throws IOException{
        TreeNode temp=Q1;
        TreeNode temp2=null;
        int savprev=2;
        while(true){
            int input = JOptionPane.showConfirmDialog(null,temp.getValue());//0=yes,1=no,2=cancel
            if(input==0&&temp.getLeft()!=null){
                temp2=temp;
                temp=temp.getLeft();
            }else if(input==1&&temp.getRight()!=null){
                temp2=temp;
                temp=temp.getRight();
            }else if(input==0){
                JOptionPane.showMessageDialog(null,"Thank you for playing!");
                break;
            }else if(input==1){//add to the tree
                String fix=JOptionPane.showInputDialog(temp.getValue()+" is not correct?\nWhat were you looking for?");
                if(fix!=null){
                    if(fix.indexOf("A:")<0){
                        fix="A:"+fix;
                    }
                    String fix2=JOptionPane.showInputDialog("What question should lead you to "+ fix+"?");
                    if(fix2!=null){
                        if(fix2.indexOf("Q:")<0){
                            fix2="Q:"+fix2;
                        }
                        if(savprev==0){
                            temp2.setLeft(new TreeNode(fix2,new TreeNode(fix,null,null),temp));
                        }else{
                            temp2.setRight(new TreeNode(fix2,new TreeNode(fix,null,null),temp));
                        }
                        preOrderOut();
                    }
                }
                break; 
            }else{
                break;
            }
            savprev=input;
        }
    }

    /**
     * Writes out the new tree to the file (QA_English.txt) in preorder
     */
    public void preOrderOut()throws IOException{
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("QA_English.txt")));
        String str=preOrderOutHelper(Q1);
        Scanner in = new Scanner(str);
        while(in.hasNextLine()){
            out.println(in.nextLine());
        }
        in.close();
        out.close();
    }

    public String preOrderOutHelper(TreeNode root){
        if(root==null){
            return "";
        }
        String s="";
        s+=preOrderOutHelper(root.getLeft())+preOrderOutHelper(root.getRight());  
        if(root==Q1){
            return root.getValue()+""+s;
        }
        return "\n"+root.getValue()+""+s;
    }

    public String preOrder() {
        // should be formatted the same way as described in toString() above
        return preOrderRecursiveHelper(Q1);
    }

    private String preOrderRecursiveHelper(TreeNode root){
        if(root==null){
            return "";
        }
        String s="";
        s+=preOrderRecursiveHelper(root.getLeft())+preOrderRecursiveHelper(root.getRight());  
        return root.getValue()+""+s;
    }
}
