import java.util.*;
import java.util.Scanner;
import java.io.*;
import javax.swing.JOptionPane;

public class Decode
{

    public Decode()throws FileNotFoundException, IOException
    {
        JOptionPane jp= new JOptionPane();
        String n=jp.showInputDialog("What file(.comp.txt) would you like to decompress?\n[input without '.comp.txt' at the end]");
        FileInputStream sc= new FileInputStream(new File(n+".comp.txt"));
        Map key= new TreeMap();
        ArrayList<Character> answer=new ArrayList<Character>();
        String last="";
        Byte cur=0;
        String ans="";
        Boolean ba=false;
        for(Byte cycle=0;cycle!=(byte)255;){
            byte a=(byte)sc.read();
            if(a!=-1){
                String s="";
                Byte fg=a;
                int len = fg.intValue();
                int qws=len;
                fg=(byte)sc.read();
                int v=fg.intValue();
                int r=v;
                if(r<0){
                    r=v+256;
                }
                for(int q=7;q>=0;q--){
                    if(r>=(int)Math.pow(2,q)){
                        s+="1";
                        r=r-(int)Math.pow(2,q);
                    }else{
                        s+="0";
                    }
                }
                qws=qws-8;
                while(qws>0){
                    fg=(byte)sc.read();
                    v=fg.intValue();
                    r=v;
                    if(r<0){
                        r=v+256;
                    }
                    for(int q=7;q>=0;q--){
                        if(r>=(int)Math.pow(2,q)){
                            s+="1";
                            r=r-(int)Math.pow(2,q);
                        }else{
                            s+="0";
                        }
                    }
                    qws=qws-8;
                }
                key.put(s.substring(0,len),(char)sc.read());
            }else{
                cycle=a;
            }
        }
        //System.out.println();
        while(sc.available()>0){
            cur=(byte)sc.read();
            // System.out.print(cur);
            String s="";
            int v=cur.intValue();
            //boolean quop= v%2>0;
            int r=v;//v/2;//fix here
            if(r<0){
                r=v+256;
            }
            for(int q=7;q>=0;q--){
                if(r>=(int)Math.pow(2,q)){
                    s+="1";
                    r=r-(int)Math.pow(2,q);
                }else{
                    s+="0";
                }
            }
            /*if(quop){
            s+="1";
            }else{
            s+="0";
            }*/
            last+=s;
        }

        String pa="";
        //byte p=0;
        for(int w=0;w<last.length()-1;w++){
            pa+=last.substring(w,w+1);
            /*p=0;
            int q=0;
            for(int e=pa.length();e>0;e--){
            q+=Integer.parseInt(pa.substring(e-1,e))*Math.pow(2,pa.length()-e);
            }
            p=(byte)q;*/
            if(key.containsKey(pa)){
                answer.add((char)key.get(pa));
                pa="";
            }
        }
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(n+".decomp.txt")));
        for(int i=0;i<answer.size();i++){
            ans+=answer.get(i).toString();
        }
        out.print(ans);out.close();
        System.out.println(ans);
        
        FileInputStream scd = new FileInputStream(new File(n+".comp.txt"));
        int t= scd.available();
        scd = new FileInputStream(new File(n+".decomp.txt"));
        int y=scd.available();
        jp.showMessageDialog(null, "File decompressed successfully as "+n+".comp.txt\nOriginal: "+t+" bytes New: "+y+" bytes");
        
        //System.out.println(last);

    }

}
