package hw_9;

import java.util.Collection;
import java.util.Iterator;

public class LinkedList implements IMyLinkedList {
    private Node First;
    private int size;

    public LinkedList() {
        First = null;
        size = 0;
    }
    public void displayList(){
        Node current = First;
        while(current!=null){
            System.out.print(current.data + " -> " );
            current = current.Next;
        }
        System.out.println("null");
    }

    @Override
    public boolean add(String str) {
        Node toAdd = new Node(str);
        Node current = First;
        if(First  == null){
            First = toAdd;
        }
        else {
            while (current.Next!=null){
                current = current.Next;
            }
            current.Next = toAdd;
        }
        return true;
    }

    @Override
    public boolean addAll(String[] strArr) {
        Node current;
        Node toAdd = new Node(strArr[0]);
        if(First==null){
            First = toAdd;
            current = First;
            for(int i = 1; i < strArr.length; i++){
                toAdd = new Node(strArr[i]);
                current.Next = toAdd;
                current = current.Next;
            }
        }
        else{
            current = First;
            while (current.Next!=null){
                current = current.Next;
            }
            for(int i = 0; i < strArr.length; i++){
                toAdd = new Node(strArr[i]);
                current.Next = toAdd;
                current = current.Next;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection strColl) {
        Iterator<String> iterator = strColl.iterator();
        Node current;
        Node toAdd = new Node(iterator.next());
        System.out.println(First);
        if(First==null){
            First = toAdd;
            current = First;
            // while loop
            while (iterator.hasNext()) {
                toAdd = new Node(iterator.next());
                current.Next = toAdd;
                current = current.Next;
            }
        }
        else{
            current = First;
            while (current.Next!=null){
                current = current.Next;
            }
            iterator = strColl.iterator();

            // while loop
            while (iterator.hasNext()) {
                toAdd = new Node(iterator.next());
                current.Next = toAdd;
                current = current.Next;
            }
        }
        return true;
    }

    @Override
    public boolean delete(int index) {
        int i = 0;
        Node current = First;
        Node previous = First;
        if(index == 0){
            First = current.Next;
            return true;
        }
        while(i!=index){
            if(current.Next == null){
                return false;
            }
            i++;
            previous = current;
            current = current.Next;
        }
        previous.Next = current.Next;
        return true;
    }

    @Override
    public boolean delete(String str) {
        Node current = First;
        Node previous = First;
        while(current.data != str)
        {
            if(current.Next == null)
                return false;
            else
            {
                previous = current;
                current = current.Next;
            }
        }
        if(current == First)
            First = First.Next;
        else
            previous.Next = current.Next;
        return true;
    }

    @Override
    public String get(int index) {
        Node current = First;
        int i = 0;
        while(i!=index){
            current = current.Next;
            i++;
        }
        return (String) current.data;
    }

    @Override
    public boolean contains(String str) {
        Node current = First;
        while(current.data != str)
        {
            if(current.Next == null)
                return false;
            else{
                current = current.Next;
            }
        }
        return true;
    }

    @Override
    public boolean clear() {
        First = null;
        return true;
    }

    @Override
    public int size() {
        Node current = First;
        int i = 0;
        while(current!=null){
            i++;
            current = current.Next;
        }
        return i;
    }

    @Override
    public boolean trim() {
        while(this.contains(null)){
            Node current = First;
            Node previous = First;
            while(current.data != null)
            {
                previous = current;
                current = current.Next;
            }
            if(current == First)
                First = First.Next;
            else
                previous.Next = current.Next;
        }
        return true;
    }

    @Override
    public boolean compare(Collection coll) {
        if(coll.size()!=this.size())
            return false;
        Node current = First;
        Iterator<String> iterator = coll.iterator();

        // while loop
        while (iterator.hasNext()) {
            if(current.data!=iterator.next()){return false;}
            System.out.println(current.data+" "+iterator.toString());
            current = current.Next;
        }
        return true;
    }
}
