import java.util.List;
import java.util.LinkedList;

public interface Graph<T> {
    boolean addVertex(Node vertex);
    boolean removeVertex(Node vertex);

    boolean addEdge(Node source, Node sink, int weight);
    boolean removeEdge(Node source, Node sink);

    Node searchConnections(LinkedList<Node> listToSearch, Node target);
    LinkedList<Node> searchVertices(Node target);

    void printGraph();
    void printVertices();
    void printEdges(Node u);
}
