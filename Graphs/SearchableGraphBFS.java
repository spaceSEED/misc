import java.util.List;
import java.util.*;

public class SearchableGraphBFS<V, E> extends Graph<V,E> implements SearchableGraph<V, E> 
{
    // protected  Map<V, Map<V, EdgeInfo<E>>> adjacencyMap=new HashMap<V, Map<V,EdgeInfo<E>>>();  	// [vertices] -> [edge]
    //protected  Map<V, VertexInfo<V>> vertex= new HashMap<V, VertexInfo<V>>();  			// [vertex] -> [info]
    // protected  List<E> edgeList=new ArrayList<E>();

    public SearchableGraphBFS(){

    }

    public boolean reachable(V v1, V v2){
        Stack stk = new Stack();
        Stack stk2 = new Stack();
        Map<V, VertexInfo<V>> path = vertex;
        Queue q = new LinkedList();
        stk.push(v1);
        path.get(v1).visited=true;
        V root=v1;
        List<V> p = new ArrayList();
        if(containsEdge(v1,v2)){
            p.add(v1);
            p.add(v2);
            return true;
        }else{
            do{
                while(!stk.isEmpty()){
                    root=(V)stk.pop();
                    Iterator i = neighbors(root).iterator();
                    while(i.hasNext()){
                        V vk= (V)i.next();
                        if(vk.equals(v2)){
                            path.get(vk).visited=true;
                            path.get(vk).previous=root;
                            Iterator ite=vertices().iterator();
                            while(ite.hasNext()){
                                V v3=(V)ite.next();
                                path.get(v3).previous=null;
                                path.get(v3).visited=false;
                            }
                            return true;
                        }
                        if(!path.get(vk).visited){
                            path.get(vk).visited=true;
                            path.get(vk).previous=root;
                            stk2.push(vk);
                        }
                    }
                }
                while(!stk2.isEmpty()){
                    stk.push(stk2.pop());
                }
            }while(!stk.isEmpty());
            
            Iterator ite=vertices().iterator();
            while(ite.hasNext()){
                V v3=(V)ite.next();
                path.get(v3).previous=null;
                path.get(v3).visited=false;
            }
            return false;
        }

        //return reachable(v1, v2,new ArrayList());
    }

    /*public List<V> minimumWeightPath(V v1, V v2){
    return null;
    }*/

    public boolean reachable(V v1, V v2, List<V> visited){
        //List<V> path= new ArrayList();
        List<V> v= visited;
        Set s = (Set)neighbors(v1);
        if(s==null){
            return false;
        }
        Iterator i= s.iterator();
        //path.add(v1);
        v.add(v1);
        while(i.hasNext()){
            V vk= (V)i.next();
            if(vk.equals(v2)){
                return true;
            }
        }  
        i= s.iterator();
        List<Boolean> b= new ArrayList();
        while(i.hasNext()){
            V vk= (V)i.next();
            if(!v.contains(vk)){
                b.add(reachable(vk,v2,v));  
            }
            //return ;
        } 
        if(b.size()!=0){
            for(int c=0;c<b.size();c++){
                if(b.get(c)!=null){
                    return true;
                }
            }
        }
        return false;
    }

    public List<V> shortestPath(V v1, V v2){
        return shortestPathHelper(v1,v2/*,new ArrayList()*/);
    }

    public List<V> shortestPathHelper(V v1, V v2 /*,List<V> visited*/){
        Stack stk = new Stack();    // 
        Stack stk2 = new Stack();
        Map<V, VertexInfo<V>> path = vertex;
        Queue q = new LinkedList();
        stk.push(v1);
        path.get(v1).visited=true;
        V root=v1;
        List<V> p = new ArrayList();
        if(containsEdge(v1,v2)){
            p.add(v1);
            p.add(v2);
            return p;
        }else{
            do{
                while(!stk.isEmpty()){
                    root=(V)stk.pop();
                    Iterator i = neighbors(root).iterator();
                    while(i.hasNext()){
                        V vk= (V)i.next();
                        if(vk.equals(v2)){
                            path.get(vk).visited=true;
                            path.get(vk).previous=root;
                            for(V b=vk;b!=null;b=path.get(b).previous){
                                p.add(0,b);
                            }
                            Iterator ite=vertices().iterator();
                            while(ite.hasNext()){
                                V v3=(V)ite.next();
                                path.get(v3).previous=null;
                                path.get(v3).visited=false;
                            }
                            return p;
                        }
                        if(!path.get(vk).visited){
                            path.get(vk).visited=true;
                            path.get(vk).previous=root;
                            stk2.push(vk);
                            //stk2.push(root);

                        }
                    }
                }
                while(!stk2.isEmpty()){
                    stk.push(stk2.pop());
                }
            }while(!stk.isEmpty());
            
            for(V b=v2;b!=null;b=path.get(b).previous){
                p.add(0,b);
            }
            Iterator ite=vertices().iterator();
            while(ite.hasNext()){
                V v3=(V)ite.next();
                path.get(v3).previous=null;
                path.get(v3).visited=false;
            }
            return p;
        }

    }
}

