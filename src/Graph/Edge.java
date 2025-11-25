package Graph;

/**
 * A class to represent an edge in a graph.
 * @param <T> Take any type
 */
public class Edge<T> {
    private int weight;
    private Node<T> source;
    private Node<T> sink;

    /**
     * Constructor to initialize an edge.
     * @param weight of the edge
     * @param source node of the edge
     * @param sink node of the edge
     */
    public Edge(int weight, Node<T> source, Node<T> sink){
        this.weight = weight;
        this.source = source;
        this.sink = sink;
    }

    /**
     * @return The edge's weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @return The edge's source
     */
    public Node<T> getSource(){
        return source;
    }

    /**
     * @return The edge's sink
     */
    public Node<T> getSink(){
        return sink;
    }
}
