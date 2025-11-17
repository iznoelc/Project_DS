import java.util.List;
import java.util.LinkedList;

public class Node<T> {
    private T value;
    private LinkedList<Edge> edges;

    public Node(T value){
        this.value = value;
    }

    public T getValue(){ return this.value; }
}
