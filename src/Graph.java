import java.util.List;
import java.util.LinkedList;

/**
 * An interface to outline key methods of a graph.
 * @param <T> Take any type
 */
public interface Graph<T> {
    /**
     * Add a vertex to the graph.
     * @param vertex to be added to the graph.
     * @return success of the operation.
     */
    boolean addVertex(Node<T> vertex);

    /**
     * Remove a vertex from the graph. Ensures all connections to vertex are also removed.
     * @param vertex to be removed from the graph
     * @return success of the operation.
     */
    boolean removeVertex(Node<T> vertex);

    /**
     * Add an edge to the graph.
     * @param source of the edge
     * @param sink of the edge
     * @param weight of the edge
     * @return success of the operation
     */
    boolean addEdge(Node<T> source, Node<T> sink, int weight);

    /**
     * Remove an edge from the graph.
     * @param source of edge to be removed
     * @param sink of edge to be removed
     * @return success of the operation
     */
    boolean removeEdge(Node<T> source, Node<T> sink);

    /**
     * Find a path from a source node to a sink node in the graph.
     * @param source of the path
     * @param sink of the path
     * @return the path if found
     */
    Path<T> findPath(Node<T> source, Node<T> sink);

    /**
     * Helper function to search vertices of the graph.
     * @param target node that we are searching for
     * @return Node if found
     */
    Node<T> searchVertices(Node<T> target);

    /**
     * Helper function to search the edges of the graph.
     * @param edgesToSearch Edge list of node that we want to search the edges for
     * @param targetSink Sink we are looking for in above node's edge list
     * @return Edge from source node to sink node if found
     */
    Edge<T> searchEdges(LinkedList<Edge<T>> edgesToSearch, Node<T> targetSink);

    /**
     * Display the graph to the console.
     */
    void printGraph();

    /**
     * Display all vertices of the graph to the console.
     */
    void printVertices();

    /**
     * Display all edges of a specified node u to the console.
     * @param u
     */
    void printEdges(Node<T> u);

    /**
     * @return The graph's node representation, an array list of nodes, each containing edge lists
     */
    List<Node<T>> getGraph();

    /**
     * @return The number of vertices in the graph.
     */
    int getNumNodes();

    /**
     * Helper function to convert raw user input and find it according to the graph's representation.
     * @param input user wants to find in the graph
     * @return node if found
     */
    Node<T> findInputInGraph(T input);
}
