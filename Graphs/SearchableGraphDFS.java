import java.util.List;
import java.util.*;

public class SearchableGraphDFS<V, E> extends Graph<V,E> implements SearchableGraph<V, E>
{
    List<V> path=new ArrayList();
    public SearchableGraphDFS(){

    }

    public boolean reachable(V v1, V v2){
        List<List<V>> pt=new ArrayList();
        Set s = (Set)neighbors(v1);
        Iterator i= s.iterator();
        vertex.get(v1).visited=true;
        while(i.hasNext()){
            V v=(V)i.next();
            if(!vertex.get(v).visited){
                pt.add(shortestPathHelper(v,v2));
            }
        }
        vertex.get(v1).visited=false;

        if(pt.size()>0){
            for(int q=0;q<pt.size();q++){
                if(pt.get(q).size()==0||!pt.get(q).get(pt.get(q).size()-1).equals(v2)){
                    pt.remove(q);
                }
            }
        }
        while(pt.size()>0){
            int sml=pt.get(0).size();
            int smlI=0;
            for(int q=0;q<pt.size();q++){
                if(sml>pt.get(q).size()&&pt.get(q).size()!=0){
                    sml=pt.get(q).size();
                    smlI=q;
                }
            }
            pt.get(smlI).add(0,v1);
            if(pt.get(smlI).get(pt.get(smlI).size()-1).equals(v2)){
                return true;
            }
            pt.remove(smlI);
        }
        if(path.size()!=0){
            path=new ArrayList();
            return true;
        }
        return false;
    }

    public List<V> shortestPath(V v1, V v2){
        Set s = (Set)neighbors(v1);
        Iterator i= s.iterator();
        vertex.get(v1).visited=true;
        List<List<V>> pt=new ArrayList();
        while(i.hasNext()){
            V v=(V)i.next();
            if(!vertex.get(v).visited){
                pt.add(shortestPathHelper(v,v2));
            }
        }
        vertex.get(v1).visited=false;

        if(pt.size()>0){
            for(int q=0;q<pt.size();q++){
                if(pt.get(q).size()==0||!pt.get(q).get(pt.get(q).size()-1).equals(v2)){
                    pt.remove(q);
                }
            }
        }
        while(pt.size()>0){
            int sml=pt.get(0).size();
            int smlI=0;
            for(int q=0;q<pt.size();q++){
                if(sml>pt.get(q).size()&&pt.get(q).size()!=0){
                    sml=pt.get(q).size();
                    smlI=q;
                }
            }
            pt.get(smlI).add(0,v1);
            if(pt.get(smlI).get(pt.get(smlI).size()-1).equals(v2)){
                return pt.get(smlI);
            }
            pt.remove(smlI);
        }

        return null;
    }

    public List<V>  shortestPathHelper(V v1, V v2){
        if(v1.equals(v2)){
            List<V> pt=new ArrayList();
            pt.add(v2);
            return pt;
        }
        List<List<V>> pt=new ArrayList();
        Set s = (Set)neighbors(v1);
        Iterator i= s.iterator();
        vertex.get(v1).visited=true;
        while(i.hasNext()){
            V v=(V)i.next();
            if(!vertex.get(v).visited){
                pt.add(shortestPathHelper(v,v2));
            }
        }
        vertex.get(v1).visited=false;
        if(pt.size()>0){
            for(int q=0;q<pt.size();q++){
                if(pt.get(q).size()==0||!pt.get(q).get(pt.get(q).size()-1).equals(v2)){
                    pt.remove(q);
                }
            }
        }
        while(pt.size()>0){
            int sml=pt.get(0).size();
            int smlI=0;
            for(int q=0;q<pt.size();q++){
                if(sml>pt.get(q).size()&&pt.get(q).size()!=0){
                    sml=pt.get(q).size();
                    smlI=q;
                }
            }
            pt.get(smlI).add(0,v1);
            if(pt.get(smlI).get(pt.get(smlI).size()-1).equals(v2)){
                return pt.get(smlI);
            }
            pt.remove(smlI);
        }
        return new ArrayList();
    }

}
