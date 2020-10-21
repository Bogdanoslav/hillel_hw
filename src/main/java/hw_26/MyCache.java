package hw_26;

public class MyCache {
    final private int lifeTime;
    final private int size;
    private MyLRUMap<Integer, Object> objects;

    public MyCache(int time, int size){
        this.size = size;
        objects = new MyLRUMap<>(this.size);
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
