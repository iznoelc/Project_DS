import java.util.ArrayList;

/**
 * Interface to layout necessary methods for finding a cycle within a graph.
 * @param <T>
 */
public interface Cycle<T> {

    boolean dfsCheck(Node<T> node, Graph<T> graph, ArrayList<Node<T>> vis, ArrayList<Node<T>> pathVis);
    ArrayList<Node<T>> findCycle(Graph<T> graph);
    void printCycle();

    ArrayList<Node<T>> getCycle();

}
