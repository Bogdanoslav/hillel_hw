package hw_9_Double_Linked_List;

import java.util.ArrayList;
import java.util.Collection;

public class run {
    public static void main(String[] args) {
        MyDoubleLinkedList d_list = new MyDoubleLinkedList();

        //   addAll collection
        System.out.println("        (1)");
        Collection<String> col = new ArrayList<>();
        col.add("A");
        col.add("B");
        col.add("C");
        col.add("D");
        d_list.addAll(col);
        System.out.println("Col = " + col.toString());
        d_list.displayForward();

        //  compare
        System.out.println("        (2)");
        System.out.println("d_list.compare(col) = " + d_list.compare(col));
        System.out.println();
        col.add("X");
        System.out.println("Col = " + col.toString());
        d_list.displayForward();
        System.out.println("d_list.compare(col) = " + d_list.compare(col));

        //addAll array
        System.out.println("        (3)");
        String[] arr = {"E", "F"};
        d_list.addAll(arr);
        d_list.displayForward();

        //delete
        System.out.println("        (4)");
        d_list.delete(0);
        d_list.delete(2);
        d_list.delete("E");
        d_list.displayForward();

        //get
        System.out.println("        (5)");
        System.out.println("d_list.get(2) = " + d_list.get(2));
        System.out.println();

        //size
        System.out.println("        (6)");
        System.out.println("d_list.size() = " + d_list.size());
        System.out.println();

        //clear
        System.out.println("        (7)");
        System.out.println("d_list.clear() = " + d_list.clear());
        d_list.displayForward();

        //trim
        System.out.println("        (8)");
        d_list.add("a");
        d_list.add(null);
        d_list.add("B");
        d_list.add("C");
        d_list.add(null);
        d_list.displayForward();
        d_list.trim();
        d_list.displayForward();

        //displayBackward
        System.out.println("        (9)");
        d_list.displayBackward();
    }
}
