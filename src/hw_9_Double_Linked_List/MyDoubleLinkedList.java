package hw_9_Double_Linked_List;

import java.util.Collection;
import java.util.Iterator;

public class MyDoubleLinkedList implements IDoubleLinkedList {
    private Node First;
    private Node Last;
    private int size;

    public MyDoubleLinkedList(){
        First = null;
        Last = null;
    }
    public boolean isEmpty() {
        return First==null;
    }
    @Override
    public boolean add(String str) {
        Node newNode = new Node(str);
        if(isEmpty()){
            First = newNode;
        }
        else{
            Last.Next = newNode;
            newNode.Previous = Last;
        }
        Last = newNode;
        return true;
    }

    @Override
    public boolean addAll(String[] strArr) {
        for(int i = 0; i < strArr.length; i++){
            Node newNode = new Node(strArr[i]);
            if(First == null){
                First = newNode;
            }
            else{
                Last.Next = newNode;
                newNode.Previous = Last;
            }
            Last = newNode;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection strColl) {
        Iterator iter = strColl.iterator();
        while(iter.hasNext()){
            Node newNode = new Node(iter.next());
            if(First == null){
                First = newNode;
            }
            else{
                Last.Next = newNode;
                newNode.Previous = Last;
            }
            Last = newNode;
        }
        return true;
    }

    @Override
    public boolean delete(int index) {
        if(index>this.size())
            return false;
        Node current = First;
        for(int i = 0; i < index; i++){
            current = current.Next;
        }
        if(current==First)
            First = First.Next;
        else{
            current.Previous.Next = current.Next;
        }

        if(current==Last){
            Last = Last.Previous;
        }
        else{
            current.Next.Previous = current.Previous;
        }
        return true;
    }

    @Override
    public boolean delete(String str) {
        Node current = First;
        while(current.data!=str){
            current = current.Next;
            if(current==null){
                return false;
            }
        }
        if(current==First)
            First = First.Next;
        else{
            current.Previous.Next = current.Next;
        }

        if(current==Last){
            Last = Last.Previous;
        }
        else{
            current.Next.Previous = current.Previous;
        }
        return true;
    }

    @Override
    public String get(int index) {
        if(index >= this.size()){
            throw new IndexOutOfBoundsException();
        }
        Node current = First;
        for(int i = 0; i < index; i++){
            current = current.Next;
        }
        return (String)current.data;
    }

    @Override
    public boolean contains(String str) {
        Node current = First;
        while(current.data!=str){
            current = current.Next;
            if(current==null){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean clear() {
        First = null;
        Last = null;
        return true;
    }

    @Override
    public int size() {
        size = 0;
        Node current = First;
        while(current!=null){
            current = current.Next;
            size++;
        }
        return size;
    }

    @Override
    public boolean trim() {
        Node current = First;
        while(this.contains(null)){
            current = current.Next;
            if(current.data==null){
                if(current==First)
                    First = First.Next;
                else{
                    current.Previous.Next = current.Next;
                }

                if(current==Last){
                    Last = Last.Previous;
                }
                else{
                    current.Next.Previous = current.Previous;
                }
            }
        }
        return true;
    }

    @Override
    public boolean compare(Collection coll) {
        if(coll.size()!=this.size())
            return false;
        Node current = First;
        Iterator iter = coll.iterator();
        while(iter.hasNext()){
            if(current.data==iter.next()){
                current = current.Next;
                continue;
            }
            else{
                return false;
            }
        }
        return true;
    }

    public void displayForward(){
        System.out.println("First -> Last");
        Node current = First;
        while(current!=null){
            current.displayLink();
            if(current.Next!=null)
                System.out.print(" => ");
            current = current.Next;
        }
        System.out.println("\n");
    }
    public void displayBackward(){
        System.out.println("Last -> First");
        Node current = Last;
        while(current!=null){
            current.displayLink();
            if(current.Previous!=null)
                System.out.print(" <= ");
            current = current.Previous;
        }
        System.out.println("\n");
    }
}
