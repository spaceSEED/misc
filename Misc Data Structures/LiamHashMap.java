import java.util.*;

public class LiamHashMap //implements Map
{
    int sz=31;
    Object mp[]=new Object[sz];
    Object kp[]=new Object[sz];
    Object del= new Object();
    public LiamHashMap()
    {
    }

    /*public Set<Map.Entry<K,V>> entrySet(){
    return new Set<Map.Entry<K,V>>();
    }*/

    Object put(Object key, Object value){
        int k=key.hashCode();
        for(int o=k%sz;o<sz;o++){
            if(mp[o]==null){
                mp[o]=value;
                kp[o]=key;
                break;
            }
        }
        return null;
    }

    Object get(Object key){
        int k=key.hashCode();
        for(int o=k%sz;o<sz;o++){
            if(kp[o].equals(key)){
                return mp[0];
            }
        }
        return mp[k%sz];
    }

    Object remove(Object key){
        int k=key.hashCode();
        for(int o=k%sz;o<sz;o++){
            if(kp[o].equals(key)){
                k=o;
                break;
            }
        }
        Object a=mp[k];
        mp[k]= del;
        kp[k]=del;
        return a;
    }

    boolean containsKey(Object key){
        int k=key.hashCode();
        if(kp[k%sz].equals(key)){
            return true;
        }else{
            for(int o=k%sz;o<sz;o++){
            if(kp[o].equals(key)){
                return true;
            }
          }
        }
        return false;
    }

    int size(){
        int a=0;
        for(int i=0; i<sz;i++){
            if(mp[i]!=null&&!mp[i].equals(del)){
                a++;
            }
        }
        return a;
    }

    Set keySet(){
        Set a = new TreeSet();
          for(int o=0;o<sz;o++){
            if(kp[o]!=null&&!kp[o].equals(del)){
                a.add(kp[o]);
            }
          }
        return a;
    }
}
