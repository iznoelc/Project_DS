import java.util.List;
import java.util.LinkedList;

public interface Graph<T> {
    boolean addVertex(Node<T> vertex);
    boolean removeVertex(Node<T> vertex);

    boolean addEdge(Node<T> source, Node<T> sink, int weight);
    boolean removeEdge(Node<T> source, Node<T> sink);

    Node<T> searchVertices(Node<T> target);
    Edge<T> searchEdges(LinkedList<Edge<T>> edgesToSearch, Node<T> targetSink);

    void printGraph();
    void printVertices();
    void printEdges(Node<T> u);

    List<Node<T>> getGraph();
    int getNumNodes();

}
