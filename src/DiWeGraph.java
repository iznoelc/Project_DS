import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 * A class intended to represent a directed, weighted graph
 */
public class DiWeGraph implements Graph {
    private List<LinkedList<Node>> graph;

    public DiWeGraph(){
        this.graph = new ArrayList<>();
    }

    @Override
    public boolean addVertex(Node vertex) {
        // return false if value already exists in graph (no duplicates!)

        // we can automatically skip while loop logic if the graph is empty because then there will obviously
        // not be a duplicate
        if(!graph.isEmpty()){
            if (searchVertices(vertex) != null){
                System.out.println("LOG: Could not add " + vertex.getValue() + " to the graph because it already exists!");
                return false;
            }
        }

        LinkedList<Node> newVertex = new LinkedList<>(); // create a new linked list to store desired value in
        newVertex.add(vertex); // add desired value to start of linked list
        graph.add(newVertex); // add new linked list (vertex) to the graph

        System.out.println("LOG: Successfully added " + vertex.getValue() + " to graph.");
        return true;
    }

    @Override
    public boolean removeVertex(Node vertex) {
        // if the graph is empty, there will be nothing to remove
        if (graph.isEmpty()){
            System.out.println("LOG: Nothing to remove; graph is empty");
            return false;
        }

        // otherwise, search through all vertices trying to find value you want to add
        // if null, it does not exist in the graph so we cannot remove a value that does not exist.
        // otherwise, it does exist in the graph, so we can remove it.
        if (searchVertices(vertex) == null){
            System.out.println("LOG: Vertex you are trying to remove does not exist in graph");
            return false;
        } else {
            // check all other vertices connections. if it has a connection to the vertex that should be removed,
            // remove this connection.
            for (LinkedList<Node> node : graph){
                if (node.contains(vertex) && node.getFirst() != vertex){
                    node.remove(vertex);
                }
            }

            graph.remove(searchVertices(vertex)); // remove the vertex itself
            System.out.println("LOG: Successfully removed vertex " + vertex.getValue() + " from the graph.");
            return true;
        }
    }

    @Override
    public boolean addEdge(Node source, Node sink, int weight) {
        // TODO: Izzy
        return false;
    }

    @Override
    public boolean removeEdge(Node source, Node sink) {
        // Removes the edge between source and sink.
        if (searchVertices(source) != null && searchVertices(sink) != null && searchVertices(source).contains(sink)){
            return searchVertices(source).remove(sink);
        }
        return false;
    }

    @Override
    public LinkedList<Node> searchVertices(Node target) {
        // search through array list to see if vertex exists.
        // if it exists, return the vertex as a linked list (including its connections)
        for (LinkedList<Node> vertex : graph){
            if (vertex.getFirst().getValue() == target.getValue()){
                return vertex;
            }
        }

        // if that connection does not exist, return null
        return null;
    }

    @Override
    public void printGraph() {
        // TODO: Landon
        System.out.println("===PRINTING GRAPH===");
        for (LinkedList<Node> vertex : graph){
            System.out.println("Vertex " + vertex.getFirst().getValue());
            for (Node node : edges){
                System.out.println("    -> " + node.getEdge().getSink());
            }
        }
    }

    @Override
    public void printVertices() {
        System.out.println("===PRINTING ALL VERTICES===");
        for (LinkedList<Node> vertex : graph){
            System.out.println("Vertex " + vertex.getFirst().getValue());
        }
    }

    @Override
    public void printEdges(Node u) {
        // TODO: Landon
        if(searchVertices(u) == null){
            System.out.println("Vertex " + u.getValue() + " does not exist in graph");
        }
        else{
            System.out.println("Vertex " + u.getValue() + " has the following connections:");
            for (Node node : edges){
                System.out.println("    -> " + node.getEdge().getSink());
            }


        }
    }

    @Override
    public Node searchConnections(LinkedList listToSearch, Node target) {
        // TODO: Izzy
        return null;
    }
}