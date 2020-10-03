package hw_8;

public class run {
    public static void main(String[] args) {
        StringCollection s = new StringCollection();
        s.add("1");
        s.add("3");
        s.add("4");
        s.add("5");
        s.add("6");
        s.add("7");
        s.add("8");
        s.add("9");
        s.add("10");
        s.add("11");
        System.out.println("Size before delete = " + s.size());
        s.delete("11");
        s.delete("1");
        System.out.println("Size after 2 deletes = " + s.size());
        s.add(0, "2");
        s.add(9, "11");
        System.out.println("Size after 2 adds = " + s.size());

        System.out.println(s);

        System.out.println("s.get() " + s.get(5));

        System.out.println("s.contains(\"10\") " + s.contain("10"));
        System.out.println("s.contains(\"1\") " + s.contain("1"));
        System.out.println();
        StringCollection s1 = new StringCollection();
        s1.str_arr = s.str_arr;
        System.out.println("s = s1 " + s.equals(s1));
        s1 = s;
        System.out.println("s = s1 " + s.equals(s1));

        s.clear();
        System.out.println(s);
        System.out.println("Size = " + s.size());


    }
}
