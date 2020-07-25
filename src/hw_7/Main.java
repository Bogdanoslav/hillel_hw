package hw_7;

public class Main{
    public static void main(String[] args) {
        MyStrCollection test = new MyStrCollection();
        test.add("A");
        test.add("B");
        test.add("C");
        test.add("D");
        test.add("E");
        test.add("F");
        test.add("G");
        test.add("H");
        test.add("I");
        test.add("J");
        test.add("K");
        test.add("L");
        test.add("M");
        test.add("TESTING",3);
        test.add("TESTING",0);
        test.delete(0);
        test.delete(11);
        test.delete("TESTING");
        test.delete("TESTING");
        test.delete("TESTING");

        for(int i = 0; i < test.str_arr.length;i++){
            System.out.println(test.str_arr[i]);
        }
    }
}