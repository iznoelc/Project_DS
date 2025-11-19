import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

/**
 * A class intended to represent a directed, weighted graph
 */
public class DiWeGraph<T> implements Graph<T> {
    private List<Node<T>> graph;

    public DiWeGraph(){
        this.graph = new ArrayList<>();
    }

    @Override
    public boolean addVertex(Node<T> vertex) {
        // return false if value already exists in graph (no duplicates!)

        // we can automatically skip while loop logic if the graph is empty because then there will obviously
        // not be a duplicate
        if(!graph.isEmpty()){
            if (searchVertices(vertex) != null){
//                System.out.println("LOG: Could not add " + vertex.getValue() + " to the graph because it already exists!");
                return false;
            }
        }

        //LinkedList<Node<T>> newVertex = new LinkedList<>(); // create a new linked list to store desired value in
        //newVertex.add(vertex); // add desired value to start of linked list
        graph.add(vertex); // add new linked list (vertex) to the graph

//        System.out.println("LOG: Successfully added " + vertex.getValue() + " to graph.");
        return true;
    }

    @Override
    public boolean removeVertex(Node<T> vertex) {
        // if the graph is empty, there will be nothing to remove
        if (graph.isEmpty()){
//            System.out.println("LOG: Nothing to remove; graph is empty");
            return false;
        }

        // otherwise, search through all vertices trying to find value you want to add
        // if null, it does not exist in the graph so we cannot remove a value that does not exist.
        // otherwise, it does exist in the graph, so we can remove it.
        if (searchVertices(vertex) == null){
//            System.out.println("LOG: Vertex you are trying to remove does not exist in graph");
            return false;
        } else {
            // check all other vertices connections. if it has a connection to the vertex that should be removed,
            // remove this connection.
            for (Node<T> node : graph){
//                if (node.getEdgeList().contains(vertex) && node.getFirst() != vertex){
//                    node.remove(vertex);
//                }
                // for each node in the graph, search its edge linked list.
                // if the vertex we want to remove is in this list (targetSink),
                // we want to REMOVE this connection, because the vertex we are deleting will
                // no longer be in the graph.
                Edge edgeToCheck = searchEdges(node.getEdgeList(), vertex);
                if (edgeToCheck != null){
                    node.getEdgeList().remove(edgeToCheck);
                }
            }

            graph.remove(searchVertices(vertex)); // remove the vertex itself
//            System.out.println("LOG: Successfully removed vertex " + vertex.getValue() + " from the graph.");
            return true;
        }
    }

    @Override
    public boolean addEdge(Node<T> source, Node<T> sink, int weight) {
        Edge<T> newEdge = new Edge<>(weight, source, sink);

        // we don't want to add an edge if the source already has a connection to the sink
        if (searchEdges(source.getEdgeList(), sink) != null){
//            System.out.println("LOG: Was unable to add edge with source " + source.getValue() + " to sink " + sink.getValue()
//                    + " with weight " + weight + " because it already exists in the graph.");
            return false;
        } else {
            source.getEdgeList().add(newEdge);
//            System.out.println("LOG: Adding new edge with source " + source.getValue() + " to sink " + sink.getValue()
//                    + " with weight " + weight);
        }
        return true;
    }

    @Override
    public boolean removeEdge(Node<T> source, Node<T> sink) {
        // Removes the edge between source and sink.
        if (source.getEdgeList() != null && sink.getEdgeList() != null && searchEdges(source.getEdgeList(), sink) != null){
            source.getEdgeList().remove(searchEdges(source.getEdgeList(), sink));
            return true;
        }
        return false;
    }

    @Override
    public Node<T> searchVertices(Node<T> target) {
        // search through array list to see if vertex exists.
        // if it exists, return the vertex as a linked list (including its connections)
        for (Node<T> vertex : graph){
            if (vertex.getValue() == target.getValue()){
                return vertex;
            }
        }

        // if that vertex does not exist, return null
        return null;
    }

    @Override
    public void printGraph() {
        System.out.println("===PRINTING GRAPH===");
        for (Node<T> vertex : graph){
            System.out.println("Vertex " + vertex.getValue());
            for (Edge<T> edge : vertex.getEdgeList()){
                System.out.println("    -> " + edge.getSink().getValue());
            }
        }
    }

    @Override
    public void printVertices() {
        System.out.println("===PRINTING ALL VERTICES===");
        for (Node<T> vertex : graph){
            System.out.println("Vertex " + vertex.getValue());
        }
    }

    @Override
    public void printEdges(Node<T> u) {
        if(searchVertices(u) == null){
            System.out.println("Vertex " + u.getValue() + " does not exist in graph");
        }
        else{
            System.out.println("Vertex " + u.getValue() + " has the following connections:");
            for (Edge<T> edge : u.getEdgeList()){
                System.out.println("    -> " + edge.getSink().getValue() + " (" + edge.getWeight() + ") ");
            }
        }
    }

    @Override
    public Edge<T> searchEdges(LinkedList<Edge<T>> edgesToSearch, Node<T> targetSink){
        for (Edge<T> edge : edgesToSearch){
            if (edge.getSink() == targetSink){
                return edge; // edge exists
            }
        }
        return null; // edge does not exist
    }

    public List<Node<T>> getGraph(){return this.graph;}
}