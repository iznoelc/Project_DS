import java.util.List;
import java.util.LinkedList;

/**
 * A class to represent a graph's node, or vertex.
 * @param <T> Take any type
 */
public class Node<T> {
    private T value;
    private LinkedList<Edge<T>> edges = new LinkedList<>();

    /**
     * Constructor to initialize the node/vertex's value
     * @param value
     */
    public Node(T value){
        this.value = value;
    }

    /**
     * Get the node's value.
     */
    public T getValue(){ return this.value; }

    /**
     * @return the node's edge list
     */
    public LinkedList<Edge<T>> getEdgeList(){ return this.edges; }
}
