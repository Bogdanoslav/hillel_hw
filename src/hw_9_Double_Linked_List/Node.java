package hw_9_Double_Linked_List;

public class Node<E> {
    public E data;
    public Node Next;
    public Node Previous;

    public Node(E data){
        this.data = data;
    }
    public void displayLink(){
            System.out.print(data);
    }
}