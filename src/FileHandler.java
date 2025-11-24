import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;

public class FileHandler<T> {


    /**
     * Takes a graph as an input and creates a text file
     * in the source folder of the graph.
     *
     * Text file name follows the scheme of graph.graphName.txt
     *
     * @param graph
     * @return boolean
     * @throws IOException
     */
    public boolean exportGraph(DiWeGraph<T> graph) throws IOException {
        File myFile = new File("graph."+"graphName"+".txt");
        String path = System.getProperty("user.dir") + "\\src\\graph." + "graphName" +".txt";
        if (myFile.createNewFile()) {
            System.out.println("File created: " + myFile.getName());
        }
        else {
            System.out.println("File already exists.");
            return false;
        }

        //NOTE TO LANDON!!!
        //Changed node and edge <T> to <Integer> and that seems to have taken away teh errors but idk man, check it
        String text = "";
        text.join("===PRINTING GRAPH===\n");
        for (Node<T> vertex : graph.getGraph()){
            text.join("Vertex " + vertex.getValue());
            for (Edge<T> edge : vertex.getEdgeList()){
                text.join("    -> " + edge.getSink().getValue());
            }
            text.join("\n");
        }

        try {
            Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

    /**
     * Takes a cycle as an input and creates a text file
     * in the source folder of the cycle.
     *
     * Text file name follows the scheme of cycle.cycleName.txt
     *
     * @param cycle
     * @return boolean
     * @throws IOException
     */

    public boolean exportCycle(Cycle<T> cycle) throws IOException {
        File myFile = new File("cycle."+"graphName"+".txt");
        String path = System.getProperty("user.dir") + "\\src\\cycle." + "graphName" +".txt";
        if (myFile.createNewFile()) {
            System.out.println("File created: " + myFile.getName());
        }
        else {
            System.out.println("File already exists.");
            return false;
        }

        String text = "";
        text.join("===PRINTING CYCLE===\n");
        if (cycle != null){
            text.join("Cycle found: ");
            for (Node cycleNode : cycle.getCycle()){
                text.join(cycleNode.getValue() + " -> ");
            }
            text.join(cycle.getCycle().getFirst().getValue().toString());
        } else {
            System.out.println("No cycle found.");
            return false;
        }

        try {
            Files.write(Paths.get(path), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            return false;
        }

        return true;
    }

}


