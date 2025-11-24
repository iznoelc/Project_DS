import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.IOException;
import java.util.Scanner;

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
    public boolean exportGraph(String fileName, DiWeGraph<T> graph, Cycle<T> cycle) throws IOException {
        File myFile = new File("src//" + "graph." + fileName + ".txt");
        String path = System.getProperty("user.dir") + "\\src\\graph." + fileName + ".txt";
        if (myFile.createNewFile()) {
            System.out.println("File created: " + myFile.getName());
        }
        else {
            System.out.println("File already exists.");
            return false;
        }

        StringBuilder text = new StringBuilder();
        text.append("===PRINTING GRAPH===\n");
        for (Node<T> vertex : graph.getGraph()){
            text.append("Vertex " + vertex.getValue());
            for (Edge<T> edge : vertex.getEdgeList()){
                text.append("    -> " + edge.getSink().getValue());
            }
            text.append("\n");
        }

        //StringBuilder text = new StringBuilder();
        text.append("===PRINTING CYCLE===\n");
        if (cycle != null){
            text.append("Cycle found: ");
            for (Node cycleNode : cycle.getCycle()){
                text.append(cycleNode.getValue() + " -> ");

            }
            text.append(cycle.getCycle().getFirst().getValue().toString());
        } else {
            System.out.println("No cycle found.");
            return false;
        }

        try {
            Files.writeString(Paths.get(path), text.toString(), StandardOpenOption.APPEND);
            System.out.println("Cycle exported to file.");
        } catch (IOException e) {
            System.out.println("Error writing to file.");
        }

        return true;
    }
}


