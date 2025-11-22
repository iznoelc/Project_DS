import java.util.ArrayList;

/**
 * Helper class to manage paths.
 */
public class Path<T> {
    private ArrayList<Node<T>> path;
    private int pathCost = 0;

    public Path(){
        this.path = new ArrayList<>();
    }

    /**
     * @return The path
     */
    public ArrayList<Node<T>> getPath(){ return path; }

    /**
     * Add a node to the path.
     * @param node to be added to the path
     * @param weight to increment the path's cost by
     */
    public void addToPath(Node<T> node, int weight){
        path.add(node);
        pathCost += weight;
    }

    /**
     * Print out the path and its cost.
     */
    public void printPath(){
        System.out.println("Path from " + path.getFirst().getValue() + " to " + path.getLast().getValue() + " exists!");
        for (Node<T> node : path){
            System.out.print(node.getValue() + " -> ");
        }
        System.out.println("\nPath cost: " + this.pathCost);
    }

    /**
     * Set the path's cost.
     * @param pathCost integer to set the path's cost to
     */
    public void setPathCost(int pathCost){ this.pathCost = pathCost; }

    /**
     * @return the path's cost
     */
    public int getPathCost(){ return this.pathCost; }
}
