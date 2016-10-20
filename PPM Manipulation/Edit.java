import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.List;
import java.util.Scanner;

/**
 * Write a description of class Edit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Edit
{
    int width, height;
    int mCVal=255;
    JOptionPane j =new JOptionPane();
    Random gen = new Random();
    /**
     * Creates random .ppm image
     */
    public Edit(int x, int y, int maxColor)throws IOException{
        height=y;
        width=x;
        mCVal=maxColor;
        String input = j.showInputDialog("Choose a file name:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println("P3");
        out.println(x+" "+y);
        out.println(maxColor);
        input = j.showInputDialog("Method:\n<1>Random");
        if(input.equals("1")){
            out.println(random(x,y,maxColor));
        }
        out.close();                                  // close the output file
        System.exit(0);     
    }

    public Edit()throws IOException{

    }
    /**
     * Averages orig with extreme contrast
     * Making it look like graphics from a 90's pc
     */
    public void cool()throws IOException{
        int q= 2;
        ArrayList<Scanner> f = new ArrayList();
        String input = j.showInputDialog("Choose a file to use:");
            f.add(new Scanner(new File(input+".ppm")));
        //
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("jkndanwladlanfseuihrvniurhceimucauqrcal3iuirlheunrliuvznmlriunh.ppm")));
        out.println(f.get(0).next());out.println(f.get(0).next()); out.println(f.get(0).next());int uh=Integer.parseInt(f.get(0).next()); out.println(uh);
        while(f.get(0).hasNext()){
            for(int rgb=0;rgb<3;rgb++){
                if(f.get(0).hasNext()){
                    String ass=f.get(0).next();

                    int azz= Integer.parseInt(ass);
                    if(azz<(uh/2)){
                        out.println(""+0);
                    }else{
                        out.println(""+uh);
                    }
                }
            }
        }
        out.close(); //
        
        f.set(0,(new Scanner(new File(input+".ppm"))));f.add(new Scanner(new File("jkndanwladlanfseuihrvniurhceimucauqrcal3iuirlheunrliuvznmlriunh.ppm")));
        input = j.showInputDialog("Choose a name for the new file:");
        out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println("P3");
        int height[]= new int[q]; int width[]= new int[q];
        int largest=0;
        for(int a=0;a<q;a++){
            f.get(a).next();
            height[a]=Integer.parseInt(f.get(a).next());
            width[a]=Integer.parseInt(f.get(a).next());
            //f.get(a).next();
        }
        for(int a=0;a<q;a++){
            if(height[largest]*width[largest]<height[a]*width[a]){
                largest=a;
            }
        }
        out.println(height[largest]);out.println(width[largest]);
        int ass=0;
        int azz=0;

        for(int x=0;x<width[largest];x++){
            for(int y=0;y<height[largest];y++){
                for(int c=0;c<3;c++){
                    for(int a=0;a<q;a++){
                        if(f.get(a).hasNext()){
                            if(x<width[a]&&y<height[a]){
                                ass=ass+Integer.parseInt(f.get(a).next());
                                azz++;
                            }
                        }
                    }
                    ass=ass/azz;
                    out.print(ass+" ");
                    ass=0;
                    azz=0;
                }
            }
        }

        for(int a =0;a<q;a++){
            f.get(a).close();
        }
        out.close();
        System.exit(0);
    }
    /**
     * Averages the pixels of chosen images (superimposing them)
     */
    public void average()throws IOException{
        String input = j.showInputDialog("Choose how many images you'll be using:");
        int q= Integer.parseInt(input);
        ArrayList<Scanner> f = new ArrayList();
        for(int a=0;a<q;a++){
            input = j.showInputDialog("Choose a file ("+(a+1)+"/"+q+ ") to use:");
            f.add(new Scanner(new File(input+".ppm")));
        }
        input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println("P3");
        int height[]= new int[q]; int width[]= new int[q];
        int largest=0;
        for(int a=0;a<q;a++){
            f.get(a).next();
            height[a]=Integer.parseInt(f.get(a).next());
            width[a]=Integer.parseInt(f.get(a).next());
            //f.get(a).next();
        }
        for(int a=0;a<q;a++){
            if(height[largest]*width[largest]<height[a]*width[a]){
                largest=a;
            }
        }
        out.println(height[largest]);out.println(width[largest]);
        int ass=0;
        int azz=0;

        for(int x=0;x<width[largest];x++){
            for(int y=0;y<height[largest];y++){
                for(int c=0;c<3;c++){
                    for(int a=0;a<q;a++){
                        if(f.get(a).hasNext()){
                            if(x<width[a]&&y<height[a]){
                                ass=ass+Integer.parseInt(f.get(a).next());
                                azz++;
                            }
                        }
                    }
                    ass=ass/azz;
                    out.print(ass+" ");
                    ass=0;
                    azz=0;
                }
            }
        }

        for(int a =0;a<q;a++){
            f.get(a).close();
        }
        out.close();
        System.exit(0);
    }
    
    /**
     * Breaks up and checkers image into chosen number of squares
     * (Doesn't work it does twice as many as chosen (for some reason working with two mini versions of the orig image)
     * Only works for numbers divis by two greater than or equal to four)
     */
    public void checkers() throws IOException{
        String input = j.showInputDialog("How many squares would you like?");
        int num=Integer.parseInt(input);
        input = j.showInputDialog("Choose a file to use:");
        Scanner f= new Scanner(new File(input+".ppm"));
        input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println(f.next());
        int height=Integer.parseInt(f.next());
        int width=Integer.parseInt(f.next());
        out.println(height);out.println(width);out.println(f.next());
        int ab[][][]=new int[width][height][3];
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                for(int c=0;c<3;c++){
                    ab[x][y][c]=Integer.parseInt(f.next());
                }
            }
        }
        int smhei=height/(int)Math.sqrt(num);
        int smwid=width/(int)Math.sqrt(num);
        int temp[][][][] = new int[num][smwid][smhei][3];
        int x2=0;int y2=0; int d=0;

        for(int x=0;x<width;x++){
            if(x!=0){
                d=(height/smhei)*(x/smwid);
                if(x%smwid==0){
                    x2=0;
                }
            }
            for(int y=0;y<height;y++){
                if(y!=0&&y%smhei==0){
                    y2=0;d++; 
                }
                for(int c=0;c<3;c++){
                    temp[d][x2][y2][c]=ab[x][y][c];
                }
                y2++;
            }
            y2=0;
            x2++;
        }
        /*int raN[]=new int[num];
        for(int i=0;i<num;i++){
            raN[i]=i;
        }*/
        int r=num-1;
        for(int n=0;n<num;n++){
            /*
            r=gen.nextInt(num);
            while(raN[r]<0){
                r=gen.nextInt(num);
            }*/
            if(r>=0){
                
            }
            for(int x=0;x<smwid;x++){
                for(int y=0;y<smhei;y++){
                    for(int c=0;c<3;c++){
                        out.print(temp[r][x][y][c]+" ");
                    }
                }
            }
            //raN[r]=raN[r]*-1;
            r--;
        }
        f.close();
        out.close();
        System.exit(0);
    }

    public String random(int x, int y, int maxc){
        String str="";
        for(int i=0;i<y;i++){
            for(int g=0;g<x;g++){
                for(int rgb=0;rgb<3;rgb++){
                    str+=gen.nextInt(maxc+1)+" ";
                }
            }
        }
        return str;
    }

    /**
     * Negates Chosen Colors
     */
    public void neg()throws IOException{
        String input = j.showInputDialog("Choose a file to use:");
        Scanner ab = new Scanner(new File(input+".ppm"));
        input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println(ab.next());out.println(ab.next()); out.println(ab.next()); int uh=Integer.parseInt(ab.next()); out.println(uh);
        input = j.showInputDialog("Choose a color to make negative: (1)R (2)G (3)B (4)All");
        int da;
        if(Integer.parseInt(input)==3){
            da=2;
        }else if(Integer.parseInt(input)==2){
            da=1;
        }else if(Integer.parseInt(input)==4){
            da=3;
        }else{
            da=0;  
        }
        while(ab.hasNext()){
            for(int rgb=0;rgb<3;rgb++){
                if(rgb==da){
                    if(ab.hasNext()){
                        String ass=ab.next();

                        int azz= Integer.parseInt(ass);
                        out.println(""+(uh-azz));
                    }
                }else if(da==3){
                    if(ab.hasNext()){
                        String ass=ab.next();

                        int azz= Integer.parseInt(ass);
                        out.println(""+(uh-azz));}
                }else{
                    if(ab.hasNext()){
                        out.println(ab.next());}
                }
            }
        }
        out.close(); 
        ab.close();
        System.exit(0);
    }
    /**
     * Creates random noise
     */
    public void noise()throws IOException{
        String input = j.showInputDialog("Choose a file to use:");
        Scanner ab = new Scanner(new File(input+".ppm"));
        input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println(ab.next());out.println(ab.next()); out.println(ab.next()); int uh=Integer.parseInt(ab.next()); out.println(uh);
        input=j.showInputDialog("How much noise?:");
        int rag=Integer.parseInt(input);
        while(ab.hasNext()){
            for(int rgb=0;rgb<3;rgb++){
                int r=gen.nextInt(rag);
                if(ab.hasNext()){
                    String ass=ab.next();

                    int azz= Integer.parseInt(ass);
                    int aos=gen.nextInt(1000);

                    if(aos<500){
                        if(azz-r>0){
                            out.println(""+(azz-r));
                        }else{
                            out.println(""+0);
                        }}else{
                        if(azz+r<uh){
                            out.println(""+(azz+r));
                        }else{
                            out.println(""+uh);
                        }}
                }
            }
        }
        out.close(); 
        ab.close();
        System.exit(0);
    }

    /**
     * Creates Extreme Contrast
     */
    public void cont()throws IOException{
        String input = j.showInputDialog("Choose a file to use:");
        Scanner ab = new Scanner(new File(input+".ppm"));
        input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println(ab.next());out.println(ab.next()); out.println(ab.next());int uh=Integer.parseInt(ab.next()); out.println(uh);

        while(ab.hasNext()){
            for(int rgb=0;rgb<3;rgb++){
                if(ab.hasNext()){
                    String ass=ab.next();

                    int azz= Integer.parseInt(ass);
                    if(azz<(uh/2)){
                        out.println(""+0);
                    }else{
                        out.println(""+uh);
                    }
                }
            }
        }
        out.close(); 
        ab.close();
        System.exit(0);
    }
    
    /**
     * Flattens the chosen colors
     */
    public void flat()throws IOException{
        String input = j.showInputDialog("Choose a file to use:");
        Scanner ab = new Scanner(new File(input+".ppm"));input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println(ab.next());out.println(ab.next()); out.println(ab.next()); out.println(ab.next());
        input = j.showInputDialog("Choose a color to flatten: (1)R (2)G (3)B (4)All");
        int da;
        if(Integer.parseInt(input)==3){
            da=2;
        }else if(Integer.parseInt(input)==2){
            da=1;
        }else if(Integer.parseInt(input)==4){
            da=3;
        }else{
            da=0;  
        }
        while(ab.hasNext()){
            for(int rgb=0;rgb<3;rgb++){
                if(rgb==da){
                    if(ab.hasNext()){
                        ab.next();}
                    out.println(""+0);

                }else if(da==3){
                    if(ab.hasNext()){
                        ab.next();}
                    out.println(""+0);
                }else{
                    if(ab.hasNext()){
                        out.println(ab.next());}
                }
            }
        }
        out.close(); 
        ab.close();
        System.exit(0);
    }
    

    /**
     * Does a Grey_Scale
     */
    public void grey()throws IOException{
        String input = j.showInputDialog("Choose a file to use:");
        Scanner ab = new Scanner(new File(input+".ppm"));
        input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        out.println(ab.next());out.println(ab.next()); out.println(ab.next()); out.println(ab.next());
        int as[]=new int[3];
        while(ab.hasNext()){
            for(int rgb=0;rgb<3;rgb++){
                String ass=ab.next();
                as[rgb]= Integer.parseInt(ass);
            }
            int az=(as[0]+as[1]+as[2])/3;
            out.println(""+az);out.println(""+az);out.println(""+az);
        }
        out.close(); 
        ab.close();
        System.exit(0);
    }

    /**
     * Does something strange
     */
    public void strange()throws IOException{
        String input = j.showInputDialog("Choose a file to use:");
        Scanner ab = new Scanner(new File(input+".ppm"));input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        input = j.showInputDialog("Strange: (1)Strange (2)Strange");
        out.println(ab.next());
        int height=Integer.parseInt(ab.next());
        int width=Integer.parseInt(ab.next());
        out.println(height);out.println(width);
        if(Integer.parseInt(input)!=3){
            out.println(ab.next());
        }
        int asd[][][]=new int[height][width][3];
        for(int y=0;y<height;y++){
            for(int x=0;x<width;x++){
                for(int rgb=0;rgb<3;rgb++){
                    String ass=ab.next();
                    asd[y][x][rgb]= Integer.parseInt(ass);
                }
            }
        }
        if(Integer.parseInt(input)==1){
            for(int y=height-1;y>=0;y--){
                for(int x=0;x<width;x++){
                    for(int rgb=0;rgb<3;rgb++){
                        out.print(" "+asd[y][x][rgb]);
                    }
                }
            }}
        else{
            for(int y=0;y<height;y++){
                for(int x=width-1;x>=0;x--){
                    for(int rgb=0;rgb<3;rgb++){
                        out.print(" "+asd[y][x][rgb]);
                    }
                }
            }}
        out.close(); 
        ab.close();
        System.exit(0);
    }

    /**
     * Flips horizontal, vertical, oboth
     */
    public void flip()throws IOException{
        String input = j.showInputDialog("Choose a file to use:");
        Scanner ab = new Scanner(new File(input+".ppm"));
        input = j.showInputDialog("Choose a name for the new file:");
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(input+".ppm")));
        input = j.showInputDialog("Flip: (1)Horizontal (2)Vertical (3)Both");
        out.println(ab.next());
        int height=Integer.parseInt(ab.next());
        int width=Integer.parseInt(ab.next());
        out.println(height);out.println(width);
        out.println(ab.next());
        int asd[][][]=new int[width][height][3];
        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                for(int rgb=0;rgb<3;rgb++){
                    String ass=ab.next();
                    asd[x][y][rgb]= Integer.parseInt(ass);
                }
            }
        }
        if(Integer.parseInt(input)==1){
            for(int x=0;x<width;x++){
                for(int y=height-1;y>=0;y--){
                    for(int rgb=0;rgb<3;rgb++){
                        out.print(" "+asd[x][y][rgb]+" ");
                    }
                }
            }
        }
        else if(Integer.parseInt(input)==2){
            for(int x=width-1;x>=0;x--){
                for(int y=0;y<height;y++){
                    for(int rgb=0;rgb<3;rgb++){
                        out.print(" "+asd[x][y][rgb]+" ");
                    }
                }
            }
        }else{
            for(int x=width-1;x>=0;x--){
                for(int y=height-1;y>=0;y--){
                    for(int rgb=0;rgb<3;rgb++){
                        out.print(" "+asd[x][y][rgb]);
                    }
                }
            }
        }
        out.close(); 
        ab.close();
        System.exit(0);
    }

}
