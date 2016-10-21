
public class RadixSort
{
    public RadixSort()
    {

    }

    public int[] radix(int jake[]){
        ArrayQueue que[] = new ArrayQueue[10];
        for(int i=0;i<que.length;i++){
            que[i]=new ArrayQueue();
        }
        int n=1;
        int qwer=place(jake);//from state farm
        int a=0;
        int w=0;
        while(qwer>a){
            for(int i=0;i<jake.length;i++){
                que[(jake[i]%(n*10))/n].enqueue(jake[i]);
            }
            w=0;
            for(int q=0;q<que.length;q++){
                while(!que[q].isEmpty()){
                    jake[w]=que[q].dequeue();
                    w++;
                }
            }
            n=n*10;
            a++;
        }
        return jake;
    }
    

    public int place(int array[]){
        int q[]=new int[array.length];
        int dem[]=new int[array.length];
        for(int i=0;i<array.length;i++){
            dem[i]=array[i];
        }
        for(int i=0;i<array.length;i++){
            while(dem[i]!=0){
                dem[i]=dem[i]/10;  
                q[i]++;
            }
        }
        int r=0;
        for(int i=0;i<q.length;i++){
            if(q[i]>r){
                r=q[i];
            }
        }
        return r;
    }
}
