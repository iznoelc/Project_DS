import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;

/**
 * A class intended to represent a directed, weighted graph
 */
public class DiWeGraph<T> implements Graph<T> {
    private List<Node<T>> graph;

    public DiWeGraph(){
        this.graph = new ArrayList<>();
    }

    /**
     * @param vertex to be added to the graph
     * @return success of the operation
     */
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

        graph.add(vertex);

//        System.out.println("LOG: Successfully added " + vertex.getValue() + " to graph.");
        return true;
    }

    /**
     * Remove a vertex from the graph; ensure all connections attached to vertex are also removed.
     * @param vertex to be removed from the graph
     * @return success of the operation
     */
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

    /**
     * Add an edge to the graph; ensure an edge from the source to sink doesn't already exist, even
     * if it has the same weight in order to avoid multigraph.
     * @param source origin of the edge
     * @param sink destination of the edge
     * @param weight of the edge
     * @return success of the operation
     */
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

    /**
     * Remove an edge from the graph; ensures edge exists first.
     * @param source origin of the edge
     * @param sink destination of the edge
     * @return success of the operation
     */
    @Override
    public boolean removeEdge(Node<T> source, Node<T> sink) {
        // Removes the edge between source and sink.
        if (source.getEdgeList() != null && sink.getEdgeList() != null && searchEdges(source.getEdgeList(), sink) != null){
            source.getEdgeList().remove(searchEdges(source.getEdgeList(), sink));
            return true;
        }
        return false;
    }

    /**
     * Find and print a path from a source node to a sink node using a BFS algorithm.
     * Source: GeeksForGeeks
     * <a href="https://www.geeksforgeeks.org/dsa/find-if-there-is-a-path-between-two-vertices-in-a-given-graph/">...</a>
     * <a href="https://www.geeksforgeeks.org/dsa/print-paths-given-source-destination-using-bfs/">...</a>
     *
     * @param source origin of the path
     * @param sink destination of the path
     * @return the path found
     */
    @Override
    public Path<T> findPath(Node<T> source, Node<T> sink){
        int n = this.graph.size();

        // keep track of visited vertices
        ArrayList<Node<T>> vis = new ArrayList<>();

        Queue<Node<T>> q = new LinkedList<>();
        Queue<Path<T>> pathQueue = new LinkedList<>();

        // mark the source node as visited and enqueue it
        vis.add(source);
        q.add(source);
        Path<T> initialPath = new Path<>();
        initialPath.getPath().add(source);
        pathQueue.add(initialPath);

        // edge case - node has a path to itself
        if (source == sink){
            if(searchEdges(source.getEdgeList(), sink) != null){
                System.out.println("Node " + source.getValue() + " has a path to itself");
                initialPath.addToPath(sink, this.searchEdges(source.getEdgeList(), sink).getWeight());
                return initialPath;
            }
        }

        while (!q.isEmpty() && !pathQueue.isEmpty()){
            Node<T> curr = q.poll();
            Path<T> currPath = pathQueue.poll();

            // if current vertex is the destination, return true
            if (curr == sink && currPath.getPath().size() > 1){
                // initial path cost from first node to second node in the path gets skipped,
                // ensure it's added.
                currPath.setPathCost(currPath.getPathCost() +
                        currPath.getPath().getFirst().getEdgeList().getFirst().getWeight());
                return currPath;
            }

            for (int i = 0; i < curr.getEdgeList().size(); i++){
                Node<T> nextVertex = curr.getEdgeList().get(i).getSink();

                if (source == sink){
                    if (nextVertex == source){
                        System.out.println("You are trying to detect a cycle.");
                        Path<T> newPath = new Path<>();
                        assert currPath != null;

                        // add lost weight from 1st connection to 2nd connection
                        currPath.setPathCost(currPath.getPathCost() +
                                currPath.getPath().getFirst().getEdgeList().getFirst().getWeight());

                        newPath.getPath().addAll(currPath.getPath());
                        newPath.setPathCost(currPath.getPathCost());

                        newPath.addToPath(nextVertex, curr.getEdgeList().get(i).getWeight());
                        return newPath;
                    }
                }

                // if next vertex has not been visited
                if (!vis.contains(nextVertex)){
                    // mark it as visited and enqueue it
                    vis.add(nextVertex);
                    q.add(nextVertex);
                    Path<T> newPath = new Path<>();
                    assert currPath != null;
                    newPath.getPath().addAll(currPath.getPath());
                    newPath.setPathCost(currPath.getPathCost());

                    newPath.addToPath(nextVertex, curr.getEdgeList().get(i).getWeight());
                    pathQueue.add(newPath);
                }
            }
        }

        // bfs complete without reaching sink node, no path. return false.
        System.out.println("Path from " + source.getValue() + " to " + sink.getValue() + " does not exist.");
        return null;
    }

    /**
     * Helper function to search the vertices of a graph to see if the target vertex exists.
     * @param target vertex to find
     * @return vertex if found, null if not found
     */
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

    /**
     * Print the entire graph and its edges (excluding weights)
     */
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

    /**
     * Print all vertices of the graph.
     */
    @Override
    public void printVertices() {
        System.out.println("===PRINTING ALL VERTICES===");
        for (Node<T> vertex : graph){
            System.out.println("Vertex " + vertex.getValue());
        }
    }

    /**
     * Print all edges of a specified node u. Print weights alongside each edge.
     * Format: Node (weight from u to Node) -> ...
     * @param u node to print edges of
     */
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

    /**
     * Helper function to search for a target node inside a source node's edge list.
     * @param edgesToSearch source node we are searching the edge list of
     * @param targetSink node that we want to find a connection to
     * @return edge from source to sink if connection exists, null if not
     */
    @Override
    public Edge<T> searchEdges(LinkedList<Edge<T>> edgesToSearch, Node<T> targetSink){
        for (Edge<T> edge : edgesToSearch){
            if (edge.getSink() == targetSink){
                return edge; // edge exists
            }
        }
        return null; // edge does not exist
    }

    /**
     * @return this graph
     */
    @Override
    public List<Node<T>> getGraph(){ return this.graph; }

    /**
     * @return number of nodes in this graph
     */
    @Override
    public int getNumNodes(){ return graph.size(); }

    /**
     * Find user input in the graph.
     * @param input value to be found in the graph
     * @return the node in the graph if data is found, null if not found
     */
    @Override
    public Node<T> findInputInGraph(T input){
        for (Node<T> vertex : this.graph){
            if (vertex.getValue().equals(input)){
                return vertex;
            }
        }

        return null;
    }
}