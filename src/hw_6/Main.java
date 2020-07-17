package hw_6;

public class Main{
    public static void main(String[] args) {
        MyStrCollection test = new MyStrCollection();
        test.add("A");
        test.add("B");
        test.add("C");
        test.add("D");
        test.add("E");
        test.add("E");
        test.add("E");
        test.add("E");
        System.out.println("Count: " + test.getCount());
        System.out.println(test.toString());
        test.delete(2);
        System.out.println(test.toString());
        test.delete("A");
        test.delete("E");
        test.delete("E");
        System.out.println(test.toString());
        test.add(6, "3");
        System.out.println(test.toString());
        test.add("E");
        test.add("E");
        test.add("E");
        test.add("E");
        test.add("E");
        test.add("E");
        test.add("E");
        test.add("E");
        System.out.println("Count: " + test.getCount());
    }
}