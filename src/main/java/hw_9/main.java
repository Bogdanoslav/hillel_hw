package hw_9;

import java.util.ArrayList;
import java.util.Collection;

public class main {
    public static void main(String[] args) {
        LinkedList l = new LinkedList();

        l.add("1");
        l.add(null);
        l.add("2");

        String[] arr = {"3","4"};
        Collection<String> coll = new ArrayList<>();
        coll.add("5");
        coll.add("6");


        l.addAll(arr);
        l.add(null);
        l.add(null);
        l.addAll(coll);

        l.displayList();
        l.trim();

        l.displayList();

        l.delete(0);
        l.delete("6");

        l.displayList();

        System.out.println();

        System.out.println("get(2): " + l.get(3));
        System.out.println("contains(\"4\") " +  l.contains("3"));
        System.out.println("size(): " +  l.size());

        System.out.println();

        l.clear();
        l.displayList();
        System.out.println("size(): " +  l.size());

        System.out.println();

        l.add("5");
        l.add("6");
        System.out.println(l.compare(coll));
        l.add("6");
        System.out.println(l.compare(coll));
    }
}
