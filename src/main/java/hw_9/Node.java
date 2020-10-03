package hw_9;

public class Node<E> {
    public E data;
    public Node Next;

    public Node(E data){
        this.data = data;
        Next = null;
    }
}
