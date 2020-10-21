package hw_26;

import com.fasterxml.jackson.databind.util.LRUMap;

import java.util.HashMap;

public class MyCache {
    private int lifeTime;
    private MyLRUMap<Integer, Object> objects;

    public MyCache(int time, int size){
        objects = new MyLRUMap<>(size);
        lifeTime = time;
        clearCache();
    }

    public Object printValues(){
        return objects.values();
    }
    public void put(Object obj){
        objects.put(obj.hashCode(), obj);
    }
    public Object get(Object obj) { return objects.get(obj);}
    public synchronized void clearCache(){ new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(lifeTime * 1000);
            } catch (InterruptedException ex) {
            }
            objects.clear();
        }
    }).start();}

}
