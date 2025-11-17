import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {
    private List<LinkedList<Node>> graph;
    private boolean directed;

    // CONSTRUCTORS
    public Graph(){
        this.graph = new ArrayList<>();
        // initialize directed as FALSE if user does not choose
        directed = false;
    }

    public Graph(boolean directed){
        this.graph = new ArrayList<>();
        this.directed = directed;
    }

    /***
     * Add a vertex to the graph.
     * @param vertex What should be added to the graph.
     * @return Success of addition.
     */
    public boolean addVertex(Node vertex){
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

    /***
     * Edge that should be added to the graph. Adds one way if graph is directed (from source to sink), and both ways
     * (from source to sink and sink to source) if undirected.
     * @param source Value to originate edge
     * @param sink Value to end edge
     * @return Success of addition
     */
    public boolean addEdge(Node source, Node sink){
        // make sure both source and sink exist in the graph. otherwise, you can't make a connection
        // to a value Not in the graph.
        if (searchVertices(source) != null && searchVertices(sink) != null){
            if (this.directed){
                // if directed, we make the edge connection ONLY from source to sink

                // first, check if connection already exists
                // if it does not exist, add the connection
                // if it DOES exist, do not add the connection.
                if (searchConnections(searchVertices(source), sink) == null){
                    searchVertices(source).add(sink);
                    System.out.println("Successfully added edge from source " + source.getValue() + " to sink " + sink.getValue());
                } else {
                    System.out.println("LOG: Could not add edge because it already exists!");
                    return false;
                }
            } else {
                // otherwise, if undirected, we make the edge connection from source to sink AND sink to source

                // ensure connection does not already exist both ways
                // if it doesn't, we can add the edge
                if (searchConnections(searchVertices(source), sink) == null &&
                        searchConnections(searchVertices(sink), source) == null){
                    searchVertices(source).add(sink);
                    searchVertices(sink).add(source);
                    System.out.println("Successfully added edge with from source " + source.getValue() + " to sink " +
                            sink.getValue() + " and vice versa (undirected graph).");
                } else {
                    System.out.println("LOG: Could not add edge because it already exists!");
                    return false;
                }
            }
            return true;
        } else {
            System.out.println("LOG: Either source (" + source.getValue() + ") or sink (" + sink.getValue() + ") does not exist in the graph.");
            return false;
        }
    }

    /***
     * Remove a vertex from the graph if it exists
     * @param value Vertex to be removed
     * @return Success of removal
     */
    public boolean removeVertex(Node value){
        // if the graph is empty, there will be nothing to remove
        if (graph.isEmpty()){
            System.out.println("LOG: Nothing to remove; graph is empty");
            return false;
        }

        // otherwise, search through all vertices trying to find value you want to add
        // if null, it does not exist in the graph so we cannot remove a value that does not exist.
        // otherwise, it does exist in the graph, so we can remove it.
        if (searchVertices(value) == null){
            System.out.println("LOG: Vertex you are trying to remove does not exist in graph");
            return false;
        } else {
            // check all other vertices connections. if it has a connection to the vertex that should be removed,
            // remove this connection.
            for (LinkedList<Node> vertex : graph){
                if (vertex.contains(value) && vertex.getFirst() != value){
                    vertex.remove(value);
                }
            }

            graph.remove(searchVertices(value)); // remove the vertex itself
            System.out.println("LOG: Successfully removed vertex " + value.getValue() + " from the graph.");
            return true;
        }
    }

    /***
     * Remove an edge from the graph if it exists
     * @param source Start of edge to remove
     * @param sink End of edge to remove
     * @return success of removal
     */
    public boolean removeEdge(Node source, Node sink){
        if (this.directed){
            // if directed, we need to remove the connection from only the source to the sink
            // search for the connection. if it is not null, then it exists so we can remove it from
            // the graph. otherwise, if it is not found we cannot remove an edge that does not exist.
            if (searchConnections(searchVertices(source), sink) != null){
                searchVertices(source).remove(sink);
            } else {
                System.out.println("LOG: Could not remove edge because it does not exist!");
                return false;
            }
        } else {
            // otherwise, we need to remove the connection/edge from both ways, so perform the same check
            // as above but for both source to sink and sink to source. if it exists, remove it.
            if (searchConnections(searchVertices(source), sink) != null &&
                    searchConnections(searchVertices(sink), source) != null){
                searchVertices(source).remove(sink);
                searchVertices(sink).remove(source);
            } else {
                System.out.println("LOG: Could not remove edge because it does not exist!");
                return false;
            }
        }

        // otherwise, we were able to add the edge
        return true;
    }

    /***
     * Search through all edges of a specific vertex
     * @param listToSearch vertex to search through the connections of
     * @param target target value that is being searched for
     * @return value searched for, null if value was not found
     */
    public Node searchConnections(LinkedList<Node> listToSearch, Node target){
        // return null if the list to search is null
        if (listToSearch == null){
            return null;
        }

        // search through linked list for a specified connection
        // if that connection exists, return it
        for (Node value : listToSearch){
            if ( value.getValue() == target.getValue()){
                return target;
            }
        }

        // if that connection does not exist, return null
        return null;
    }

    /***
     * Search through all vertices of the graph
     * @param target vertex being searched for
     * @return vertex + connections of vertex if found, null if not found
     */
    public LinkedList<Node> searchVertices(Node target){
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

    /**
     * Print the entire graph
     */
    public void printGraph(){
        System.out.println("===PRINTING GRAPH===");
        for (LinkedList<Node> vertex : graph){
            for (Node node : vertex){
                System.out.print(node.getValue() + " -> ");
            }
            System.out.println();
        }
    }

    /***
     * Print all vertices of a graph
     */
    public void printVertexes(){
        System.out.println("===PRINTING ALL VERTICES===");
        for (LinkedList<Node> vertex : graph){
            System.out.println("Vertex " + vertex.getFirst().getValue());
        }
    }

    /***
     * Print all edges of a vertex
     * @param u, vertex that edges should be printed of 
     */
    public void printEdges(Node u){
        System.out.println("===PRINTING ALL EDGES FOR " + u + "===");
        for (Node node : searchVertices(u)){
            System.out.print(u.getValue() + " -> ");
        }
        System.out.println();
    }
}
