import java.util.*;
import java.util.Scanner;
import java.io.*;
import java.io.FileInputStream;
import javax.swing.JOptionPane;

public class Encode
{
    JOptionPane jp= new JOptionPane();
    private ArrayList<Object> tree;
    Map key= new TreeMap();
    TreeNode tr;
    byte[]mess;
    String fileName="";
    public Encode()throws FileNotFoundException, IOException
    {
        fileName= jp.showInputDialog("What file(.txt) would you like to compress?\n[input without '.txt' at the end]");
        Map<Character,Integer> a= frequency();
        makeTree(a);
        TreeNode tr= (TreeNode)tree.get(0);
        makeKey(tr);
        mess=makeMess(key);
        //  System.out.println(mess);

        Map kk=new TreeMap();
        Set s= key.keySet();
        Iterator it= s.iterator();
        while(it.hasNext()){
            Character c = (char)it.next();
            String qw=(String)key.get(c);
            kk.put(qw,c);
        }
        Set k=kk.keySet();
        it=k.iterator();
        FileOutputStream sc = new FileOutputStream(new File(fileName+".comp.txt"));
        String mapcode="";
        while(it.hasNext()){
            String qw=(String)it.next();
            Character c= (char)kk.get(qw);
            mapcode+=qw+"@"+c;
        }
        String gh="";
        for(int o=0;o<mapcode.length()-1;o++){
            while(!mapcode.substring(o,o+1).equals("@")){
                gh+=mapcode.substring(o,o+1);
                o++;
            }
            o++;
            sc.write((byte)gh.length());
            while(gh.length()<8){
                gh+="0";
            }
            byte p=0;
            int q=0;
            for(int i=8;i>0;i--){
                q+=Integer.parseInt(gh.substring(i-1,i))*Math.pow(2,8-i);
            }
            p=(byte)q;
            sc.write(p);
            while(gh.length()>8){
                p=0;
                q=0;
                gh=gh.substring(8,gh.length());
                while(gh.length()<8){
                    gh+="0";
                }
                for(int i=8;i>0;i--){
                    q+=Integer.parseInt(gh.substring(i-1,i))*Math.pow(2,8-i);
                }
                p=(byte)q;
                sc.write(p);
            }
            sc.write((byte)mapcode.charAt(o));
            gh="";
        }
        sc.write((byte)255);

        for(int o=0;o<mess.length;o++){
            sc.write(mess[o]);
        }
        sc.close();
        FileInputStream scd = new FileInputStream(new File(fileName+".txt"));
        int t= scd.available();
        scd = new FileInputStream(new File(fileName+".comp.txt"));
        int y=scd.available();
        jp.showMessageDialog(null, "File compressed successfully as "+fileName+".comp.txt\nOriginal: "+t+" bytes New: "+y+" bytes");
        //Decode de= new Decode();
    }

    public Map frequency()throws FileNotFoundException, IOException{
        Map<Character,Integer> a= new TreeMap<Character,Integer>();
        FileInputStream sc = new FileInputStream(new File(fileName+".txt"));
        while(sc.available()>0){
            Character b=(char)((byte)sc.read());
            if(a.keySet().contains((Object)b)){
                a.put(b,a.get(b)+1);
            }else{
                a.put(b,1);
            }
        }
        sc.close();
        return a;
    }

    public void makeTree(Map<Character,Integer> a){
        tree= new ArrayList<Object>(); 
        Set c=a.keySet();
        Iterator it= c.iterator();
        while(it.hasNext()){
            Character ki=(Character)it.next();
            add(new TreeNode(ki,a.get(ki),null,null));
        }
        while(tree.size()>1){
            TreeNode left= (TreeNode)remove();
            TreeNode right=(TreeNode)remove();
            int key=(Integer)left.getKey()+(Integer)right.getKey();
            add(new TreeNode(null, key,left,right));
        }
    }

    public void makeKey(TreeNode root){
        key= new TreeMap();
        numLeaves(root);
    }

    public int numLeaves(TreeNode root) {
        // recursive sol'n by calling helper
        return numLeavesHelper(root,"");
    }

    private int numLeavesHelper(TreeNode root,String path) {
        if(root==null){
            return 0;
        }
        if(root.getLeft()==null&&root.getRight()==null){
            key.put(root.getValue(),path);
            return 1;
        }
        else{
            return numLeavesHelper(root.getLeft(),path+"0")+numLeavesHelper(root.getRight(),path+"1");
        }
    }

    public byte[] makeMess(Map a)throws FileNotFoundException, IOException{
        FileInputStream sc = new FileInputStream(new File(fileName+".txt"));
        int g=0;
        String byt="";
        while(sc.available()>0){
            Character b=(char)sc.read();
            if(a.containsKey(b)){
                String s= (String) a.get(b);
                byt+=s;
            }
        }
        byte[] by= new byte[byt.length()/8+1];
        while(byt.length()>=8){
            byte p=0;
            int q=0;
            for(int i=8;i>0;i--){
                q+=Integer.parseInt(byt.substring(i-1,i))*Math.pow(2,8-i);
            }
            p=(byte)q;
            by[g]=p;
            byt=byt.substring(8,byt.length());
            g++;
        }

        if(byt.length()>0){
            while(byt.length()<8){
                byt+="0";
            }
            byte p=0;
            int q=0;
            for(int i=8;i>0;i--){
                q+=Integer.parseInt(byt.substring(i-1,i))*Math.pow(2,8-i);
            }
            p=(byte)q;
            by[g]=p;
            byt=byt.substring(8,byt.length());
            g++;
        }
        sc.close();
        return by;
    }
    
    
    //Heap Code after here.
    public boolean add(Object obj) {
        // you must write this add object at end and call percolateUp
        tree.add(obj);
        percolateUp();
        return true;
    }

    public Object remove() {
        // you must write this.  call percolate down
        if(!isEmpty()){
            if(tree.size()>1){
                Object a = tree.get(0);
                tree.set(0,tree.get(tree.size()-1));
                tree.remove(tree.size()-1);
                percolateDown();
                return a;
            }else{
                return tree.remove(0);
            }
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    private int parent(int node) {
        return (node - 1) / 2;
    }

    private int leftChild(int node) {
        return 2 * node + 1;
    }

    private int rightChild(int node) {
        return 2 * node + 2;
    }

    public boolean isEmpty() {
        // you must write this
        if(tree==null||tree.size()==0){
            return true;
        }else{
            return false;
        }
    }

    private void percolateUp() {
        // you must write this
        int a = tree.size()-1;
        Object b;
        Comparable temp=(Comparable)tree.get(parent(a));
        while(a>=0&&temp.compareTo(tree.get(a))>0){
            b= tree.get(a);
            tree.set(a,tree.get(parent(a)));
            tree.set(parent(a),b);
            a=parent(a);
            if(a>=0){
                temp=(Comparable)tree.get(parent(a));  
            }
        }
    }

    private void percolateDown() {
        // you must write this
        int a =0;
        Comparable b= (Comparable)tree.get(a);
        Comparable d;
        Object c;
        while(true){
            if(leftChild(a)<tree.size()){
                if(rightChild(a)<tree.size()){
                    if(b.compareTo(tree.get(leftChild(a)))>0){
                        if(b.compareTo(tree.get(rightChild(a)))>0){
                            d=(Comparable)tree.get(leftChild(a));
                            if(d.compareTo(tree.get(rightChild(a)))<0){
                                c= tree.get(a);
                                tree.set(a,tree.get(leftChild(a)));
                                tree.set(leftChild(a),c);
                                a=leftChild(a);
                            }else{
                                c= tree.get(a);
                                tree.set(a,tree.get(rightChild(a)));
                                tree.set(rightChild(a),c);
                                a=rightChild(a);
                            }
                        }else{
                            c= tree.get(a);
                            tree.set(a,tree.get(leftChild(a)));
                            tree.set(leftChild(a),c);
                            a=leftChild(a);
                        }
                    }else if(b.compareTo(tree.get(rightChild(a)))>0){
                        c= tree.get(a);
                        tree.set(a,tree.get(rightChild(a)));
                        tree.set(rightChild(a),c);
                        a=rightChild(a);
                    }else{
                        break; 
                    }
                    b=(Comparable)tree.get(a);
                }else if(b.compareTo(tree.get(leftChild(a)))>0){
                    c= tree.get(a);
                    tree.set(a,tree.get(leftChild(a)));
                    tree.set(leftChild(a),c);
                    a=leftChild(a);
                    b=(Comparable)tree.get(a);
                }else{
                    break;    
                }
            }else{
                break;
            }
        }
    }    
}
