import java.util.List;
import java.util.*;

/**
 * Write a description of class Graph here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Graph<V,E> implements SearchableGraph<V, E> 
{
    protected Map<V, Map<V, EdgeInfo<E>>> adjacencyMap=new HashMap<V, Map<V,EdgeInfo<E>>>();  	// [vertices] -> [edge]
    protected  Map<V, VertexInfo<V>> vertex= new HashMap<V, VertexInfo<V>>();  			// [vertex] -> [info]
    protected  List<E> edgeList=new ArrayList<E>();

    /*public List<V> minimumWeightPath(V v1, V v2){
    return null;
    }*/
    public boolean reachable(V v1, V v2){
        return false;
    }

    public List<V> shortestPath(V v1, V v2){

        return null;
    }

    public E edge(V v1, V v2){
        Map<V, EdgeInfo<E>> a=null;
        if(containsVertex(v1)){
            a=adjacencyMap.get(v1);
        }
        Set s= a.keySet();
        Iterator it = s.iterator();
        V v=null;
        while(it.hasNext()){
            v=(V)it.next();
            if(v.equals(v2)){
                break;
            }else{
                v=null;  
            }
        }
        if(v==null){
            return null;
        }
        EdgeInfo<E> ei=a.get(v);
        String str= ei.toString();
        return (E)str.substring(str.indexOf("("),str.indexOf(":"));
    }

    public Collection<E> edges(){
        Collection<E> e= edgeList;
        return e;
    }

    public int edgeWeight(V v1, V v2){
        Map<V, EdgeInfo<E>> a=null;
        if(containsVertex(v1)){
            a=adjacencyMap.get(v1);
        }
        Set s= a.keySet();
        Iterator it = s.iterator();
        V v=null;
        while(it.hasNext()){
            v=(V)it.next();
            if(v.equals(v2)){
                break;
            }else{
                v=null;  
            }
        }
        if(v==null){
            return 0;
        }
        EdgeInfo<E> ei=a.get(v);
        String str= ei.toString();
        return Integer.parseInt(str.substring(str.indexOf(":"),str.indexOf(")")));
    }

    public void addEdge(V v1, V v2, E e){
        adjacencyMap.get(v1).put(v2,new EdgeInfo(e));
        edgeList.add(e);
    }

    public void addEdge(V v1, V v2, E e, int weight){
        adjacencyMap.get(v1).put(v2,new EdgeInfo(e,weight));
        edgeList.add(e);
    }

    public boolean containsEdge(V v1, V v2){
        Map<V, EdgeInfo<E>> a=null;
        if(containsVertex(v1)){
            a=adjacencyMap.get(v1);
        }
        Set s= a.keySet();
        Iterator it = s.iterator();
        V v=null;
        while(it.hasNext()){
            v=(V)it.next();
            if(v.equals(v2)){
                break;
            }else{
                v=null;  
            }
        }
        if(v==null){
            return false;
        }
        return true;
    }

    public void addVertex(V v){
        vertex.put(v,new VertexInfo<V>(v));
        adjacencyMap.put(v, new HashMap<V,EdgeInfo<E>>());
    }

    public boolean containsVertex(V v){
        Set s= adjacencyMap.keySet();
        Iterator it = s.iterator();
        V c=null;
        while(it.hasNext()){
            c=(V)it.next();
            if(c.equals(v)){
                return true;
            }
        }
        return false;
    }

    public Collection<V> neighbors(V v){
        if(adjacencyMap.containsKey(v)){
            return adjacencyMap.get(v).keySet();
        }
        return null;
    }

    public Collection<V> vertices(){
        Iterator i = adjacencyMap.keySet().iterator();
        Collection<V> c = new ArrayList<V>();
        while(i.hasNext()){
            c.add((V)i.next());
        }
        return c;
    }
}
