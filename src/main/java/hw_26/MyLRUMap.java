package hw_26;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class MyLRUMap<K, V> {
    private HashMap<K, V> hashMap;
    private LinkedList<V> linkedList;
    private int size;
    private int count;

    public MyLRUMap(int size){
        hashMap = new HashMap<>();
        linkedList = new LinkedList<>();
        this.size = size;
        count = 0;
    }

    public void put(K key ,V val) {
        if(count==size){
            hashMap.remove(linkedList.getFirst().hashCode());
            linkedList.removeFirst();;
            count--;
        }
        if(!linkedList.contains(val)){
            linkedList.add(val);
            hashMap.put(key, val);
        }
        else{
            linkedList.remove(val);
            linkedList.addLast(val);
        }
        count++;
    }
    public V get(V val){
        if(linkedList.contains(val)){
            linkedList.remove(val);
            linkedList.addLast(val);
            return val;
        }
        return null;
    }
    public void clear(){
        hashMap.clear();
        linkedList.clear();
        count = 0;
    }
    public Collection<K> keySet(){
        return hashMap.keySet();
    }
    public Collection<V> values(){
        return hashMap.values();
    }

}
