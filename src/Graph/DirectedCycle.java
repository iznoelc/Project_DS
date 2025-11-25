package Graph;

import java.util.ArrayList;

/**
 * Find a cycle in a Directed graph.
 * Base DFS algorithm to find cycle taken from channel "take U forward" on YouTube.
 * <a href="https://www.youtube.com/watch?v=9twcmtQj4DU">...</a>
 *
 * @param <T> Take any type (since graph interface/concrete classes can take any type)
 */
public class DirectedCycle<T> implements Cycle<T> {
    private Node<T> successNode;
    public ArrayList<Node<T>> cycle = new ArrayList<>();

    /**
     * Recursively calls the dfs algorithm on all unvisited adjacent nodes to the node passed to it.
     * Finds a cycle according to if the success node has been previously visited on the same path, meaning
     * we've circled around and thus have a cycle.
     * @param node, current node to check adjacent nodes of
     * @param graph, our entire graph
     * @param vis, an array list containing nodes we have already visited
     * @param pathVis, an array list containing nodes we have already visited on the CURRENT PATH
     * @return true if path is found, false otherwise
     */
    @Override
    public boolean dfsCheck(Node<T> node, Graph<T> graph, ArrayList<Node<T>> vis, ArrayList<Node<T>> pathVis){
        // update visited and path visited to include current node because we have now seen this node AND seen
        // it on this specific path.
        if (!vis.contains(node)){
            vis.add(node);

        }

        if (!pathVis.contains(node)){
            pathVis.add(node);
        }

        // traverse the adjacent nodes  by traversing the node's edge list
        for (Edge<T> edge : node.getEdgeList()){
            // if the node in the edge list has not been visited, recursively call dfs check to check this
            // node for a cycle
            if (!vis.contains(edge.getSink())){
                // if it goes on to find a cycle, we should return true
                if (dfsCheck(edge.getSink(), graph, vis, pathVis)) return true;
            }
            // if the node has been previously visited (on the same path)
            else if (pathVis.contains(edge.getSink())){
                successNode = edge.getSink();
                return true;
            }
        }

        // when we are going back, we want to REMOVE the current node from the path visited
        pathVis.remove(node);
        return false;
    }

    /**
     * Finds a cycle in a graph by ensuring all nodes are checked in the graph and deploying the dfs check on
     * them to detect cycles.
     * @param graph the graph we want to find a cycle on
     * @return cycle if found, null if not
     */
    @Override
    public ArrayList<Node<T>> findCycle(Graph<T> graph){
        System.out.println("Find cycle");

        ArrayList<Node<T>> vis = new ArrayList<>(); // store visited nodes
        ArrayList<Node<T>> pathVis = new ArrayList<>(); // store nodes visited on current path

        // for each node in the graph...
        for (Node<T> node: graph.getGraph()){
            // if the node has NOT been visited
            if (!vis.contains(node)){
                // we need to call our dfs check on this node
                // if it returns TRUE, there is a cycle
                if (dfsCheck(node, graph, vis, pathVis)) {
                    // print cycle
                    // store it in a variable (for exporting purposes)
                    if (successNode != null){
                        // first find start of cycle
                        int i;
                        for (i = 0; i < pathVis.size(); i++){
                            if (pathVis.get(i) == successNode){ break; }
                        }

                        // then print start of cycle to end of cycle
                        System.out.println("Cycle found: ");
                        for (int j = i; j < pathVis.size(); j++){
                            cycle.add(pathVis.get(j));
                            System.out.print(pathVis.get(j).getValue() + " -> ");
                        }
                        System.out.println(successNode.getValue());
                    }

                    return cycle;
                }
            }
        }
        // if it returns FALSE
        // there is not a cycle.

        System.out.println("No cycle found.");
        return null;
    }

    @Override
    public void printCycle(){

        if (cycle != null){
            System.out.println("Cycle found: ");
            for (Node cycleNode : cycle){
                System.out.print(cycleNode.getValue() + " -> ");
            }
            //System.out.println(cycle.getFirst().getValue());
        } else {
            System.out.println("No cycle found.");
        }
    }

    @Override
    public ArrayList<Node<T>> getCycle(){ return this.cycle; }

}
